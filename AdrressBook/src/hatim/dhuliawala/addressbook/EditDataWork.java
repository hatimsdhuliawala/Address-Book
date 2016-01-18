package hatim.dhuliawala.addressbook;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import hatim.dhuliawala.addressbook.R;

public class EditDataWork extends Activity implements OnClickListener {

	Button edit, main, back;
	EditText fname, lname, nname, address;
	String gotbread = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editdatapage);
		initialize();
		Bundle gotBasket = getIntent().getExtras();
		gotbread = gotBasket.getString("key");
		settextEdit();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		edit = (Button) findViewById(R.id.beditdatafinal);
		main = (Button) findViewById(R.id.bgobacktomain);
		back = (Button) findViewById(R.id.bgobacktoeditdata);
		fname = (EditText) findViewById(R.id.etFirstNameedit);
		lname = (EditText) findViewById(R.id.etLastNameedit);
		nname = (EditText) findViewById(R.id.etNickNameedit);
		address = (EditText) findViewById(R.id.etAddressedit);
		back.setOnClickListener(EditDataWork.this);
		main.setOnClickListener(EditDataWork.this);
		edit.setOnClickListener(EditDataWork.this);

	}

	private void settextEdit() {
		// TODO Auto-generated method stub
		boolean InfoWork = true;
		try {
			long l = Long.parseLong(gotbread);
			AddressDatabase getdatainedit = new AddressDatabase(EditDataWork.this);
			getdatainedit.open();
			String returnedFName = getdatainedit.getFName(l);
			String returnedLName = getdatainedit.getLName(l);
			String returnedNName = getdatainedit.getNName(l);
			String returnedAddress = getdatainedit.getAddress(l);
			getdatainedit.close();
			fname.setText(returnedFName);
			lname.setText(returnedLName);
			nname.setText(returnedNName);
			address.setText(returnedAddress);
		} catch (Exception e) {
			InfoWork = false;
			String error = e.toString();
			Dialog d = new Dialog(this);
			d.setTitle("Oops it did not Work");
			TextView tv = new TextView(this);
			tv.setText(error);
			d.setContentView(tv);
			d.show();
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.beditdatafinal:
			boolean some = true;
			try{
			String mFName = fname.getText().toString();
			String mLName = lname.getText().toString();
			String mNName =nname.getText().toString();
			String mAddress =address.getText().toString();
			
			long lRow = Long.parseLong(gotbread);
			AddressDatabase hmod = new AddressDatabase(EditDataWork.this);
			hmod.open();
			hmod.getUpdate(lRow , mFName , mLName, mNName, mAddress);
			hmod.close();
		}catch (Exception e){
			some = false;
			 String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Oops it did not Work");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
		}finally{
			if (some == true){
				Toast done = Toast.makeText(getBaseContext(), "Modified", Toast.LENGTH_LONG);
				done.show();
				
				Intent mainactivity1 = new Intent("hatim.dhuliawala.addressbook.ADDRESSACTIVITY");
				startActivity(mainactivity1);

				// Hiding Keyboard
				InputMethodManager i = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				i.hideSoftInputFromInputMethod(address.getWindowToken(), 0);
				this.finish();
			}
		}


			break;

		case R.id.bgobacktoeditdata:
			Intent mainactivity = new Intent("hatim.dhuliawala.addressbook.EDITDATAMAIN");
			startActivity(mainactivity);
			this.finish();
			break;

		case R.id.bgobacktomain:
			Intent mainactivity1 = new Intent("hatim.dhuliawala.addressbook.ADDRESSACTIVITY");
			startActivity(mainactivity1);
			this.finish();
			break;
		}

	}

}
