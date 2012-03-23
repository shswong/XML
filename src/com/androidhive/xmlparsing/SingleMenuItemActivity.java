package com.androidhive.xmlparsing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleMenuItemActivity extends Activity
{

	// XML node keys
	static final String item = "item";
	static final String key_title = "title";
	static final String key_link = "link";
	static final String key_guid = "guid";
	static final String key_description = "description";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_list_item);

		// getting intent data
		Intent in = getIntent();

		// Get XML values from previous intent
		String title = in.getStringExtra(key_title);
		String link = in.getStringExtra(key_link);
		String guid = in.getStringExtra(key_guid);
		String description = in.getStringExtra(key_description);

		// Displaying all values on the screen
		TextView labelTitle = (TextView) findViewById(R.id.title_label);
		TextView labelLink = (TextView) findViewById(R.id.link_label);
		TextView labelGuid = (TextView) findViewById(R.id.guid_label);
		TextView labelDescription = (TextView) findViewById(R.id.description_label);

		labelTitle.setText(title);
		labelLink.setText(link);
		labelGuid.setText(guid);
		labelDescription.setText(description);
	}
}
