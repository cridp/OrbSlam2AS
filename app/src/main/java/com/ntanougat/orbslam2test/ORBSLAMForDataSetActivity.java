package com.ntanougat.orbslam2test;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ntanougat.orbslam2test.ndkHelper.OrbNdkHelper;

import java.io.File;
import java.util.Arrays;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ORBSLAMForDataSetActivity extends AppCompatActivity implements OnClickListener,
		Renderer {
	ImageView imgSource, imgDealed;
	TextView dataTextView;
	Button start, stop;
	String vocPath, calibrationPath, ImgPath;
	LinearLayout linear;
	public double timestamp;
	public double timestampOld;
	public double timeStep;

	private static final int INIT_FINISHED = 0x00010001;

	private AssetManager mAssetMgr = null;

	private final int CONTEXT_CLIENT_VERSION = 3;
	private GLSurfaceView mGLSurfaceView;

	static {
		System.loadLibrary("ORB_SLAM2_EXCUTOR");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_dataset_orb);
		dataTextView = (TextView) findViewById(R.id.dataTextView);
		imgSource = (ImageView) findViewById(R.id.img_origin);
		imgDealed = (ImageView) findViewById(R.id.img_dealed);
		start = (Button) findViewById(R.id.start);
		stop = (Button) findViewById(R.id.stop);
		start.setOnClickListener(this);
		stop.setOnClickListener(this);
		vocPath = getIntent().getStringExtra("voc");
		calibrationPath = getIntent().getStringExtra("calibration");
		ImgPath = getIntent().getStringExtra("images");

		mGLSurfaceView = new GLSurfaceView(this);
		linear = (LinearLayout) findViewById(R.id.surfaceLinear);

		//mGLSurfaceView.setEGLContextClientVersion(CONTEXT_CLIENT_VERSION);
		mGLSurfaceView.setRenderer(this);
		linear.addView(mGLSurfaceView, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));
		if (TextUtils.isEmpty(vocPath) || TextUtils.isEmpty(calibrationPath)) {
			Toast.makeText(this, "null param,return!", Toast.LENGTH_LONG)
				 .show();
			finish();
		} else {
			Toast.makeText(ORBSLAMForDataSetActivity.this, "init has been started!",
					Toast.LENGTH_LONG).show();
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					OrbNdkHelper.initSystemWithParameters(vocPath,
							calibrationPath);
					Log.e("information==========>",
							"init has been finished!");
					myHandler.sendEmptyMessage(INIT_FINISHED);
				}
			}).start();
		}
	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case INIT_FINISHED:
					Toast.makeText(ORBSLAMForDataSetActivity.this,
							"init has been finished!",
							Toast.LENGTH_LONG).show();
					new Thread(new Runnable() {

						@Override
						public void run() {
							if (!TextUtils.isEmpty(ImgPath)) {
								File dir = new File(ImgPath);
								File[] files = dir.listFiles();
								Arrays.sort(files);
								if (dir.isDirectory()) {
									for (File file : files) {
										tmp = BitmapFactory.decodeFile(file
												.getAbsolutePath());
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												// TODO Auto-generated method stub
												imgSource.setImageBitmap(tmp);
											}
										});
										timestampOld = timestamp;
										timestamp = Double.parseDouble(file.getName()
																		   .substring(0,
																				   file.getName().length() - 5));
										timeStep = timestamp - timestampOld;
										// TODO Auto-generated method stub
										int w = tmp.getWidth(), h = tmp.getHeight();
										int[] pix = new int[w * h];
										tmp.getPixels(pix, 0, w, 0, 0, w, h);

										int[] resultInt = OrbNdkHelper.startCurrentORB(
												timestamp, pix, w, h);
										resultImg = Bitmap.createBitmap(w, h, Config.RGB_565);
										resultImg.setPixels(resultInt, 0, w, 0, 0, w, h);

										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												// TODO Auto-generated method stub
												imgDealed.setImageBitmap(resultImg);
												dataTextView.setText("Time Step: " + timeStep + "");
											}
										});
									}
								}
							} else {
								Toast.makeText(ORBSLAMForDataSetActivity.this, "empty images",
										Toast.LENGTH_LONG).show();
							}
						}
					}).start();
					break;
			}
			super.handleMessage(msg);
		}
	};

	private Bitmap tmp, resultImg;
	//private double timestamp;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.start:
			case R.id.stop:
				break;
		}
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		//OrbNdkHelper.readShaderFile(mAssetMgr);
		OrbNdkHelper.glesInit();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		OrbNdkHelper.glesResize(width, height);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		OrbNdkHelper.glesRender();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mGLSurfaceView.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mGLSurfaceView.onPause();
	}

	private boolean detectOpenGLES30() {
		ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		ConfigurationInfo info = am.getDeviceConfigurationInfo();

		return (info.reqGlEsVersion >= 0x30000);
	}
}
