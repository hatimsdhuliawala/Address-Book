package hatim.dhuliawala.addressbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import hatim.dhuliawala.addressbook.R;

public class AddressActivity extends Activity implements OnClickListener {
	Button add, delete, edit, view, search, exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		add = (Button) findViewById(R.id.bAdd);
		delete = (Button) findViewById(R.id.bDelete);
		edit = (Button) findViewById(R.id.bEdit);
		view = (Button) findViewById(R.id.bViewAll);
		search = (Button) findViewById(R.id.bSearch);
		exit = (Button) findViewById(R.id.bExit);
		add.setOnClickListener(AddressActivity.this);
		delete.setOnClickListener(AddressActivity.this);
		edit.setOnClickListener(AddressActivity.this);
		view.setOnClickListener(AddressActivity.this);
		search.setOnClickListener(AddressActivity.this);
		exit.setOnClickListener(AddressActivity.this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bAdd:
			Intent add = new Intent("hatim.dhuliawala.addressbook.ADDDATA");
			startActivity(add);
			this.finish();
			break;

		case R.id.bDelete:
			Intent delete = new Intent("hatim.dhuliawala.addressbook.DELETEDATA");
			startActivity(delete);
			this.finish();
			break;

		case R.id.bEdit:
			Intent editdatamain = new Intent("hatim.dhuliawala.addressbook.EDITDATAMAIN");
			startActivity(editdatamain);
			this.finish();
			break;

		case R.id.bViewAll:
			Intent viewdata = new Intent("hatim.dhuliawala.addressbook.VIEWDATA");
			startActivity(viewdata);
			this.finish();
			break;

		case R.id.bSearch:
			Intent searchdata = new Intent("hatim.dhuliawala.addressbook.SEARCHDATA");
			startActivity(searchdata);
			this.finish();
			break;

		case R.id.bExit:
			this.finish();
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			break;

		}

	}

}