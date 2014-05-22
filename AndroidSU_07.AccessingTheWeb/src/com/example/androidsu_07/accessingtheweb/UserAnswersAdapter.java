package com.example.androidsu_07.accessingtheweb;

import java.util.List;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserAnswersAdapter extends BaseAdapter {

	private Context context;
	private UserAnswerListResponse data;
	private LayoutInflater layoutInflater;
	private int listItemLayout;

	public UserAnswersAdapter(Context context, int layoutId) {
		this.context = context;
		this.layoutInflater = (LayoutInflater) this.context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		this.setData(new UserAnswerListResponse());
		this.setListItemLayoutId(layoutId);
	}

	public UserAnswersAdapter(Context context, int layoutId, UserAnswerListResponse data) {
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
			convertView = this.layoutInflater.inflate(R.layout.user_answer_list_item,
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
		UserAnswer dataItem = this.getData().get(position);
		viewHolder.textViewAnswerTitle.setText(dataItem.getTitle().toString());

		viewHolder.textViewAnswerScore.setText(String.valueOf(dataItem.getScore()));
	}

	private ViewHolder createAndFillViewHolder(View convertView) {
		ViewHolder viewHolder = null;
		LinearLayout insider = (LinearLayout) convertView
				.findViewById(R.id.listViewUserAnswersListItem);

		TextView textViewAnswerTitle = (TextView) insider
				.findViewById(R.id.textViewAnswerTitle);
		TextView textViewAnswerScore = (TextView) convertView
				.findViewById(R.id.textViewAnswerScore);

		viewHolder = new ViewHolder(textViewAnswerTitle, textViewAnswerScore);
		
		return viewHolder;
	}

	public List<UserAnswer> getData() {
		return data.getUserAnswers();
	}

	public void setData(UserAnswerListResponse data) {
		this.data = data;
	}

	public int getListItemLayout() {
		return listItemLayout;
	}

	public void setListItemLayoutId(int layoutId) {
		this.listItemLayout = layoutId;
	}

	private class ViewHolder {
		TextView textViewAnswerTitle;
		TextView textViewAnswerScore;

		public ViewHolder(TextView textViewAnswerTitle, 
				TextView textViewAnswerScore) {
			this.textViewAnswerTitle = textViewAnswerTitle;
			this.textViewAnswerScore = textViewAnswerScore;
		}
	}
}
