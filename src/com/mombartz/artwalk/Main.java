package com.mombartz.artwalk;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity implements OnItemClickListener, OnClickListener {
	List<Walks> walkData = new ArrayList<Walks>();
	ArrayList <Walks> list = new ArrayList <Walks>();
	JSONArray user = null;
	int IDish;
	String NAME;
	JSONArray arrayOfWalks = null;
	ListView walkListView;
	private static String url = "http://artwalknyc.com/explore/api/walks/get_walks_index";
	private static final String walk_name = "walks";
	TextView name1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button show_me = (Button) findViewById(R.id.show_data);
		show_me.setOnClickListener(this);
		new JSONParse().execute();

	}

	private class JSONParse extends AsyncTask<String, String, JSONObject>
			implements OnItemClickListener {
		private ProgressDialog pDialog;
		

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			ListView walkListView = (ListView) findViewById(R.id.list_of);
			ListAdapter customAdapter = new ListAdapter(Main.this,
					R.layout.row, walkData);
			walkListView.setAdapter(customAdapter);
			pDialog = new ProgressDialog(Main.this);
			pDialog.setMessage("Getting Data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		@Override
		protected JSONObject doInBackground(String... params) {

			ServiceHandler sh = new ServiceHandler();
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
			Log.d("Response: ", "> " + jsonStr);
			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);
					arrayOfWalks = jsonObj.getJSONArray(walk_name);
					for (int i = 0; i < arrayOfWalks.length(); i++) {
						JSONObject c = arrayOfWalks.getJSONObject(i);
						String IMAGE = c.getString("image");
						String WALK = c.getString("name");
						String DESCRIPTION = c.getString("description");
						IDish = c.getInt("id");
						String SLUG = c.getString("slug");
						NAME = c.getString("slug");
						JSONObject obj = arrayOfWalks.getJSONObject(i);
			            list.add(new Walks(IMAGE, NAME, DESCRIPTION, SLUG, WALK));
						walkData.add(new Walks(IMAGE, NAME, DESCRIPTION, SLUG, WALK));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;

		}

		@Override
		protected void onPostExecute(JSONObject json) {
			pDialog.dismiss();


			setListUp ();
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {



		}

	}
public void  setListUp () {
	walkListView = (ListView) findViewById(R.id.list_of);

	ListAdapter customAdapter = new ListAdapter(Main.this,
			R.layout.row, walkData);
	walkListView.setAdapter(customAdapter);
	walkListView.setOnItemClickListener(this);
	
}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		parent.getItemAtPosition(position);
		String testing = null;
		try {
			testing = arrayOfWalks.optJSONObject(position).getString("image");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//String.valueOf(id);
		try {
			Intent mIntent = new Intent(this, SeeImage.class);
			String s = arrayOfWalks.optJSONObject(position).getString("image");
		
			Bundle mBundle = new Bundle();
			mBundle.putString("theImage", s);
			mIntent.putExtras(mBundle);
			startActivity(mIntent);

		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String testing = (parent.getItemAtPosition(position).toString());
		Toast.makeText(Main.this, testing, Toast.LENGTH_LONG).show();
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String testing = (arrayOfWalks.toString());
		Toast.makeText(Main.this, testing, Toast.LENGTH_LONG).show();
	}

}
