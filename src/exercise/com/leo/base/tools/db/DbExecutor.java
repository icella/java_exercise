package exercise.com.leo.base.tools.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import exercise.com.leo.base.lang.io.AreaCode;
import exercise.com.leo.base.lang.io.CallDetail;

/**
 * 数据库常用操作
 * @author lla
 *
 */
public abstract class DbExecutor {
  
  //TODO 可以学习nutz拼接方法http://nutzam.github.io/nutz/
  abstract String buildInsertSql(String tbName, Map<String, String> fieldPairs);
  abstract String buildIsExistsSql(String tbName, Map<String, String> fieldPairs);
  abstract String buildUpdateSql(String tbName, Map<String, String> setPairs, Map<String, String> wherePairs);
  
  /**
   * 执行sql
   * @param tbName
   * @param fieldPairs
   */
  public void executeSql(String tbName, Map<String, String> setPairs,  Map<String, String> wherePairs, String type) {
    DruidPooledConnection conn = null;
    Statement stmt = null;

    try {
      DBconnFactory dbd = DBconnFactory.getInstance();
      conn = dbd.getDds().getConnection();
      stmt = conn.createStatement();
      
      String sql = "";
      if(type.equals("insert")){
        sql = buildInsertSql(tbName, setPairs);
      } else if(type.equals("update")){
        sql = buildUpdateSql(tbName, setPairs, wherePairs);
      }
      
      if(StringUtils.isNotEmpty(sql)){
        stmt.execute(sql);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBconnFactory.colseStmtQuietly(stmt);
      DBconnFactory.colseConnQuietly(conn);
    }
  }
  
  public List<CallDetail> getCallDetailAllRecords(String tbName, Map<String, String> fieldPairs) {
    DruidPooledConnection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      DBconnFactory dbd = DBconnFactory.getInstance();
      conn = dbd.getDds().getConnection();
      stmt = conn.createStatement();
      
      String sql = buildIsExistsSql(tbName, fieldPairs);
      
      rs = stmt.executeQuery(sql);
      
      List<CallDetail> list = Lists.newArrayList();
      if (rs.next()){
        CallDetail callDetai = new CallDetail();
        callDetai.setCid(rs.getInt(1)).setContact(rs.getString(7)).setContact_qc(rs.getString(9));
        list.add(callDetai);
      }
      
      return list;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBconnFactory.colseRsQuietly(rs);
      DBconnFactory.colseStmtQuietly(stmt);
      DBconnFactory.colseConnQuietly(conn);
    }
    
    return null;
  }  
  
  public List<AreaCode> getAreaCodeRecords(String tbName, Map<String, String> fieldPairs) {
    DruidPooledConnection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      DBconnFactory dbd = DBconnFactory.getInstance();
      conn = dbd.getDds().getConnection();
      stmt = conn.createStatement();
      
      String sql = buildIsExistsSql(tbName, fieldPairs);
      
      rs = stmt.executeQuery(sql);
      
      List<AreaCode> list = Lists.newArrayList();
      if (rs.next()){
        AreaCode areaCode = new AreaCode();
        areaCode.setAreaCode(rs.getString(1)).setCity(rs.getString(3)).setProvince(rs.getString(2));
        list.add(areaCode);
      }
      
      return list;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBconnFactory.colseRsQuietly(rs);
      DBconnFactory.colseStmtQuietly(stmt);
      DBconnFactory.colseConnQuietly(conn);
    }
    
    return null;
  }  
  
  
  /**
   * 查询sql
   * @param tbName
   * @param fieldPairs
   * @return
   */
  public boolean isRecordExists(String tbName, Map<String, String> fieldPairs) {
    DruidPooledConnection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      DBconnFactory dbd = DBconnFactory.getInstance();
      conn = dbd.getDds().getConnection();
      stmt = conn.createStatement();
      
      String sql = buildIsExistsSql(tbName, fieldPairs);
      
      rs = stmt.executeQuery(sql);
      
      if (rs.next())
        return true;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBconnFactory.colseRsQuietly(rs);
      DBconnFactory.colseStmtQuietly(stmt);
      DBconnFactory.colseConnQuietly(conn);
    }

    return false;
  }
  
  /**
   * 分别获取<key,value>，用逗号分割
   * @param fieldPairs
   * @return
   */
  Map<String, String> joinValueFieldPairs(Map<String, String> fieldPairs){
    StringBuilder fieldStr = new StringBuilder();
    StringBuilder valueStr= new StringBuilder();
    for (Entry<String, String> entry : fieldPairs.entrySet()) {
      fieldStr.append(entry.getKey()).append(", ");
      String value = entry.getValue();
      if(value != null && !value.equals("\"\""))
        value = "\"" + value + "\"";
      
      valueStr.append(value).append(", ");
    }
    
    Map<String, String> resultMap = Maps.newHashMap();
    String fieldsKey = fieldStr.toString();
    String fieldsValue = valueStr.toString();
    
    resultMap.put(fieldsKey.substring(0, fieldsKey.length() - 2), fieldsValue.substring(0, fieldsValue.length() - 2));
    
    return resultMap;
  }
  
  /**
   * 拼接等号连接的<key, value>
   * @param fieldPairs
   * @return
   */
  String joinEqualFieldPairs(Map<String, String> fieldPairs){
    StringBuilder fieldStr = new StringBuilder();
    for (Entry<String, String> entry : fieldPairs.entrySet()) {
      fieldStr.append(entry.getKey()).append(" = '").append(entry.getValue()).append("' ");
    }
    
    return fieldStr.toString();
  }
  
  String joinEqualFieldPairs2(Map<String, String> fieldPairs){
    StringBuilder fieldStr = new StringBuilder();
    for (Entry<String, String> entry : fieldPairs.entrySet()) {
      fieldStr.append(entry.getKey()).append(" = '").append(entry.getValue()).append("' ").append(", ");
    }
    
    String result = fieldStr.toString();
    return result.substring(0, result.length() - 2);
  }
}
