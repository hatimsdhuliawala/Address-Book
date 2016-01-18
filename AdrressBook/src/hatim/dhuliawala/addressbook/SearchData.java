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

public class SearchData extends Activity implements OnClickListener{

	Button searchBack, searchdata;
	EditText findnickname;
	TextView searchDisplay;
	boolean del;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchdata);
		initialize();
		
	}

	private void initialize() {
		// TODO Auto-generated method stub
		searchdata = (Button) findViewById(R.id.bSearchData);
		searchBack = (Button) findViewById(R.id.bSearchBack);
		findnickname = (EditText) findViewById(R.id.etSearchData);
		searchDisplay = (TextView) findViewById(R.id.tvSearchDisplay);
		searchBack.setOnClickListener(SearchData.this);
		searchdata.setOnClickListener(SearchData.this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			
		case R.id.bSearchData:
			del = true;
			try{
			String sRowSearch =findnickname.getText().toString();
			if (sRowSearch.equals("") )
			{
				del =false;
				 Toast err1 = Toast.makeText(getBaseContext(), "Please Enter NickName ", Toast.LENGTH_LONG);
					err1.show();
			
			}else {
			AddressDatabase nsearch= new AddressDatabase(SearchData.this);
			nsearch.open();
			String data = nsearch.searchdata(sRowSearch);
			
			nsearch.close();
			searchDisplay.setText(data);
			}
		}catch (Exception e){
			del = false;
			e.printStackTrace();
			Toast err = Toast.makeText(getBaseContext(), "Please Enter all Value", Toast.LENGTH_LONG);
			err.show();
		}finally{
			// Hiding Keyboard
			InputMethodManager i = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			i.hideSoftInputFromInputMethod(findnickname.getWindowToken(), 0);
			}
		

			
				break;
				
			
		case R.id.bSearchBack:
			Intent mainactivity = new Intent ("hatim.dhuliawala.addressbook.ADDRESSACTIVITY");
			startActivity(mainactivity);
			this.finish();
				break;
		}
	}

}
