package exercise.com.leo.base.lang.io;

public class CallDetail {
  private int cid;
  private String contact;
  private String contact_qc;
  public int getCid() {
    return cid;
  }
  public CallDetail setCid(int cid) {
    this.cid = cid;
    return this;
  }
  public String getContact() {
    return contact;
  }
  public CallDetail setContact(String contact) {
    this.contact = contact;
    return this;
  }
  public String getContact_qc() {
    return contact_qc;
  }
  public CallDetail setContact_qc(String contact_qc) {
    this.contact_qc = contact_qc;
    return this;
  }
  
}
