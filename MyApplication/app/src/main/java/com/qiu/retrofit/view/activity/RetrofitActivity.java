package com.qiu.retrofit.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.qiu.retrofit.R;
import com.qiu.retrofit.entity.Message;
import com.qiu.retrofit.entity.MovieEntity;
import com.qiu.retrofit.entity.Upgrade;
import com.qiu.retrofit.entity.User;
import com.qiu.retrofit.model.MessageService;
import com.qiu.retrofit.model.MovieService;
import com.qiu.retrofit.model.UpgradService;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;
import com.qiu.retrofit.view.adapter.BaseRecyclerAdapter;
import com.qiu.retrofit.view.holder.MainHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author @Qiu
 * @version V1.3
 * @Description:测试Retrofit 2.0
 * @date 2017/1/6 11:27
 */
public class RetrofitActivity extends BaseActivity {
	private static final String TAG = "MainActivity";


	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.swipe_target)
	RecyclerView swipeTarget;

	private BaseRecyclerAdapter adapter;

	private String getUrl = "https://api.douban.com/v2/movie/top250?start=0&count=10";

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_retrofit);
	}

	@Override
	public void initView() {
		setSupportActionBar(toolbar);
		swipeTarget.setLayoutManager(new LinearLayoutManager(mContext));
	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}

	@Override
	public void initData() {
		//TODO 获取数据
		reqMessage();
		reqAdapter();
	}

	/**
	 * Adapter设置
	 */
	private void reqAdapter() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add("string:" + i);
		}
		adapter = new BaseRecyclerAdapter(list, R.layout.item_main, MainHolder.class);
		swipeTarget.setAdapter(adapter);
	}


	/**
	 * 获取数据(测试1 ->Get)
	 */
	private void reqData1() {
		String baseUrl = "https://api.douban.com/v2/movie/";
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		MovieService movieService = retrofit.create(MovieService.class);
		Call<MovieEntity> call = movieService.getTopMovie(0, 10);
		//call.enqueue()异步请求，call.execute()同步请求
		call.enqueue(new Callback<MovieEntity>() {
			@Override
			public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
				Log.e(TAG, "response:" + response.body().getTitle().toString());
			}

			@Override
			public void onFailure(Call<MovieEntity> call, Throwable t) {
				Log.e(TAG, "t:" + t.getMessage().toString());
			}
		});
	}

	/**
	 * 获取版本信息
	 */
	private void reqVersion() {
		String url = "http://192.168.1.149:8080/mt-nio/";
		Retrofit retrofit = new Retrofit.Builder().
				baseUrl(url).
				addConverterFactory(GsonConverterFactory.create())
				.build();

		UpgradService service = retrofit.create(UpgradService.class);
		Call<Upgrade> call = service.reqVersion();
		call.enqueue(new Callback<Upgrade>() {
			@Override
			public void onResponse(Call<Upgrade> call, Response<Upgrade> response) {
				Log.e(TAG, "version:" + response.body().getResult().getAndroid().toString());
			}

			@Override
			public void onFailure(Call<Upgrade> call, Throwable t) {
			}
		});
	}

	/**
	 * 请求消息信息
	 */
	private void reqMessage() {
		String token = "608e7ae094bab07996a2fe274e8ce3670d55ec063ac7dd1c14e2b853c157d2d26b493ebc4e649f8c41092d21586cefd6668d4ec5c27cf9fbfe5f15896f00f7525b9f679527f7d6dd414f4839d8670a414fee936f34affb3f";
		String url = "http://192.168.1.149:8080/mt-nio/msgApi/";
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(url)
				.client(getOkHttpClient())//使用自己创建的OkHttp
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		MessageService service = retrofit.create(MessageService.class);
		Call<Message> call = service.reqMessages(new User(token));

		call.enqueue(new Callback<Message>() {
			@Override
			public void onResponse(Call<Message> call, Response<Message> response) {
				Log.e(TAG, "errorBody:" + response.code());
//				Log.e(TAG, "version:" + response.body().toString());
			}

			@Override
			public void onFailure(Call<Message> call, Throwable t) {

			}
		});
	}

	/**
	 * 过滤Header
	 *
	 * @return
	 */
	public OkHttpClient genericClient() {
		OkHttpClient httpClient = new OkHttpClient.Builder()
				.addNetworkInterceptor(new Interceptor() {
					@Override
					public okhttp3.Response intercept(Chain chain) throws IOException {
						Request request = chain.request()
								.newBuilder()
								.addHeader("application/json", "application/json; charset=UTF-8")
								.addHeader("Connection", "keep-alive")
								.build();
						return chain.proceed(request);
					}
				})
				.build();
		return httpClient;
	}


	/**
	 * 新建log拦截器
	 *
	 * @return
	 */
	private OkHttpClient getOkHttpClient() {
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
			@Override
			public void log(String message) {
				Log.e("Retrofit", "OkHttp====Message:" + message);
			}
		});
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		//定制OkHttp
		return new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
	}

}
