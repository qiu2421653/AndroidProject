package com.qiu.retrofit.model;

import com.qiu.retrofit.entity.MovieEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Qiu
 * @version V1.3
 * @Description:
 * @date 2017/1/6 11:43
 */
public interface MovieService {

	@GET("top250")
	Call<MovieEntity> getTopMovie(
			@Query("start")
			int a,
			@Query("count")
			int b
	);
}
