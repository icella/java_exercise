package exercise.com.leo.base.lang.io;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exercise.com.leo.base.tools.db.TbDunTelExecute;
import exercise.com.leo.base.tools.phoneinfo.RegExpValidateUtil;

public class UpdateDunView {
  protected static Logger log = LoggerFactory.getLogger(UpdateDunView.class);

  public static void main(String[] args) throws IOException {
    updateDetailContactQc();
  }

  private static void updateDetailContactQc() {
    TbDunTelExecute execute = new TbDunTelExecute();
    String tb_details = "tb_call_details";
    Map<String, String> getAllPair = new HashMap<String, String>() {
      private static final long serialVersionUID = 1L;
      {
        put("cid", "1");
      }
    };

    List<CallDetail> allRecords = execute.getCallDetailAllRecords(tb_details, getAllPair);
    for (final CallDetail record : allRecords) {
      String phoneNumber = record.getContact();
      if (RegExpValidateUtil.isTelephone(phoneNumber)) {
        final String code = phoneNumber.substring(0, 3);
        // update db_grayscale.tb_call_details set contact_qc = '' where contact = '';
        Map<String, String> getPair = new HashMap<String, String>() {
          private static final long serialVersionUID = 1L;
          {
            put("area_code", code);
          }
        };

        List<AreaCode> qc = execute.getAreaCodeRecords("upc.tb_area_code_info", getPair);
        final AreaCode areaCodeRecord = qc.get(0);

        Map<String, String> setPair = new HashMap<String, String>() {
          private static final long serialVersionUID = 1L;
          {
            put("contact_qc", areaCodeRecord.getProvince() + areaCodeRecord.getCity());
          }
        };

        Map<String, String> wherePair = new HashMap<String, String>() {
          private static final long serialVersionUID = 1L;
          {
            put("contact", record.getContact());
          }
        };

        execute.executeSql(tb_details, setPair, wherePair, "update");
      }
    }
  }

  private static void insertTbAreaCode() throws IOException {
    String path = "/home/lla/Documents/test_daily/2016/gray/get_dun_telephone/";
    List<String> contents = FileUtils.readLines(new File(path + "dialling-code"));
    log.info(String.valueOf(contents.size()));

    String tbName = "tb_area_code_info";
    TbDunTelExecute execute = new TbDunTelExecute();
    for (String line : contents) {
      try {
        String[] fields = line.split(" ", -1);
        final String code = fields[0];
        String[] addr = fields[1].split("/", -1);
        final String province = addr[0];
        final String city = addr[1];

        Map<String, String> existsPair = new HashMap<String, String>() {
          private static final long serialVersionUID = 1L;
          {
            put("area_code", code);
            put("province", province);
            put("city", city);
          }
        };
        execute.executeSql(tbName, existsPair, null, "insert");
      } catch (Exception e) {
        e.printStackTrace();
        continue;
      }
    }
  }
}
