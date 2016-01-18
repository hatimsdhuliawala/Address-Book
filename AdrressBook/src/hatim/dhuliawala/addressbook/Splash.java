package hatim.dhuliawala.addressbook;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import hatim.dhuliawala.addressbook.R;

public class Splash extends Activity {
	MediaPlayer oursong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		oursong = MediaPlayer.create(Splash.this, R.raw.entry);
		oursong.start();

		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					Intent first = new Intent("hatim.dhuliawala.addressbook.ADDRESSACTIVITY");
					startActivity(first);
				}
			}
		};
		timer.start();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		oursong.release(); 
		finish();
	}

}
