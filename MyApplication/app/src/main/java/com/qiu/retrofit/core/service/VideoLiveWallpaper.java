package com.qiu.retrofit.core.service;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

import com.qiu.retrofit.core.log.CLog;

import java.io.IOException;


/**
 * @author hongyang
 * @version V1.0
 * @Description:视频桌面 https://github.com/songixan/Wallpaper
 * @date 2017/5/31 10:47
 */
public class VideoLiveWallpaper extends WallpaperService {


	private static final String TAG = "VideoLiveWallpaper";

	public Engine onCreateEngine() {
		return new VideoEngine();
	}

	public static final String VIDEO_PARAMS_CONTROL_ACTION = "com.zhy.livewallpaper";
	public static final String KEY_ACTION = "action";
	public static final int ACTION_VOICE_SILENCE = 110;
	public static final int ACTION_VOICE_NORMAL = 111;

	public static void voiceSilence(Context context) {
		Intent intent = new Intent(VideoLiveWallpaper.VIDEO_PARAMS_CONTROL_ACTION);
		intent.putExtra(VideoLiveWallpaper.KEY_ACTION, VideoLiveWallpaper.ACTION_VOICE_SILENCE);
		context.sendBroadcast(intent);
	}

	public static void voiceNormal(Context context) {
		Intent intent = new Intent(VideoLiveWallpaper.VIDEO_PARAMS_CONTROL_ACTION);
		intent.putExtra(VideoLiveWallpaper.KEY_ACTION, VideoLiveWallpaper.ACTION_VOICE_NORMAL);
		context.sendBroadcast(intent);
	}

	public static void setToWallPaper(Context context) {
		final Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
		intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
				new ComponentName(context, VideoLiveWallpaper.class));
		context.startActivity(intent);
	}


	class VideoEngine extends Engine {

		private MediaPlayer mMediaPlayer;

		private BroadcastReceiver mVideoParamsControlReceiver;

		@Override
		public void onCreate(SurfaceHolder surfaceHolder) {
			super.onCreate(surfaceHolder);
			CLog.e(TAG, "VideoEngine#onCreate");

			IntentFilter intentFilter = new IntentFilter(VIDEO_PARAMS_CONTROL_ACTION);
			registerReceiver(mVideoParamsControlReceiver = new BroadcastReceiver() {
				@Override
				public void onReceive(Context context, Intent intent) {
					CLog.e(TAG, "onReceive");
					int action = intent.getIntExtra(KEY_ACTION, -1);

					switch (action) {
						case ACTION_VOICE_NORMAL:
							mMediaPlayer.setVolume(1.0f, 1.0f);
							break;
						case ACTION_VOICE_SILENCE:
							mMediaPlayer.setVolume(0, 0);
							break;

					}
				}
			}, intentFilter);


		}

		@Override
		public void onDestroy() {
			CLog.e(TAG, "VideoEngine#onDestroy");
			unregisterReceiver(mVideoParamsControlReceiver);
			super.onDestroy();

		}

		@Override
		public void onVisibilityChanged(boolean visible) {
			CLog.e(TAG, "VideoEngine#onVisibilityChanged visible = " + visible);
			if (visible) {
				mMediaPlayer.start();
			} else {
				mMediaPlayer.pause();
			}
		}


		@Override
		public void onSurfaceCreated(SurfaceHolder holder) {
			CLog.e(TAG, "VideoEngine#onSurfaceCreated ");
			super.onSurfaceCreated(holder);
			mMediaPlayer = new MediaPlayer();
			mMediaPlayer.setSurface(holder.getSurface());
			try {
				AssetManager assetMg = getApplicationContext().getAssets();
				AssetFileDescriptor fileDescriptor = assetMg.openFd("test1.mp4");
				mMediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),
						fileDescriptor.getStartOffset(), fileDescriptor.getLength());
				mMediaPlayer.setLooping(true);
				mMediaPlayer.setVolume(0, 0);
				mMediaPlayer.prepare();
				mMediaPlayer.start();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		@Override
		public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			CLog.e(TAG, "VideoEngine#onSurfaceChanged ");
			super.onSurfaceChanged(holder, format, width, height);
		}

		@Override
		public void onSurfaceDestroyed(SurfaceHolder holder) {
			CLog.e(TAG, "VideoEngine#onSurfaceDestroyed ");
			super.onSurfaceDestroyed(holder);
			mMediaPlayer.release();
			mMediaPlayer = null;

		}
	}

}  