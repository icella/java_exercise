package exercise.com.leo.base.lang.exception;
public class TestException {  
    public TestException() {  
    }  
  
    boolean testEx() throws Exception {  
        boolean ret = true;  
        try {  
            ret = testEx1();  
        } catch (Exception e) {  
            System.out.println("testEx, catch exception");  
            ret = false;  
            throw e;  
        } finally {  
            System.out.println("testEx, finally; return value=" + ret);  
        }  
        
        return ret;  
    }  
  
    boolean testEx1() throws Exception {  
        boolean ret = true;  
        try {  
            ret = testEx2();  
            if (!ret) {  
                return false;  
            }  
            System.out.println("testEx1, at the end of try");  
            return ret;  
        } catch (Exception e) {  
            System.out.println("testEx1, catch exception");  
            ret = false;  
            throw e;  
        } finally {  
            System.out.println("testEx1, finally; return value=" + ret);  
        }  
    }  
  
    boolean testEx2() throws Exception {  
        boolean ret = true;  
        try {  
            int b = 12;  
            int c;  
            for (int i = 2; i >= -2; i--) {  
                c = b / i;  
                System.out.println("i=" + i);  
            }  
            return true;  
        } catch (Exception e) {  
            System.out.println("testEx2, catch exception");  
            ret = false;  
            throw e;  
        } finally {  
            System.out.println("testEx2, finally; return value=" + ret);  
        }  
    }  
    
    boolean testEx3() throws Exception {  
      boolean ret = true;  
      try {  
        String tempStr = "1,2,3";
        String[] spliter = tempStr.split(",");
        if(spliter[3].equals("3")){
          ret = true;
        }
        ret = false;
      } catch (Exception e) {  
          System.out.println("testEx, catch exception");  
//          ret = false;  
      }
      
      return ret;
  }  
    
    boolean testEx4() throws Exception{
      boolean ret = true;
      String tempStr = "1,2,3";
      String[] spliter = tempStr.split(",");
      
      if(ret){
        return spliter[3].equals("3");
      }
      
      return ret;
    }
  
    public static void main(String[] args) {  
        TestException testException1 = new TestException();  
        try {  
          boolean result = testException1.testEx4();
          System.out.println(result ? 1 : 0);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}  