package com.example.androidsu_07.accessingtheweb;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class UserAnswerListResponse {

	@SerializedName("items")
	private List<UserAnswer> answers;

	public UserAnswerListResponse() {
		this.answers = new ArrayList<UserAnswer>();
	}
	
	public List<UserAnswer> getUserAnswers() {
		return answers;
	}

	public void setUserAnswers(List<UserAnswer> userAnswers) {
		this.answers = userAnswers;
	}
}
