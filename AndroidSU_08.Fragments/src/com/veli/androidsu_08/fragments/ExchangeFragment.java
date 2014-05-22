package com.veli.androidsu_08.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ExchangeFragment extends Fragment {

	public static final String EXTRA_FRAGMENT_TEXTVIEW_TEXT = "FRAGMENT_TEXTVIEW_TEXT";

	private static final String TEXTVIEW_DEFAULT_TEXT = "blank";
	private TextView textViewExcahnge;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater
				.inflate(R.layout.fragment_layout, container, false);

		this.textViewExcahnge = (TextView) view
				.findViewById(R.id.textViewExchange);

		Bundle args = getArguments();
		restoreDataFromBundle(args);

		return view;
	}

	private void restoreDataFromBundle(Bundle data) {
		if (data != null) {
			textViewExcahnge.setText(data
					.getString(EXTRA_FRAGMENT_TEXTVIEW_TEXT));
		} else {
			textViewExcahnge.setText(TEXTVIEW_DEFAULT_TEXT);
		}
	}
}
