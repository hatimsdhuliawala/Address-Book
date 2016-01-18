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

public class DeleteData extends Activity implements OnClickListener {
	Button delete, deleteBack , getRowId;
	EditText deleteNickName, getrowiddelete;
	TextView displayrowid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		delete = (Button) findViewById(R.id.bDeleteData);
		deleteBack = (Button) findViewById(R.id.bDeleteBack);
		getRowId = (Button) findViewById(R.id.bDeleteRowId);
		deleteNickName = (EditText) findViewById(R.id.etDeleteNickname);
		getrowiddelete = (EditText) findViewById(R.id.etGetRowidforDeletion);
		displayrowid = (TextView) findViewById(R.id.tvDeleteRowId);
		getRowId.setOnClickListener(DeleteData.this);
		delete.setOnClickListener(DeleteData.this);
		deleteBack.setOnClickListener(DeleteData.this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bDeleteData:
			boolean del = true;

			try {
				String sRowDelete = getrowiddelete.getText().toString();
				long lRowDelete = Long.parseLong(sRowDelete);
				if (getrowiddelete.equals(""))

				{
					del = false;
					Toast err1 = Toast.makeText(getBaseContext(), "Please Enter Row Id ", Toast.LENGTH_LONG);
					err1.show();

				} else {

					AddressDatabase hdelete = new AddressDatabase(DeleteData.this);
					hdelete.open();
					
					hdelete.deleteEntry(lRowDelete);

					hdelete.close();
				}
			} catch (Exception e) {
				del = false;
				e.printStackTrace();
				Toast err = Toast.makeText(getBaseContext(), "Record Not Found", Toast.LENGTH_LONG);
				err.show();
			} finally {
				if (del == true) {
					Toast show = Toast.makeText(getBaseContext(), "Deleted", Toast.LENGTH_LONG);
					show.show();
					Intent mainactivity1 = new Intent("hatim.dhuliawala.addressbook.ADDRESSACTIVITY");
					startActivity(mainactivity1);

					// Hiding Keyboard
					InputMethodManager i = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					i.hideSoftInputFromInputMethod(deleteNickName.getWindowToken(), 0);
					this.finish();
				}
			}

			break;
			
		case R.id.bDeleteRowId:
			boolean rowdel = true;
			try {
				String rowiddelete = deleteNickName.getText().toString();
				if (deleteNickName.equals(""))

				{
					del = false;
					Toast err1 = Toast.makeText(getBaseContext(), "Please Enter NickName ", Toast.LENGTH_LONG);
					err1.show();

				} else {

					AddressDatabase getrowiddata = new AddressDatabase(DeleteData.this);
					getrowiddata.open();
					String data = getrowiddata.getDeleteRowId(rowiddelete);
					getrowiddata.close();
					if(data.equals("")){
						Toast err = Toast.makeText(getBaseContext(), "Enter Valid Nickname", Toast.LENGTH_LONG);
						err.show();
					}else
					displayrowid.setText(data);
				}
			} catch (Exception e) {
				rowdel = false;
				e.printStackTrace();
				Toast err = Toast.makeText(getBaseContext(), "Please Enter all Value", Toast.LENGTH_LONG);
				err.show();
			
			}
			break;
			
		case R.id.bDeleteBack:
			Intent mainactivity = new Intent("hatim.dhuliawala.addressbook.ADDRESSACTIVITY");
			startActivity(mainactivity);
			this.finish();

		}

	}

}
