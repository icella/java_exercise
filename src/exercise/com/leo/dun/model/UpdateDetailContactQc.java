package exercise.com.leo.dun.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import exercise.com.leo.base.tools.db.DBconnFactory;
import exercise.com.leo.base.tools.phoneinfo.RegExpValidateUtil;

public class UpdateDetailContactQc {
  protected static Logger log = LoggerFactory.getLogger(UpdateDetailContactQc.class);

  // tb_area_code表中,area_code是三位数字的单独列出来。其余的都是四位
  static List<String> AreaCodes_3digist = new ArrayList<String>() {
    {
      add("010");
      add("020");
      add("021");
      add("022");
      add("023");
      add("024");
      add("025");
      add("027");
      add("028");
      add("029");
    }
  };

  public static void main(String[] args) throws IOException {
    DBconnFactory dbd = DBconnFactory.getInstance();
    Dao dao = dbd.getDao();

    int callDetailsCount = dao.count(CallDetails.class);
    int page = callDetailsCount / 1000 + 1;
    log.info(String.format(
        "Total number of tb_call_details is %d, 1000 record per page, total page numbers are %d  ",
        callDetailsCount, page));

    int updateCounter = 0;
    List<String> unMatchCode = Lists.newArrayList();
    for (int i = 1; i < page; i++) {
      List callList = getCallDetailList(dao, i, 1000).getList();
      for (Object call : callList) {
        CallDetails record = (CallDetails) call;
        String contact = record.getContact();
        String contactQc = record.getContactQc();
        if (!StringUtils.isEmpty(contactQc)) {
          continue;
        }

        if (RegExpValidateUtil.isTelephone(contact)) {
          String prefix = "";
          for (String code : AreaCodes_3digist) {
            if (contact.startsWith(code)) {
              prefix = contact.substring(0, 3);
            } else {
              prefix = contact.substring(0, 4);
            }
            break;
          }

          if (!prefix.equals("")) {
            AreaCode areaCode = dao.fetch(AreaCode.class, Cnd.where("area_code", "= ", prefix));
            if (null != areaCode) {
              String addr = areaCode.getProvince() + areaCode.getCity();
              log.info(String.format("Current record's contact is %s, prefix is %s, address is %s",
                  contact, prefix, addr));

              record.setContactQc(addr);

              dao.update(record);
              updateCounter++;
              log.info(record.toString());
            } else {
              unMatchCode.add(contact);
            }
          }
        } else if (RegExpValidateUtil.isMobile(contact)) {
          String prefix = contact.substring(0, 7);
          PhoneSegmentInfo segmentInfo =
              dao.fetch(PhoneSegmentInfo.class, Cnd.where("phone_segment", "=", prefix));
          if (null != segmentInfo) {
            String addr = segmentInfo.getProvince() + segmentInfo.getCity();
            log.info(String.format("Current record's contact is %s, prefix is %s, address is %s",
                contact, prefix, addr));
            record.setContactQc(addr);

            dao.update(record);
            updateCounter++;
            log.info(record.toString());
          }
        }
      }
    }

    log.info(String.format("Number of updated records is %d", updateCounter));
    FileUtils.writeLines(new File("/home/lla/Documents/test_daily/2016/12/7"), unMatchCode);
  }

  static QueryResult getCallDetailList(Dao dao, int pageNumber, int pageSize) {
    Pager pager = dao.createPager(pageNumber, pageSize);
    List<CallDetails> list = dao.query(CallDetails.class, null, pager);
    pager.setRecordCount(dao.count(CallDetails.class));
    return new QueryResult(list, pager);
  }
}
