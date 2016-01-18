package hatim.dhuliawala.addressbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import hatim.dhuliawala.addressbook.R;

public class AddData extends Activity implements OnClickListener {
	Button add, back;
	EditText fname, lname, nickname, address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		add = (Button) findViewById(R.id.bAddData);
		back = (Button) findViewById(R.id.bAddBack);
		fname = (EditText) findViewById(R.id.etFirstName);
		lname = (EditText) findViewById(R.id.etLastName);
		nickname = (EditText) findViewById(R.id.etUnique);
		address = (EditText) findViewById(R.id.etAddress);
		add.setOnClickListener(AddData.this);
		back.setOnClickListener(AddData.this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bAddData:
			boolean didItWork = true;
			try {
				String firstname = fname.getText().toString();
				String lastname = lname.getText().toString();
				String addnickname = nickname.getText().toString();
				String addaddress = address.getText().toString();
				if (firstname.equals("") || lastname.equals("") || addnickname.equals("") || addaddress.equals("")) {
					didItWork = false;
					Toast err1 = Toast.makeText(getBaseContext(), "Please Enter all Value", Toast.LENGTH_LONG);
					err1.show();

				} else {
					didItWork = true;
					AddressDatabase entry = new AddressDatabase(AddData.this);
					entry.open();
					entry.createEntry(firstname, lastname, addnickname, addaddress);
					entry.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
				didItWork = false;
				Toast err = Toast.makeText(AddData.this, "Please Enter all Value", Toast.LENGTH_LONG);
				err.show();
			} finally {
				if (didItWork == true) {
					Toast show = Toast.makeText(getBaseContext(), "Added", Toast.LENGTH_LONG);
					show.show();
					Intent mainactivity1 = new Intent("hatim.dhuliawala.addressbook.ADDRESSACTIVITY");
					startActivity(mainactivity1);

					// Hiding Keyboard
					InputMethodManager i = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					i.hideSoftInputFromInputMethod(address.getWindowToken(), 0);
					this.finish();
				}
			}

			break;

		case R.id.bAddBack:
			Intent mainactivity = new Intent("hatim.dhuliawala.addressbook.ADDRESSACTIVITY");
			startActivity(mainactivity);
			this.finish();
		}

	}

}
