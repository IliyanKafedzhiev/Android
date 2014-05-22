package com.example.androidsu_04.userinteraction;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.nfc.FormatException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements
		OnItemClickListener, OnClickListener {

	private static final String TAG = "TAG";
	private static final String NOTES_LIST_FIRST_VISIBLE_POS = "NOTES_LIST_FIRST_VISIBLE_POS";
	private static final String NOTES_ARRAYLIST_DATA = "NOTES_ARRAYLIST_DATA";
	private static final String NOTES_DEFAULT_IMAGE_ASSET_TITLE = "sonic";

	private ImageView imageViewPlaceholder;
	private int imageViewPlaceholderWidth;
	private int imageViewPlaceholderHeight;

	private ListView listViewNotes;
	private NotesAdapter notesListAdapter;

	private String[] defaultNoteTexts;
	private int newNoteIndex;

	private static final String[] mockedUpTxtFile = { "Mitko - Knuckles ",
			" Tosho - sonic ", "Penko - Tails " };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initializeViews();

		setupNotesListViewAndAdapterData(savedInstanceState);

		this.newNoteIndex = 0;
	}

	private void initializeViews() {
		imageViewPlaceholder = (ImageView) findViewById(R.id.imageViewPlaceholder);
		imageViewPlaceholderHeight = imageViewPlaceholder.getLayoutParams().height;
		imageViewPlaceholderWidth = imageViewPlaceholder.getLayoutParams().width;

		listViewNotes = (ListView) findViewById(R.id.listViewNotes);

		defaultNoteTexts = getResources()
				.getStringArray(R.array.notes_defaults);
	}

	private void setupNotesListViewAndAdapterData(Bundle savedInstanceState) {
		ArrayList<NotesAdapterDataItem> notesDataList = null;
		if (savedInstanceState == null) {
			notesDataList = reloadNotesData(notesDataList);
		} else {
			notesDataList = restoreNotesDataFromBundle(savedInstanceState);
		}

		notesListAdapter = new NotesAdapter(this, R.layout.list_item_note,
				notesDataList);
		listViewNotes.setAdapter(notesListAdapter);
		listViewNotes.setOnItemClickListener(this);
	}

	@SuppressWarnings("unchecked")
	private ArrayList<NotesAdapterDataItem> restoreNotesDataFromBundle(
			Bundle savedInstanceState) {
		ArrayList<NotesAdapterDataItem> notesDataList;
		notesDataList = ((ArrayList<NotesAdapterDataItem>) savedInstanceState
				.getSerializable(NOTES_ARRAYLIST_DATA));
		listViewNotes.setSelection(savedInstanceState.getInt(
				NOTES_LIST_FIRST_VISIBLE_POS, 0));
		return notesDataList;
	}

	private ArrayList<NotesAdapterDataItem> reloadNotesData(
			ArrayList<NotesAdapterDataItem> notesDataList) {

		try {
			notesDataList = loadListViewItemsFromStringArray(MainActivity.mockedUpTxtFile);
		} catch (FormatException e) {
			e.printStackTrace();
		}

		return notesDataList;
	}

	private ArrayList<NotesAdapterDataItem> loadListViewItemsFromStringArray(
			String[] mockedUpTxtFile) throws FormatException {

		ArrayList<NotesAdapterDataItem> notesData = new ArrayList<NotesAdapterDataItem>();

		for (String line : mockedUpTxtFile) {
			processInputFileLine(notesData, line);
		}

		return notesData;
	}

	/**
	 * The method will add to the 'notesData' ArrayList after processing using
	 * the string in the expected format which is passed as the second argument.
	 * 
	 * @param notesData
	 *            The data ArrayList to fill.
	 * @param line
	 *            A single line containing data in the following format: <
	 *            PersonName - AssetImageTitle >; <br>
	 *            Note: The AssetImageTitle does not contain the file extension
	 *            (any valid image file type is accepted)
	 * @throws FormatException
	 */
	private void processInputFileLine(
			ArrayList<NotesAdapterDataItem> notesData, String line)
			throws FormatException {
		String[] resultArr = line.trim().split("\\s+[-]\\s+");

		if (line.isEmpty()) {
			return;
		}

		if (resultArr.length != 2) {
			throw new FormatException(
					"The input file line is not in the format. Line item count: " + 2);
		}

		String noteName = resultArr[0];
		String noteImageAssetTitle = resultArr[1];

		NotesAdapterDataItem dataItem = new NotesAdapterDataItem(noteName,
				noteImageAssetTitle);
		notesData.add(dataItem);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable(NOTES_ARRAYLIST_DATA,
				(ArrayList<NotesAdapterDataItem>) this.notesListAdapter
						.getData());
		outState.putInt(NOTES_LIST_FIRST_VISIBLE_POS,
				this.listViewNotes.getFirstVisiblePosition());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.options_toggle_image:
			toggleImageViewPlaceholder();
			break;
		case R.id.options_add_note:
			addNewNote();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private void addNewNote() {
		ArrayList<NotesAdapterDataItem> data = (ArrayList<NotesAdapterDataItem>) this.notesListAdapter
				.getData();
		Log.d(TAG, data.toString());
		Log.d(TAG, Integer.toString(data.size()));
		Log.d(TAG, Integer.toString(defaultNoteTexts.length));

		String noteText = defaultNoteTexts[this.newNoteIndex
				% defaultNoteTexts.length];

		data.add(new NotesAdapterDataItem(noteText,
				NOTES_DEFAULT_IMAGE_ASSET_TITLE));
		this.notesListAdapter.notifyDataSetChanged();

		this.newNoteIndex++;
	}

	private void toggleImageViewPlaceholder() {
		LinearLayout.LayoutParams params = null;
		if (this.imageViewPlaceholder.getVisibility() == ImageView.VISIBLE) {
			this.imageViewPlaceholder.setVisibility(ImageView.INVISIBLE);
			params = new LinearLayout.LayoutParams(imageViewPlaceholderWidth, 0);
		} else {
			this.imageViewPlaceholder.setVisibility(ImageView.VISIBLE);
			params = new LinearLayout.LayoutParams(imageViewPlaceholderWidth,
					imageViewPlaceholderHeight);
		}

		this.imageViewPlaceholder.setLayoutParams(params);
	}

	@Override
	public void onBackPressed() {
		showConfirmExitDialog();
	}

	private void showConfirmExitDialog() {
		new AlertDialog.Builder(this)
				.setTitle("Exit...")
				.setMessage("Are you sure you want to exit?")
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// exit
								MainActivity.this.finish();
							}
						})
				.setNegativeButton(android.R.string.no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// do nothing
							}
						}).setIcon(android.R.drawable.ic_dialog_alert).show();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		Log.d(TAG, "position: " + Integer.toString(position));
		Log.d(TAG, "id: " + Long.toString(id));

		ImageView imageViewTopContainer = (ImageView) this
				.findViewById(R.id.imageViewPlaceholder);
		ImageView imageViewFromCurrentItem = (ImageView) view
				.findViewById(R.id.imageViewNoteImg);

		imageViewTopContainer.setImageDrawable(imageViewFromCurrentItem
				.getDrawable());
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.buttonRemoveNoteItem:

			int indexOfNoteToRemove = (Integer) view
					.getTag(R.id.notes_current_view_index);
			notesListAdapter.getData().remove(indexOfNoteToRemove);
			notesListAdapter.notifyDataSetChanged();

			Log.d(TAG, "remove item at pos: " + indexOfNoteToRemove); // debug

			break;

		default:
			break;
		}
	}
}
