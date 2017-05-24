package exercise.com.leo.dun.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("tb_phone_segment_info")
public class PhoneSegmentInfo {
	
	@Name
	@Column("phone_segment")
	private String phoneSegment;
	@Column("province")
	private String province;
	@Column("city")
	private String city;
	@Column("operator")
	private String operator;
	@Column("operator_code")
	private String operatorCode;
	@Column("area_code")
	private String areaCode;
	@Column("zip_code")
	private String zipCode;

	public String getPhoneSegment() {
		return phoneSegment;
	}

	public void setPhoneSegment(String phoneSegment) {
		this.phoneSegment = phoneSegment;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
