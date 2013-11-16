package com.example.servbot;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class RestaurantMenu extends Activity {
	public final static String TEST1 = "com.example.servbot.MESSAGE1";
	public final static String TEST2 = "com.example.servbot.MESSAGE2";
	String name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		name = getIntent().getStringExtra(RestaurantList.EXTRA_MESSAGE);
;		setContentView(R.layout.activity_restaurant_menu);
		setTitle(name);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurant_menu, menu);
		return true;
	}
	
	public void sendTable(View view){
		EditText editText = (EditText) findViewById(R.id.test);
		String table = editText.getText().toString();
		HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://servbot.herokuapp.com/restaurants/" + name + "/" + table);
	    try {
			HttpResponse response = httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Intent intent = new Intent(this, Order.class);
	    intent.putExtra(TEST1, name);
	    intent.putExtra(TEST2, table);
	    startActivity(intent);
		
	}

}



