package exercise.com.leo.base.tools.db.procedure;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;

import exercise.com.leo.base.tools.TimeUtils;
import exercise.com.leo.base.tools.db.DBconnFactory;

public class CallProcedure {

  @Before
  public void setUp() throws Exception {}

//  @Test
  public void testProcedure() {
    Dao dao = DBconnFactory.getInstance().getDao();
    Cnd condition = Cnd.where("app_id", "=", "14");
    condition.and("module_type", "=", "9");
    ModulePlan modulePlan = dao.fetch(ModulePlan.class, condition);
    Assert.assertNotNull(modulePlan);
    // Assert.assertEquals(modulePlan.getPlanAccessTimesDaily(), new Integer(499658));
    System.out.println(modulePlan.getPlanAccessTimesDaily());
  }
  
  @Test
  public void freqTestPro() throws InterruptedException{
    long start = System.currentTimeMillis();
    System.out.println(TimeUtils.dateToString(new Date()));
    ExecutorService executor = Executors.newCachedThreadPool();
    for (int i = 0; i < 5; i++) {
        Runnable worker = new MyRunnable(2, i);
        executor.execute(worker);
//        System.out.println("add " + i);
    }
    executor.shutdown();
    executor.awaitTermination(5L, TimeUnit.MINUTES);
    long end = System.currentTimeMillis();
    System.out.println(end - start);
    System.out.println(TimeUtils.dateToString(new Date()));
  }

  static class MyRunnable implements Runnable {
    private final long countUntil;
    private final long number ;

    MyRunnable(long countUntil, long number) {
      this.countUntil = countUntil;
      this.number = number;
    }

    @Override
    public void run() {
      Dao dao = DBconnFactory.getInstance().getDao();
      for (long i = 0; i < countUntil; i++) {
        Sql sql = Sqls.fetchEntity("CALL updateModulePlanTimes(@appId,@moduleType)");
        sql.setEntity(dao.getEntity(App.class));
        sql.params().set("appId", 14).set("moduleType", 9);
        dao.execute(sql);
//        System.out.println(number + "\t" + i);
      }
    }
  }


  // @Test
  public void testDaoInsert() {
    Dao dao = DBconnFactory.getInstance().getDao();
    App app = new App();
    app.setAppCompany("nutzTest");
    app.setAppKey("1201611180001");
    app.setAppSecret("4064a75ce33f9ba74b59e348e9857f61");
    app.setAppState(1);
    app.setCreateTime(new Date());
    app.setLastupdatetime(new Date());
    app.setAppType(1);

    dao.insert(app);
  }

  // @Test
  public void testDaoFetch() {
    Dao dao = DBconnFactory.getInstance().getDao();
    App app = dao.fetch(App.class, 99);
    System.out.println(app);

    app = dao.fetch(App.class, Cnd.where("app_company", "=", "nutzTest"));
    System.out.println(app);
  }

}
