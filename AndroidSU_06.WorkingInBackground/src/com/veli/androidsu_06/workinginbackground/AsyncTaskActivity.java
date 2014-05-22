package com.veli.androidsu_06.workinginbackground;

import com.veli.androidsu_06.workinginbackground.AsyncTaskSlowLoop.SlowLoopInputData;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class AsyncTaskActivity extends PresetMenuActivity {

	private Button buttonExecuteAsyncTask;
	private Button buttonCancelAsyncTask;
	private ProgressBar asyncTaskProgress;

	private AsyncTaskSlowLoop task;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_async_task);

		this.buttonExecuteAsyncTask = (Button) this
				.findViewById(R.id.buttonExecuteAsyncTask);
		this.buttonCancelAsyncTask = (Button) this
				.findViewById(R.id.buttonCancelAsyncTask);
		this.asyncTaskProgress = (ProgressBar) this
				.findViewById(R.id.progressBar_async_task_progress);

		this.buttonExecuteAsyncTask.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AsyncTaskActivity.this.createAndExecuteSlowLoopAsyncTask();
			}
		});

		this.buttonCancelAsyncTask.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				asyncTaskProgress.setProgress(0);
				AsyncTaskActivity.this.cancelAsyncTask();
			}
		});
	}

	private void createAndExecuteSlowLoopAsyncTask() {
		this.task = new AsyncTaskSlowLoop(AsyncTaskActivity.this,
				asyncTaskProgress);

		SlowLoopInputData data = this.task.new SlowLoopInputData(0, 200000000,
				20000000);

		this.task.execute(data);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// as there is no correct way to retain an AsyncTask across activty
		// lifecycle without fragments I simply cancel the task
		cancelAsyncTask();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();

		cancelAsyncTask();
	}

	private void cancelAsyncTask() {
		if (this.task != null) {
			this.task.cancel(false);
			this.task = null;
		}
	}
}
