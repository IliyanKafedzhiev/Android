package com.example.helloworld;
import java.lang.Thread;


import android.widget.Button;

public class MyThread extends Thread{

	Button LoginButton;
	public MyThread(Button bb) {
		LoginButton = bb;
	}
	@Override
	public void run() {
		LoginButton.setText("Hello neW Btn");
		super.run();
	}

}
