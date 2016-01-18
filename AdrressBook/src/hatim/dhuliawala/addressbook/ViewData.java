package hatim.dhuliawala.addressbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import hatim.dhuliawala.addressbook.R;

public class ViewData extends Activity implements OnClickListener {
	TextView displaydata;
	Button done;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view);
		initialize();
		try{
		AddressDatabase info = new AddressDatabase(ViewData.this);
		info.open();
		String data = info.getData();
		info.close();
		displaydata.setText(data);
		}catch(Exception e){
			e.printStackTrace();
			Toast err = Toast.makeText(getBaseContext(), "ERR", Toast.LENGTH_LONG);
			err.show();
		}
	}
	private void initialize() {
		// TODO Auto-generated method stub
		displaydata =(TextView) findViewById(R.id.tvDisplay);
		done = (Button) findViewById(R.id.bViewDone);
		done.setOnClickListener(ViewData.this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bViewDone:
			Intent mainactivity = new Intent ("hatim.dhuliawala.addressbook.ADDRESSACTIVITY");
			startActivity (mainactivity);
			this.finish();
		}
		
			
	}
	

}
