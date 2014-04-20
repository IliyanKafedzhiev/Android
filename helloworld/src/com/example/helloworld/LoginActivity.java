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
	EditText Username;
	EditText Password;
	Validatator Val;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        Val = new Validatator();
	        setContentView(R.layout.login_activity);
	        LoginButton = (Button)findViewById(R.id.ButtonLogin);
	        LoginButton.setOnClickListener(this);
	        Username = (EditText)findViewById(R.id.username);
	        Password = (EditText)findViewById(R.id.password);
	        Val.user = new String("u");
	        Val.password = new String("q");
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
	}
}
