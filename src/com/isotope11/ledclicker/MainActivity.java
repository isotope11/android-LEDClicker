package com.isotope11.ledclicker;

import org.apache.bsf.BSFException;
import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	protected final String TAG = MainActivity.class.toString();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		System.setProperty("jruby.bytecode.version", "1.5");
		Ruby runtime = Ruby.newInstance();
		IRubyObject result = runtime.evalScriptlet("'foo'");
		
		Toast.makeText(this, (String) result.convertToString().toJava(String.class), Toast.LENGTH_LONG).show();
	}
}
