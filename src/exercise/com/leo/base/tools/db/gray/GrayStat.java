package exercise.com.leo.base.tools.db.gray;

import org.junit.Test;
import org.nutz.dao.Dao;

import exercise.com.leo.base.tools.db.DBconnFactory;

/**
 * 连续两周之内每周出现2次的联系人
 * @author lla
 *
 */
public class GrayStat {
  
  @Test
  public void testProcedure() {
    Dao dao = DBconnFactory.getInstance().getDao();
    
  }
}
