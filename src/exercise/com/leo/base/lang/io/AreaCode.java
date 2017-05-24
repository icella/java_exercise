package exercise.com.leo.base.lang.io;

public class AreaCode {
  private String areaCode;
  private String province;
  private String city;
  public String getAreaCode() {
    return areaCode;
  }
  public AreaCode setAreaCode(String areaCode) {
    this.areaCode = areaCode;
    return this;
  }
  public String getProvince() {
    return province;
  }
  public AreaCode setProvince(String province) {
    this.province = province;
    return this;
  }
  public String getCity() {
    return city;
  }
  public AreaCode setCity(String city) {
    this.city = city;
    return this;
  }
  
}
