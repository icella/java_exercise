package exercise.com.leo.base.lang;

public class EnumTest {

  public static void main(String[] args) {
   System.out.println(TAG_CATEGORY.COMMONCONTACTS.getName());  
  }
  
  enum TAG_CATEGORY {
    DIRECTCALL("directCall"),
    COMMONCONTACTS("commonContacts"),
    TAGS("tags"),
    INTERCOMMONS("interactCommons"),
    GRAYSCALE("grayscale");

    private String name;

    private TAG_CATEGORY(String name) {
        this.name = name;
    }

    public String getName() {
      return name;
    }

  }

}
