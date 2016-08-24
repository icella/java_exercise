package exercise.com.leo.base.db.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.junit.After;
import org.junit.Test;

public class H2ConnTest {
	private static String JDBC_URL = "";
	
	private static final String USER = "gacl";
	private static final String PASSWORD = "123";
	private static final String DRIVER_CLASS = "org.h2.Driver";
	
	Connection conn ;
	Statement stmt;
	
	@After
	public void after()throws Exception{
		stmt.close();
		conn.close();
	}
	
	/**
	 * 以嵌入式(本地)连接方式连接H2数据库
	 * <p>这种连接方式默认情况下只允许有一个客户端连接到H2数据库，有客户端连接到H2数据库之后，此时数据库文件就会被锁定，那么其他客户端就无法再连接了。
	 * <p>连接语法：jdbc:h2:[file:][<path>]<databaseName>
	 * <p>例如：
	 * <p>jdbc:h2:~/test //连接位于用户目录下的test数据库
	 * <p>jdbc:h2:file:/data/sample
	 * <p>jdbc:h2:file:D:/Program Files/h2/manualConfig(Windows only)
	 * <p>
	 * 需要说明一下使用这种"jdbc:h2:D:/Program Files/h2/manualConfig"这种方式连接H2数据库容易遇到的问题，如果已经在H2的WebConsole控制台中登录manualConfig数据库
	 * <p>此时manualConfig数据库就会被锁定，此时通过java代码连接manualConfig数据库时就会出现错误
	 * <p>引起这个错误的原因是因为manualConfig数据库对应的文件已经被锁定了，所以java代码这边无法再访问，为了能够让Java代码能够正常访问，必须把WebConsole控制台那边的连接先断开，
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
//	@Test
	public void test1() throws SQLException, ClassNotFoundException{
		//数据库连接URL，当前连接的是D:/Program Files/h2目录下的manualConfig数据库
		JDBC_URL = "jdbc:h2:D:/Program Files/h2/manualConfig";
		
		init();
		dropTable();
		createTable();
		insertRecords();
		query();
	}
	
	/**
	 * 使用TCP/IP的服务器模式(远程连接)方式连接H2数据库(推荐)
	 * <p>这种连接方式就和其他数据库类似了，是基于Service的形式进行连接的，因此允许多个客户端同时连接到H2数据库
	 * <p>连接语法：jdbc:h2:tcp://<server>[:<port>]/[<path>]<databaseName>
	 * <p>范例：jdbc:h2:tcp://localhost/~/test
	 * <p>注意：如果使用H2数据库的内存模式，那么我们创建的数据库和表都只是保存在内存中，一旦服务器重启，那么内存中的数据库和表就不存在了。
	 * @throws Exception
	 */
//	@Test
	public void test2() throws Exception {
		//数据库连接URL，通过使用TCP/IP的服务器模式（远程连接），当前连接的是D:/Program Files/h2目录下的manualConfig数据库
		JDBC_URL = "jdbc:h2:tcp://localhost/D:/Program Files/h2/manualConfig";
		
		init();
		dropTable();
		createTable();
		insertRecords();
		query();
	}
	
	/**
	 * H2数据库的内存模式
	 * H2数据库被称为内存数据库，因为它支持在内存中创建数据库和表
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void test3() throws SQLException, ClassNotFoundException{
		//数据库连接URL，通过使用TCP/IP的服务器模式（远程连接），当前连接的是内存里面的gacl数据库
		JDBC_URL = "jdbc:h2:tcp://localhost/mem:manualConfig";
		
		init();
		dropTable();
		createTable();
		insertRecords();
		query();
	}
	
	private void init() throws ClassNotFoundException, SQLException{
		Class.forName(DRIVER_CLASS);
		conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
		stmt = conn.createStatement();
	}
	
	private void dropTable() throws SQLException{
		stmt.execute("DROP TABLE IF EXISTS USER_INFO");
	}
	
	private void createTable() throws SQLException{
		stmt.execute("CREATE TABLE USER_INFO(id VARCHAR(36) PRIMARY KEY,name VARCHAR(100),sex VARCHAR(4))");
	}
	
	private void insertRecords() throws SQLException{
		stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','大日如来','男')");
		stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','青龙','男')");
		stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','白虎','男')");
		stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','朱雀','女')");
		stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','玄武','男')");
		stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','苍狼','男')");
	}
	
	private void query() throws SQLException{
		ResultSet rs = stmt.executeQuery("SELECT * FROM USER_INFO");
		while (rs.next()) {
			System.out.println(rs.getString("id") + "," + rs.getString("name") + "," + rs.getString("sex"));
		}
	}

}
