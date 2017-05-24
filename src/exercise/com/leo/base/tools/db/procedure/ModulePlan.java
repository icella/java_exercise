package exercise.com.leo.base.tools.db.procedure;

import java.io.Serializable;
import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;


@Table("tb_module_plan")
public class ModulePlan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column("plan_id")
	private Integer planId;

	@Column("app_id")
	private Integer appId;

	@Column("module_type")
	private Integer moduleType;

	@Column("plan_type")
	private Integer planType;

	@Column("plan_state")
	private Integer planState;

	@Column("access_limit")
	private Integer accessLimit;

	@Column("access_limit_daily")
	private Integer accessLimitDaily;

	@Column("plan_access_times")
	private Integer planAccessTimes;

	@Column("plan_access_times_daily")
	private Integer planAccessTimesDaily;

	@Column("create_time")
	private Date createTime;

	@Column("expire_time")
	private Date expireTime;

	@Column("last_update_time")
	private Date lastUpdateTime;
	
	@Column("youdun_imsi_used")
	private boolean youdunImsiUsed;

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getModuleType() {
		return moduleType;
	}

	public void setModuleType(Integer moduleType) {
		this.moduleType = moduleType;
	}

	public Integer getPlanType() {
		return planType;
	}

	public void setPlanType(Integer planType) {
		this.planType = planType;
	}

	public Integer getPlanState() {
		return planState;
	}

	public void setPlanState(Integer planState) {
		this.planState = planState;
	}

	public Integer getAccessLimit() {
		return accessLimit;
	}

	public void setAccessLimit(Integer accessLimit) {
		this.accessLimit = accessLimit;
	}

	public Integer getAccessLimitDaily() {
		return accessLimitDaily;
	}

	public void setAccessLimitDaily(Integer accessLimitDaily) {
		this.accessLimitDaily = accessLimitDaily;
	}

	public Integer getPlanAccessTimes() {
		return planAccessTimes;
	}

	public void setPlanAccessTimes(Integer planAccessTimes) {
		this.planAccessTimes = planAccessTimes;
	}

	public Integer getPlanAccessTimesDaily() {
		return planAccessTimesDaily;
	}

	public void setPlanAccessTimesDaily(Integer planAccessTimesDaily) {
		this.planAccessTimesDaily = planAccessTimesDaily;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public boolean isYoudunImsiUsed() {
		return youdunImsiUsed;
	}

	public void setYoudunImsiUsed(boolean youdunImsiUsed) {
		this.youdunImsiUsed = youdunImsiUsed;
	}

  @Override
  public String toString() {
    return "ModulePlan [planId=" + planId + ", appId=" + appId + ", moduleType=" + moduleType
        + ", planType=" + planType + ", planState=" + planState + ", accessLimit=" + accessLimit
        + ", accessLimitDaily=" + accessLimitDaily + ", planAccessTimes=" + planAccessTimes
        + ", planAccessTimesDaily=" + planAccessTimesDaily + ", createTime=" + createTime
        + ", expireTime=" + expireTime + ", lastUpdateTime=" + lastUpdateTime + ", youdunImsiUsed="
        + youdunImsiUsed + "]";
  }
}
