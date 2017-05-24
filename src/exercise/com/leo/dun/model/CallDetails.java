package exercise.com.leo.dun.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("tb_call_details")
public class CallDetails {

  @Id
  @Column("cid")
  private int cid;
  
  @Column("pid")
  private int pid;
  
  @Column("call_start_time")
  private String callStartTime;
  
  @Column("duration")
  private int duration;
  
  @Column("called_type")
  private String calledType;
  
  @Column("called_type_new")
  private int calledTypeNew;
  
  @Column("contact")
  private String contact;
  
  @Column("native_call_addr")
  private String nativeCallAddr;
  
  @Column("contact_qc")
  private String contactQc;
  
  @Column("call_type")
  private String callType;
  
  @Column("cost")
  private double cost;
  
  @Column("exists_dun_db")
  private int existsDunDB;
  
  @Column("adid")
  private int adid;
  
  @Column("dun_org_name")
  private String dunOrgName;
  
  @Column("dun_org_type")
  private String dunOrgType;
  
  @Column("dun_number_source")
  private int dunNumberSource;
  
  @Column("call_user_amount")
  private int callUserAmount;
  
  @Column("create_time")
  private Date createTime;
  
  @Column("call_good_user_amount")
  private int callGoodUserAmount;
  
  @Column("call_bad_user_amount")
  private int callBadUserAmount;
  
  @Column("call_bad_user_weight")
  private double callBadUserWeith;

  public int getCid() {
    return cid;
  }

  public int getPid() {
    return pid;
  }

  public String getCallStartTime() {
    return callStartTime;
  }

  public int getDuration() {
    return duration;
  }

  public String getCalledType() {
    return calledType;
  }

  public int getCalledTypeNew() {
    return calledTypeNew;
  }

  public String getContact() {
    return contact;
  }

  public String getNativeCallAddr() {
    return nativeCallAddr;
  }

  public String getContactQc() {
    return contactQc;
  }

  public String getCallType() {
    return callType;
  }

  public double getCost() {
    return cost;
  }

  public int getExistsDunDB() {
    return existsDunDB;
  }

  public int getAdid() {
    return adid;
  }

  public String getDunOrgName() {
    return dunOrgName;
  }

  public String getDunOrgType() {
    return dunOrgType;
  }

  public int getDunNumberSource() {
    return dunNumberSource;
  }

  public int getCallUserAmount() {
    return callUserAmount;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public int getCallGoodUserAmount() {
    return callGoodUserAmount;
  }

  public int getCallBadUserAmount() {
    return callBadUserAmount;
  }

  public double getCallBadUserWeith() {
    return callBadUserWeith;
  }

  public void setCid(int cid) {
    this.cid = cid;
  }

  public void setPid(int pid) {
    this.pid = pid;
  }

  public void setCallStartTime(String callStartTime) {
    this.callStartTime = callStartTime;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public void setCalledType(String calledType) {
    this.calledType = calledType;
  }

  public void setCalledTypeNew(int calledTypeNew) {
    this.calledTypeNew = calledTypeNew;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public void setNativeCallAddr(String nativeCallAddr) {
    this.nativeCallAddr = nativeCallAddr;
  }

  public void setContactQc(String contactQc) {
    this.contactQc = contactQc;
  }

  public void setCallType(String callType) {
    this.callType = callType;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  public void setExistsDunDB(int existsDunDB) {
    this.existsDunDB = existsDunDB;
  }

  public void setAdid(int adid) {
    this.adid = adid;
  }

  public void setDunOrgName(String dunOrgName) {
    this.dunOrgName = dunOrgName;
  }

  public void setDunOrgType(String dunOrgType) {
    this.dunOrgType = dunOrgType;
  }

  public void setDunNumberSource(int dunNumberSource) {
    this.dunNumberSource = dunNumberSource;
  }

  public void setCallUserAmount(int callUserAmount) {
    this.callUserAmount = callUserAmount;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public void setCallGoodUserAmount(int callGoodUserAmount) {
    this.callGoodUserAmount = callGoodUserAmount;
  }

  public void setCallBadUserAmount(int callBadUserAmount) {
    this.callBadUserAmount = callBadUserAmount;
  }

  public void setCallBadUserWeith(double callBadUserWeith) {
    this.callBadUserWeith = callBadUserWeith;
  }

  @Override
  public String toString() {
    return "CallDetails [cid=" + cid + ", pid=" + pid + ", callStartTime=" + callStartTime
        + ", duration=" + duration + ", calledType=" + calledType + ", calledTypeNew="
        + calledTypeNew + ", contact=" + contact + ", nativeCallAddr=" + nativeCallAddr
        + ", contactQc=" + contactQc + ", callType=" + callType + ", cost=" + cost
        + ", existsDunDB=" + existsDunDB + ", adid=" + adid + ", dunOrgName=" + dunOrgName
        + ", dunOrgType=" + dunOrgType + ", dunNumberSource=" + dunNumberSource
        + ", callUserAmount=" + callUserAmount + ", createTime=" + createTime
        + ", callGoodUserAmount=" + callGoodUserAmount + ", callBadUserAmount=" + callBadUserAmount
        + ", callBadUserWeith=" + callBadUserWeith + "]";
  }
  
}
