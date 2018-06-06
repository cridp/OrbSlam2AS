package com.ntanougat.orbslam2test;

import java.io.File;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;

import com.ntanougat.orbslam2test.FileChooserAdapter.FileInfo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


public class FileChooserActivity extends Activity {
	private GridView mGridView;
	private View mBackView;
	private View mBtExit,mBtOk;
	private TextView mTvPath ;
	
	private String mSdcardRootPath ;
	private String mLastFilePath ;
	
	private ArrayList<FileInfo> mFileLists  ;
	private FileChooserAdapter mAdatper ;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				  WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.filechooser_show);

		mSdcardRootPath = Environment.getExternalStorageDirectory().getAbsolutePath();

		mBackView = findViewById(R.id.imgBackFolder);
		mBackView.setOnClickListener(mClickListener);
		mBtExit = findViewById(R.id.btExit);
		mBtExit.setOnClickListener(mClickListener);
		mBtOk= findViewById(R.id.btOK);
		mBtOk.setOnClickListener(mClickListener);
		
		mTvPath = (TextView)findViewById(R.id.tvPath);
		
		mGridView = (GridView)findViewById(R.id.gvFileChooser);
		mGridView.setEmptyView(findViewById(R.id.tvEmptyHint));
		mGridView.setOnItemClickListener(mItemClickListener);
		setGridViewAdapter(mSdcardRootPath);
	}

	private void setGridViewAdapter(String filePath) {
		updateFileItems(filePath);
		mAdatper = new FileChooserAdapter(this , mFileLists);
		mGridView.setAdapter(mAdatper);
	}

	private void updateFileItems(String filePath) {
		mLastFilePath = filePath ;
		mTvPath.setText(mLastFilePath);
		
		if(mFileLists == null)
			mFileLists = new ArrayList<FileInfo>() ;
		if(!mFileLists.isEmpty())
			mFileLists.clear() ;
		
		File[] files = folderScan(filePath);
		if(files == null) 
			return ;
		
		for (int i = 0; i < files.length; i++) {
			if(files[i].isHidden())
				continue ;
			
			String fileAbsolutePath = files[i].getAbsolutePath() ;
			String fileName = files[i].getName();
		    boolean isDirectory = false ;
			if (files[i].isDirectory()){
				isDirectory = true ;
			}
		    FileInfo fileInfo = new FileInfo(fileAbsolutePath , fileName , isDirectory) ;
			mFileLists.add(fileInfo);
		}
		if(mAdatper != null)
		    mAdatper.notifyDataSetChanged();
	}

	private File[] folderScan(String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		return files;
	}
	
	private OnClickListener mClickListener = new  OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.imgBackFolder:
				backProcess();
				break;
			case R.id.btExit :
				setResult(RESULT_CANCELED);
				finish();
			    break ;
			case R.id.btOK:
				Intent intent = new Intent();
			    intent.putExtra(DataSetModeActivity.EXTRA_FILE_CHOOSER , mLastFilePath);
			    setResult(RESULT_OK , intent);
			    finish();
				break;
			}
		}
	};
	
	private OnItemClickListener mItemClickListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> adapterView, View view, int position,
				long id) {
			FileInfo fileInfo = (FileInfo)(((FileChooserAdapter)adapterView.getAdapter()).getItem(position));
			if(fileInfo.isDirectory())
				updateFileItems(fileInfo.getFilePath()) ;
			else{
				Intent intent = new Intent();
			    intent.putExtra(DataSetModeActivity.EXTRA_FILE_CHOOSER , fileInfo.getFilePath());
			    setResult(RESULT_OK , intent);
			    finish();
			}
		}
	};
    
	public boolean onKeyDown(int keyCode , KeyEvent event){
		if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode()
			== KeyEvent.KEYCODE_BACK){
			backProcess();   
			return true ;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void backProcess(){

		if (!mLastFilePath.equals(mSdcardRootPath)) {  
			File thisFile = new File(mLastFilePath);
			String parentFilePath = thisFile.getParent();
			updateFileItems(parentFilePath);
		} 
		else {
			setResult(RESULT_CANCELED);
			finish();
		}
	}
	private void toast(CharSequence hint){
	    Toast.makeText(this, hint , Toast.LENGTH_SHORT).show();
	}
}