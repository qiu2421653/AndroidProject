package com.qiu.retrofit.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Property;

/**
 * @author Qiu
 * @version V1.3
 * @Description:
 * @date 2017/1/6 15:01
 */
@Keep
@Entity(nameInDb = "user")
public class User {

	@Property(nameInDb = "userToken")
	public String userToken;

	public User(String userToken) {
		this.userToken = userToken;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
}
