package com.ntanougat.orbslam2test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private Button datasetMode, cameraMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);

		// Example of a call to a native method
		datasetMode = (Button) findViewById(R.id.dataset_mode);
		cameraMode = (Button) findViewById(R.id.camera_mode);
		datasetMode.setOnClickListener(this);
		cameraMode.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.dataset_mode:
				startActivity(new Intent(MainActivity.this, DataSetModeActivity.class));
				break;
			case R.id.camera_mode:
				//Toast.makeText(MainActivity.this, "on the way...", Toast.LENGTH_LONG).show();
				startActivity(new Intent(MainActivity.this, CameraModeActivity.class));
				break;
		}
	}
}
