package exercise.com.leo.base.tools.db;

import java.util.Map;
import java.util.Map.Entry;

public class TbDunTelExecute extends DbExecutor {
  @Override
  public String buildInsertSql(String tbName, Map<String, String> fieldPairs) {
    String sql1 = "INSERT INTO " + tbName + " (";
    String sql2 = ") VALUES(";
    String sql3 = ")";

    String resultSql = "";
    for (Entry<String, String> entry : joinValueFieldPairs(fieldPairs).entrySet()) {
      resultSql = sql1 + entry.getKey() + sql2 + entry.getValue() + sql3;
    }

    return resultSql;
  }

  @Override
  String buildIsExistsSql(String tbName, Map<String, String> fieldPairs) {
    String sql = "SELECT * FROM " + tbName + " where ";

    return sql + joinEqualFieldPairs(fieldPairs) + "limit 1000";
  }

  @Override
  String buildUpdateSql(String tbName, Map<String, String> setPairs,
      Map<String, String> wherePairs) {
    // update db_grayscale.tb_call_details set contact_qc = '' where contact = '';
    String sql1 = "UPDATE " + tbName + " SET ";
    String sql2 = " WHERE ";

    String resultSql = sql1 + joinEqualFieldPairs2(setPairs) + sql2 + joinEqualFieldPairs(wherePairs);
    

    return resultSql;
  }
}
