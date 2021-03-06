package com.example.helloworld;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{
	
	Button LoginButton;
	Button GoToGridView;
	Button StartThread;
	Button StartHttp;
	EditText Username;
	EditText Password;
	Validatator Val;
	MyThread thr;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        Val = new Validatator();
	        setContentView(R.layout.login_activity);
	        LoginButton = (Button)findViewById(R.id.ButtonLogin);
	        LoginButton.setOnClickListener(this);
	        GoToGridView = (Button)findViewById(R.id.ButtonGoGridView);
	        GoToGridView.setOnClickListener(this);
	        StartThread = (Button)findViewById(R.id.button_start_thread);
	        StartThread.setOnClickListener(this);
	        StartHttp = (Button)findViewById(R.id.button_start_Http_request);
	        StartHttp.setOnClickListener(this);
	        Username = (EditText)findViewById(R.id.username);
	        Password = (EditText)findViewById(R.id.password);
	        Val.user = new String("u");
	        Val.password = new String("q");
	        thr = new MyThread(LoginButton);
	        thr.start();
	    }
	 
	 private static class Validatator
	 {
		 public String user,password;
		 public boolean IsValidCridantials(String name,String pass)
		 {
			 if(user.equals(name) && password.equals(pass))
			 {
				 return true;
			 }
			 return false;
		 }
	 }

	@Override
	public void onClick(View v) {
		
		if(v.getId() == LoginButton.getId())
		{
			if(Val.IsValidCridantials(Username.getText().toString(), Password.getText().toString()))
			{
				Intent run = new Intent(this,EmailSender.class);
				run.putExtra("username",Username.getText().toString());
				startActivity(run);
			}
			else
			{
				Toast.makeText(this, R.string.wrongCredantials, Toast.LENGTH_LONG).show();
			}
		}
		else if (GoToGridView.getId() == v.getId())
		{
			Intent run = new Intent(this,GridViewActivity.class);
			startActivity(run);
		}
		else if(v.getId() == StartThread.getId())
		{
			MyAsyncTask task = new MyAsyncTask();
			task.activity = this;
			task.execute();
		}
		else if (StartHttp.getId() == v.getId())
		{
			Intent run = new Intent(this,HttpTest.class);
			startActivity(run);
		}
	}
}
