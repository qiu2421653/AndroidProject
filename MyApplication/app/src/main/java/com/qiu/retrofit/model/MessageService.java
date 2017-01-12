package com.qiu.retrofit.model;

import com.qiu.retrofit.entity.Message;
import com.qiu.retrofit.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author Qiu
 * @version V1.3
 * @Description:消息
 * @date 2017/1/6 14:09
 */
public interface MessageService {

	/**
	 * post 传递Json数据时，使用@Body;传递表单数据时，使用@Field
	 *
	 * @param user
	 * @return
	 */
	@POST("CM01.htm")
	Call<Message> reqMessages(@Body User user);
}
