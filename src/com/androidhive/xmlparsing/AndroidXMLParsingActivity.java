package com.androidhive.xmlparsing;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AndroidXMLParsingActivity extends ListActivity
{

	// All static variables
	static final String URL = "http://www.reddit.com/.xml";
	// XML node keys
	static final String KEY_ITEM = "item"; // parent node
	static final String KEY_TITLE = "title";
	static final String KEY_LINK = "link";
	static final String KEY_GUID = "guid";
	static final String KEY_DESCRIPTION = "description";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

		XMLParser parser = new XMLParser();
		String xml = parser.getXmlFromUrl(URL); // getting XML
		Document doc = parser.getDomElement(xml); // getting DOM element

		NodeList nl = doc.getElementsByTagName(KEY_TITLE);
		// looping through all item nodes <item>
		for (int i = 0; i < nl.getLength(); i++)
		{
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			map.put(KEY_ITEM, parser.getValue(e, KEY_ITEM));
			map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
			map.put(KEY_LINK, parser.getValue(e, KEY_LINK));
			map.put(KEY_GUID, parser.getValue(e, KEY_GUID));
			map.put(KEY_DESCRIPTION, parser.getValue(e, KEY_DESCRIPTION));

			// adding HashList to ArrayList
			menuItems.add(map);
		}

		// Adding menuItems to ListView
		ListAdapter adapter = new SimpleAdapter(this, menuItems, R.layout.list_item, new String[] { KEY_ITEM, KEY_TITLE,
			KEY_LINK, KEY_GUID, KEY_DESCRIPTION }, new int[] { R.id.title, R.id.link, R.id.guid, R.id.description });

		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();

		lv.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				// getting values from selected ListItem
				String title = ((TextView) view.findViewById(R.id.title)).getText().toString();
				String link = ((TextView) view.findViewById(R.id.link)).getText().toString();
				String guid = ((TextView) view.findViewById(R.id.guid)).getText().toString();
				String description = ((TextView) view.findViewById(R.id.description)).getText().toString();

				// Starting new intent
				Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
				in.putExtra(KEY_GUID, guid);
				in.putExtra(KEY_DESCRIPTION, description);
				startActivity(in);

			}
		});
	}
}