/**
 * @(#)App.java
 *
 * @author huawei
 * @version 1.0 2015-5-14
 *
 *          Copyright (C) 2012,2015 , PING' AN, Inc.
 */
package exercise.com.leo.base.tools.db.procedure;

import java.io.Serializable;
import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 
 * Purpose:
 * 
 * @see
 * @since 1.1.0
 */
@Table("tb_app_copy")
public class App implements Serializable {
  public static final Integer APP_STATE_VALID = Integer.valueOf(1);// 有效
  public static final Integer APP_STATE_NOT_AVAIL = Integer.valueOf(0);// 无效
  public static final Integer APP_TYPE_THIRD_ORG = Integer.valueOf(0); // 第三方征信机构
  public static final Integer APP_TYPE_ORG = Integer.valueOf(1);// 金融机构

  public static final String APP_STATE_VALID_STR = "有效";
  public static final String APP_STATE_NOT_AVAIL_STR = "无效";
  public static final String APP_TYPE_THIRD_ORG_STR = "第三方征信机构";
  public static final String APP_TYPE_ORG_STR = "金融机构";

  @Id
  private Integer app_Id;
  
  @Column("app_company")
  private String appCompany;
  
  @Column("app_key")
  private String appKey;
  
  @Column("app_secret")
  private String appSecret;
  
  @Column("app_auth")
  private String appAuth;
  
  @Column("app_state")
  private Integer appState;
  
  @Column("create_time")
  private Date createTime;
  
  @Column("lastupdatetime")
  private Date lastupdatetime;
  
  @Column("app_type")
  private Integer appType;
  
  @Column("limit_times")
  private Integer limitTimes;
  
  @Column("limit_unit")
  private Integer limitUnit;

  public String getAppAuth() {
    return appAuth;
  }

  public void setAppAuth(String appAuth) {
    this.appAuth = appAuth;
  }

  public String getAppCompany() {
    return appCompany;
  }

  public void setAppCompany(String appCompany) {
    this.appCompany = appCompany;
  }
  public Integer getApp_Id() {
    return app_Id;
  }

  public void setApp_Id(Integer app_Id) {
    this.app_Id = app_Id;
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public String getAppSecret() {
    return appSecret;
  }

  public void setAppSecret(String appSecret) {
    this.appSecret = appSecret;
  }

  public Integer getAppState() {
    return appState;
  }

  public void setAppState(Integer appState) {
    this.appState = appState;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getLastupdatetime() {
    return lastupdatetime;
  }

  public void setLastupdatetime(Date lastupdatetime) {
    this.lastupdatetime = lastupdatetime;
  }

  public Integer getAppType() {
    return appType;
  }

  public void setAppType(Integer appType) {
    this.appType = appType;
  }

  public Integer getLimitTimes() {
    return limitTimes;
  }

  public void setLimitTimes(Integer limitTimes) {
    this.limitTimes = limitTimes;
  }

  public Integer getLimitUnit() {
    return limitUnit;
  }

  public void setLimitUnit(Integer limitUnit) {
    this.limitUnit = limitUnit;
  }

  @Override
  public String toString() {
    return "App [appId=" + app_Id + ", appCompany=" + appCompany + ", appKey=" + appKey
        + ", appSecret=" + appSecret + ", appAuth=" + appAuth + ", appState=" + appState
        + ", createTime=" + createTime + ", lastupdatetime=" + lastupdatetime + ", appType="
        + appType + ", limitTimes=" + limitTimes + ", limitUnit=" + limitUnit + "]";
  }
}


/**
 * $Log: App.java,v $
 * 
 * @version 1.0 2015-5-14
 *
 */
