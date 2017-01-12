package com.qiu.retrofit.model;

import com.qiu.retrofit.entity.Upgrade;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Qiu
 * @version V1.3
 * @Description:
 * @date 2017/1/6 13:29
 */
public interface UpgradService {

	@GET("version.json")
	Call<Upgrade> reqVersion();
}
