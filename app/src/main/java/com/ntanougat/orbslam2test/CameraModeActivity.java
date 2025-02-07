package com.ntanougat.orbslam2test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CameraModeActivity extends AppCompatActivity implements OnClickListener {
	Button ChooseCalibration, ChooseVOC, finish;
	TextView CalibrationTxt, VOCPathText;

	private static final int REQUEST_CODE_2 = 2;
	private static final int REQUEST_CODE_3 = 3;
	private Intent fileChooserIntent;
	public static final String EXTRA_FILE_CHOOSER = "file_chooser";

//	private String VOCPath, TUMPath;
	private String VOCPath = "/storage/emulated/0/Android/data/com.ntanougat.orbslam2test/files/VOC/ORBvoc.txt";
	private String TUMPath = "/storage/emulated/0/Android/data/com.ntanougat.orbslam2test/files/Calibration/List.yaml";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_camera_mode);
		ChooseCalibration = (Button) findViewById(R.id.choose_calibration);
		ChooseVOC = (Button) findViewById(R.id.choose_voc);
		finish = (Button) findViewById(R.id.finish);
		finish.setOnClickListener(this);
		ChooseCalibration.setOnClickListener(this);
		ChooseVOC.setOnClickListener(this);
		CalibrationTxt = (TextView) findViewById(R.id.cal_path_txt);
		VOCPathText = (TextView) findViewById(R.id.voc_path_txt);

		fileChooserIntent = new Intent(this, FileChooserActivity.class);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.choose_calibration:
				if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
					startActivityForResult(fileChooserIntent, REQUEST_CODE_2);
				else
					Toast.makeText(CameraModeActivity.this, "can't find SDcard", Toast.LENGTH_LONG).show();
				break;
			case R.id.choose_voc:
				if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
					startActivityForResult(fileChooserIntent, REQUEST_CODE_3);
				else
					Toast.makeText(CameraModeActivity.this, "can't find SDcard", Toast.LENGTH_LONG).show();
				break;
			case R.id.finish:
				if (!TextUtils.isEmpty(TUMPath) && !TextUtils.isEmpty(VOCPath)) {
					Bundle bundle = new Bundle();
					bundle.putString("voc", VOCPath);
					bundle.putString("calibration", TUMPath);
					Intent intent = new Intent(CameraModeActivity.this, ORBSLAMForCameraActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(CameraModeActivity.this, "None of Calibration path or Vocabulary path can be empty!", Toast.LENGTH_LONG).show();
				}
				break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_CANCELED) {
			Toast.makeText(CameraModeActivity.this, "no return value", Toast.LENGTH_LONG).show();
			return;
		}
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_2) {

			TUMPath = data.getStringExtra(EXTRA_FILE_CHOOSER);
			CalibrationTxt.setText("calibration path is " + TUMPath);
			return;
		}
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_3) {

			VOCPath = data.getStringExtra(EXTRA_FILE_CHOOSER);
			VOCPathText.setText("Vocabulary path is " + VOCPath);
			return;
		}
	}
}
