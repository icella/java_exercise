package exercise.com.leo.base.lang;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SplitTest {

  final String elements = "原姣姣\t13938186660\t410825199107147546\t2016-05-01 14:34:31\t10\t1\t03916566508\t新乡\t";
  
  @Before
  public void setUp() throws Exception {
    
  }

  @Test
  public void test() {
    String[] fields = elements.split("\t");
    System.out.println(fields.length);
    assertEquals(8, fields.length);
    
    fields = elements.split("\t", -1);
    System.out.println(fields.length);
    assertEquals(9, fields.length);
  }
}
