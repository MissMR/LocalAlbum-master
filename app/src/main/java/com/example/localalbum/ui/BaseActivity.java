package com.example.localalbum.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.localalbum.AppContext;
import com.example.localalbum.AppManager;


/**
 * @Description:Activity基类
 * @author linjizong
 * @date 2015-3-18
 */

/**
 * @Description:
 * @author linjizong
 * @date 2015-3-30
 */
public class BaseActivity extends AppCompatActivity {
    //应用是否销毁标志
	protected boolean isDestroy;
	//防止重复点击设置的标志，涉及到点击打开其他Activity时，将该标志设置为false，在onResume事件中设置为true
	private boolean clickable=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isDestroy=false;
		/*//设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//垂直显示
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);*/
		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		isDestroy=true;
		// 结束Activity&从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		//每次返回界面时，将点击标志设置为可点击
		clickable=true;
	}

	/**
	 * 当前是否可以点击
	 * @return
	 */
	protected boolean isClickable(){
		return  clickable;
	}

	/**
	 * 锁定点击
	 */
	protected void lockClick(){
		clickable=false;
	}



	/*@Override
	public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
		if(isClickable()) {
			lockClick();
			super.startActivityForResult(intent, requestCode,options);
		}
	}*/
}
