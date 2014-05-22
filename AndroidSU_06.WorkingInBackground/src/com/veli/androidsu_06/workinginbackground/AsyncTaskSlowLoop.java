package com.veli.androidsu_06.workinginbackground;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AsyncTaskSlowLoop extends
		AsyncTask<AsyncTaskSlowLoop.SlowLoopInputData, Integer, Void> {

	private static final String TAG = "TAG";

	public class SlowLoopInputData {

		private int loopStart;
		private int loopEnd;
		private int loopUpdateStep;

		public SlowLoopInputData(int loopStart, int loopEnd, int loopStep) {
			this.setLoopStart(loopStart);
			this.setLoopEnd(loopEnd);
			this.setLoopUpdateStep(loopStep);
		}

		public int getLoopStart() {
			return loopStart;
		}

		public void setLoopStart(int loopStart) {
			this.loopStart = loopStart;
		}

		public int getLoopEnd() {
			return loopEnd;
		}

		public void setLoopEnd(int loopEnd) {
			this.loopEnd = loopEnd;
		}

		public int getLoopUpdateStep() {
			return loopUpdateStep;
		}

		public void setLoopUpdateStep(int loopStep) {
			this.loopUpdateStep = loopStep;
		}
	}

	private Context context;
	private ProgressBar progressBar;

	public AsyncTaskSlowLoop(Context context, ProgressBar progressBar) {
		this.context = context;
		this.progressBar = progressBar;
	}

	@Override
	protected void onPreExecute() {
		this.progressBar.setMax(100);
		Toast.makeText(context, "Slow Loop Started", Toast.LENGTH_SHORT).show();
	};

	@Override
	protected Void doInBackground(SlowLoopInputData... params) {

		SlowLoopInputData loop = params[0];

		int loopStart = loop.getLoopStart();
		int loopStep = loop.getLoopUpdateStep();
		int loopEnd = loop.getLoopEnd();
		long totalLength = loopEnd - loopStart;

		for (int i = loopStart; i < loopEnd; i++) {
			if ((i % loopStep) == 0) {
				if (isCancelled()) {
					return null;
				}
				
				int progress = (int)((((float)i / (float)totalLength)) * 100);
				Log.d(TAG, "progress: " + progress);
				publishProgress(progress);
			}
		}

		return null;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		this.progressBar.setProgress(values[0]);
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		this.progressBar.setProgress(100);
		Toast.makeText(context, "Slow Loop Finished", Toast.LENGTH_SHORT).show();
	}
}
