package exercise.com.leo.portal;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exercise.com.leo.base.tools.GenerateTradeNo;
import exercise.com.leo.base.tools.RandomUtil;
import exercise.com.leo.base.tools.TimeUtils;
import exercise.com.leo.base.tools.db.DBconnFactory;
import exercise.com.leo.base.tools.db.procedure.App;

/**
 * insert random company into tb_app_copy.
 * @author lla
 *
 */
public class InsertTbApp {
  protected static Logger log = LoggerFactory.getLogger(InsertTbApp.class);

  public static void main(String[] args) throws Exception {
    DBconnFactory dbd = DBconnFactory.getInstance();
    Dao dao = dbd.getDao();
    
    int counter = dao.count(App.class);
    log.info(String.valueOf(counter));
    
    try {
      LineIterator it = FileUtils.lineIterator(new File("/home/lla/Documents/test_daily/2017/2/tb_company.csv"));
      while (it.hasNext()) {
        String line = it.nextLine().trim();
        if(StringUtils.isEmpty(line))
          continue;
        
        String companyName = line.substring(1, line.length() - 1);
        
        App app = new App();
        app.setAppCompany(companyName);
        
        String timeStr = TimeUtils.format(new Date(), "yyyyMMdd");
        String appKey = "1" + timeStr + GenerateTradeNo.getInstance().generateId();
        app.setAppKey(appKey);
        
        String appSecret = "1" + System.currentTimeMillis() + RandomUtil.genRandomString(6);
        app.setAppSecret(appSecret);
        
        app.setAppState(1);
        app.setCreateTime(new Date());
        app.setLastupdatetime(new Date());
        app.setAppType(1);
        app.setLimitTimes(0);
        app.setLimitUnit(0);
        
        dao.insert(app);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
