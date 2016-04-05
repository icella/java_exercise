package exercise.com.leo.base.math;

public class ContactInfo {
	private String phone;
	private String imsi;
	private String imei;

	public String getPhone() {
		return phone;
	}

	public ContactInfo setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getImsi() {
		return imsi;
	}

	public ContactInfo setImsi(String imsi) {
		this.imsi = imsi;
		return this;
	}

	public String getImei() {
		return imei;
	}

	public ContactInfo setImei(String imei) {
		this.imei = imei;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imei == null) ? 0 : imei.hashCode());
		result = prime * result + ((imsi == null) ? 0 : imsi.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactInfo other = (ContactInfo) obj;
		if (imei == null) {
			if (other.imei != null)
				return false;
		} else if (!imei.equals(other.imei))
			return false;
		if (imsi == null) {
			if (other.imsi != null)
				return false;
		} else if (!imsi.equals(other.imsi))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return phone;
	}
}
