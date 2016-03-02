package com.hyderabadwater.hmwssb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.hyderabadwater.hmwssb.R;

public class AccountVerified extends Activity {

	private int STOP_SPLASH = 0;
	private int SPLASH_TIME = 5000;
	String message;
	TextView textView1;

	private Handler Splash_Handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == STOP_SPLASH) {

				if(message.equals("1")){
					SharedPreferences.Editor sp = getSharedPreferences("nonconsumerregistered", MODE_PRIVATE).edit();
					sp.putString("registered", "success");
					sp.commit();
					textView1.setText("Your Account Details are Successfully Verified");
					startActivity(new Intent(AccountVerified.this,
							NonConsumerDashboard.class));
					AccountVerified.this.finish();

				}else if(message.equals("2")){
					
					SharedPreferences sharedPreferences = PreferenceManager
							.getDefaultSharedPreferences(getApplicationContext());

					SharedPreferences.Editor editor = sharedPreferences
							.edit();
					editor.putString("accountverfied", "accountverfied");
					editor.commit();
					
					textView1.setText("Your HMWSSB Account Details Verified");
					startActivity(new Intent(AccountVerified.this,
							TankerGreviance.class));
					AccountVerified.this.finish();

				}

				
			}
			super.handleMessage(msg);
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.account_verified);
		
		textView1= (TextView) findViewById(R.id.textView1);
		message = getIntent().getExtras().getString("nconsumer");

		if(message.equals("1")){
			textView1.setText("Your Account Details are Successfully Verified");
		}else if(message.equals("2")){
			textView1.setText("Your HMWSSB Account Details Verified");
		}
		
		Message msg = new Message();
		msg.what = STOP_SPLASH;
		
		Splash_Handler.sendMessageDelayed(msg, SPLASH_TIME);
	}

}