package com.example.androidsu_04.userinteraction;

import java.io.Serializable;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * The notes adapter data item.
 */
public class NotesAdapterDataItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private String noteText;
	private String noteImage;

	public NotesAdapterDataItem(String noteText, String noteImageAssetName) {
		this.setNoteText(noteText);
		this.setNoteImageAssetTitle(noteImageAssetName);
	}

	public String getNoteText() {
		return noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

	public String getNoteImageAssetTitle() {
		return noteImage;
	}

	public void setNoteImageAssetTitle(String noteImage) {
		this.noteImage = noteImage;
	}

	public static int getImageIdentifierFromAssetsByTitle(Context context,
			String imageAssetTitle) {
		int noteImageIdentifier = context.getResources().getIdentifier(
				imageAssetTitle, "drawable", context.getPackageName());
		return noteImageIdentifier;
	}
}
