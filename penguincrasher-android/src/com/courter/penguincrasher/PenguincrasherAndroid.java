package com.courter.penguincrasher;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.courter.penguincrasher.Penguincrasher;

public class PenguincrasherAndroid extends AndroidApplication {
	/** Called when the activity is first created. */
	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initialize(new Penguincrasher(), false);
	}
}