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
import android.widget.TextView;
import android.widget.Toast;
import hatim.dhuliawala.addressbook.R;

public class EditDataMain extends Activity implements OnClickListener {
	Button edittonext, editBacktomain, getRowIdforEdit;
	EditText getrowidvianickname, rowidforedit;
	TextView displayrowidforedit;
	String bread;
	boolean row;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editdata);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub

		edittonext = (Button) findViewById(R.id.bEditdatawithRowid123);
		editBacktomain = (Button) findViewById(R.id.bEditDataBacktoMain);
		getRowIdforEdit = (Button) findViewById(R.id.bGetDataforEdit);
		getrowidvianickname = (EditText) findViewById(R.id.etGetEditNickname);
		rowidforedit = (EditText) findViewById(R.id.etGetRowidforEdit);
		displayrowidforedit = (TextView) findViewById(R.id.tvGetEditRowId);
		edittonext.setOnClickListener(EditDataMain.this);
		editBacktomain.setOnClickListener(EditDataMain.this);
		getRowIdforEdit.setOnClickListener(EditDataMain.this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bGetDataforEdit:
			row = true;
			try {
				String rowidedit = getrowidvianickname.getText().toString();
				if (getrowidvianickname.equals(""))

				{
					row = false;
					Toast err1 = Toast.makeText(getBaseContext(), "Enter A nickname", Toast.LENGTH_LONG);
					err1.show();

				} else {

					AddressDatabase getrowiddata = new AddressDatabase(EditDataMain.this);
					getrowiddata.open();
					String data = getrowiddata.getDeleteRowId(rowidedit);
					getrowiddata.close();
					if (data.equals("")) {
						Toast err = Toast.makeText(getBaseContext(), "Enter Valid Nickname", Toast.LENGTH_LONG);
						err.show();
					} else
						displayrowidforedit.setText(data);
				}
			} catch (Exception e) {
				row = false;
				e.printStackTrace();
				Toast err = Toast.makeText(getBaseContext(), "Please Enter all Value", Toast.LENGTH_LONG);
				err.show();

			}

			break;

		case R.id.bEditdatawithRowid123:
			try{
			bread = rowidforedit.getText().toString();
			if (bread.equals(""))

			{
				row = false;
				Toast err1 = Toast.makeText(getBaseContext(), "Please Enter Rowid ", Toast.LENGTH_LONG);
				err1.show();

			} else {

			
			Bundle basket = new Bundle();
			basket.putString("key", bread);
			Intent a = new Intent(EditDataMain.this,EditDataWork.class);
			a.putExtras(basket);
			startActivity(a);
			this.finish();

			// Hiding Keyboard
			InputMethodManager i = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			i.hideSoftInputFromInputMethod(rowidforedit.getWindowToken(), 0);
			}}catch(Exception e){
				e.printStackTrace();
				Toast t = Toast.makeText(getBaseContext(), "some error", Toast.LENGTH_LONG);
				t.show();
			}
			//Intent a = new Intent("com.example.addressbook.EDITDATA");
			//a.putExtras(basket);
			//startActivity(a);*/
			//Intent mainactivity1 = new Intent("com.example.addressbook.EDITDATAWORK");
			//startActivity(mainactivity1);
			

			break;

		case R.id.bEditDataBacktoMain:
			Intent mainactivity = new Intent("hatim.dhuliawala.addressbook.ADDRESSACTIVITY");
			startActivity(mainactivity);
			this.finish();
			break;
		}

	}

}
