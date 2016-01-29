package exercise.com.leo.base.lang;

import exercise.com.leo.base.lang.comparator.Student;

public class HexAddOneTest {

	public static void main(String[] args) {
		System.out.println(Student.class.getSimpleName());
		
		String str  = "123fFF";
		System.out.println(hexAddOne(str));
	}

    private static String hexAddOne(String hexStr)
    {
    	String str = hexStr.trim().replace(" ", "").toUpperCase();
    	char newStr = ' ';
    	
        int index = 0;
        for (int i = str.length() - 1; i > 0; i--)
        {
            if (str.charAt(i) == (char)57)
            {
                newStr = 'A';
                index = i;
                break;
            }
            else if (str.charAt(i) != (char)70)
            {
            	char dua = str.substring(i, i +1).toCharArray()[0];
            	byte b = (byte) dua;
            	int intValue = b + 1;
                newStr = (char) intValue;
                index = i;
                break;
            }
        }

        return str.substring(0, index) + newStr + str.substring(index+1).replace("F","0");
    }

}
