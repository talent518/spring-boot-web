package com.talent518.demo.entity2;

import java.util.Date;

public class Profile {
	public static enum SexType {
		Boy("B"), Girl("G"), None("N"),
		B("B"), G("G"), N("N");
		private String value;

		private SexType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return value;
		}
	};

	private int uid;
	private String realName;
	private SexType sex;
	private Date birthday;
	private String address;

	public int getId() {
		return uid;
	}

	public void setId(int uid) {
		this.uid = uid;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public SexType getSex() {
		return sex;
	}

	public void setSex(SexType sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Profile{uid: " + uid + ", realName: " + realName + ", sex: " + sex + ", birthday: " + birthday + ", address: " + address + "}";
	}

}
