package com.example.helloworld;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.os.Build;

public class MainActivity extends Activity implements android.view.View.OnClickListener{

	TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx =(TextView) findViewById(R.id.textView1);
        
        Bundle b = getIntent().getExtras();
        
        if(b != null)
        {
        	tx.setText(b.getString("username"));
			
        }

    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

    

}
