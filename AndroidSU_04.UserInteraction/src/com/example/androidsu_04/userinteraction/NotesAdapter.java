package com.example.androidsu_04.userinteraction;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NotesAdapter extends BaseAdapter {

	private static final String TAG = "TAG";
	private Context context;
	private List<NotesAdapterDataItem> data;
	private LayoutInflater layoutInflater;
	private int listItemLayoutId;

	public NotesAdapter(Context context, int layoutId) {
		this.context = context;
		this.layoutInflater = (LayoutInflater) this.context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		this.setData(new ArrayList<NotesAdapterDataItem>());
		this.setListItemLayoutId(layoutId);
	}

	public NotesAdapter(Context context, int layoutId, List<NotesAdapterDataItem> data) {
		this(context, layoutId);
		this.setData(data);
	}

	@Override
	public int getCount() {
		return this.getData().size();
	}

	@Override
	public Object getItem(int position) {
		return this.getData().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = this.layoutInflater.inflate(this.listItemLayoutId,
					parent, false);

			viewHolder = createAndFillViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		updateCurrentListItemFromViewHolder(position, viewHolder);

		return convertView;
	}

	private void updateCurrentListItemFromViewHolder(int position,
			ViewHolder viewHolder) {
		NotesAdapterDataItem dataItem = this.getData().get(position);
		viewHolder.textViewNote.setText(dataItem.getNoteText());

		int noteImageIdentifier = NotesAdapterDataItem
				.getImageIdentifierFromAssetsByTitle(context,
						dataItem.getNoteImageAssetTitle().toLowerCase());
		viewHolder.imageViewNoteImg.setImageResource(noteImageIdentifier);

		viewHolder.buttonRemoveNote
				.setOnClickListener((OnClickListener) context);
		viewHolder.buttonRemoveNote.setTag(R.id.notes_current_view_index,
				position);
	}

	private ViewHolder createAndFillViewHolder(View convertView) {
		ViewHolder viewHolder;
		LinearLayout insider = (LinearLayout) convertView
				.findViewById(R.id.linearLayout_list_item_note_middle);

		TextView textViewNoteText = (TextView) insider
				.findViewById(R.id.textView_list_item_note_text);
		Button buttonRemoveNote = (Button) convertView
				.findViewById(R.id.buttonRemoveNoteItem);
		ImageView imageViewNoteImg = (ImageView) convertView
				.findViewById(R.id.imageViewNoteImg);

		viewHolder = new ViewHolder(textViewNoteText, buttonRemoveNote,
				imageViewNoteImg);
		return viewHolder;
	}

	public List<NotesAdapterDataItem> getData() {
		return data;
	}

	public void setData(List<NotesAdapterDataItem> data) {
		this.data = data;
	}

	public int getListItemLayoutId() {
		return listItemLayoutId;
	}

	public void setListItemLayoutId(int layout) {
		this.listItemLayoutId = layout;
	}

	private class ViewHolder {
		TextView textViewNote;
		Button buttonRemoveNote;
		ImageView imageViewNoteImg;

		public ViewHolder(TextView textViewNote, Button buttonRemoveNote,
				ImageView imageViewNoteImg) {
			this.textViewNote = textViewNote;
			this.buttonRemoveNote = buttonRemoveNote;
			this.imageViewNoteImg = imageViewNoteImg;
		}
	}
}
