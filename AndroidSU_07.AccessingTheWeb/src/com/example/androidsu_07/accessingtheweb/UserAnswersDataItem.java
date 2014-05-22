package com.example.androidsu_07.accessingtheweb;


/**
 * The notes adapter data item.
 */
public class UserAnswersDataItem {
	
	private String answerText;
	private int upvoteCount;
	private int downvoteCount;

	public String getAnswerTitle() {
		return answerText;
	}
	public void setAnswerTitle(String answerText) {
		this.answerText = answerText;
	}
	public int getUpvoteCount() {
		return upvoteCount;
	}
	public void setUpvoteCount(int upvoteCount) {
		this.upvoteCount = upvoteCount;
	}
	public int getDownvoteCount() {
		return downvoteCount;
	}
	public void setDownvoteCount(int downvoteCount) {
		this.downvoteCount = downvoteCount;
	}
}
