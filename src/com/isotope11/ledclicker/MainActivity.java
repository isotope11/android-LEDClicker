package com.isotope11.ledclicker;

import java.util.ArrayList;
import java.util.List;

import org.apache.bsf.BSFException;
import org.jruby.embed.ScriptingContainer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	protected final String TAG = MainActivity.class.toString();
	protected ScriptingContainer mRubyContainer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.setProperty("jruby.bytecode.version", "1.5");
		mRubyContainer = new ScriptingContainer();
		List<String> loadPaths = new ArrayList<String>();
        loadPaths.add("jruby.home/lib/ruby/shared");
        loadPaths.add("jruby.home/lib/ruby/1.8");
        mRubyContainer.setLoadPaths(loadPaths);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onLEDClickerClick(View view) {
		try {
			runSomeRubbies();
		} catch (BSFException e) {
			Log.d(TAG, "Caught Exception", e);
		}
	}

	private void runSomeRubbies() throws BSFException {
		String rubyDrbClient = "require 'drb/drb';SERVER_URI='druby://192.168.1.83:8787';DRb.start_service;timeserver = DRbObject.new_with_uri(SERVER_URI);puts timeserver.get_current_time";
		String result = (String) mRubyContainer.runScriptlet(rubyDrbClient);
		
		Toast.makeText(this, result, Toast.LENGTH_LONG).show();
	}
}
