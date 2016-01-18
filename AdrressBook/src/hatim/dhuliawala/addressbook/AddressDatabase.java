package hatim.dhuliawala.addressbook;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import android.widget.Toast;

public class AddressDatabase extends Activity {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_FNAME = "person_firstName";
	public static final String KEY_LNAME = "person_lastName";
	public static final String KEY_NICKNAME = "person_nickname";
	public static final String KEY_ADDRESS = "person_address";

	private static final String DATABASE_NAME = "Addressdb";
	private static final String DATABASE_TABLE = "peopleTable";
	private static final int DATABASE_VERSION = 1;

	private final Context ourContext;
	private DBHelper ourHelper;
	private SQLiteDatabase ourDatabase;

	private static class DBHelper extends SQLiteOpenHelper {

		public DBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_FNAME + " TEXT NOT NULL, " + KEY_LNAME + " TEXT NOT NULL, " + KEY_NICKNAME
					+ " TEXT NOT NULL, " + KEY_ADDRESS + " TEXT NOT NULL);");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXIST " + DATABASE_TABLE);
			onCreate(db);

		}

	}

	public AddressDatabase(Context c) {
		ourContext = c;
	}

	public AddressDatabase open() throws SQLException {
		ourHelper = new DBHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;

	}

	public void close() {
		ourHelper.close();
	}

	public long createEntry(String fname, String lname, String nickname, String address) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_FNAME, fname);
		cv.put(KEY_LNAME, lname);
		cv.put(KEY_NICKNAME, nickname);
		cv.put(KEY_ADDRESS, address);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public String getData() throws SQLException {
		// TODO Auto-generated method stub
		String[] column = new String[] { KEY_ROWID, KEY_FNAME, KEY_LNAME, KEY_NICKNAME, KEY_ADDRESS };
		Cursor c = ourDatabase.query(DATABASE_TABLE, column, null, null, null, null, null);
		String result = "";

		int iFName = c.getColumnIndex(KEY_FNAME);
		int iLName = c.getColumnIndex(KEY_LNAME);
		int iNickname = c.getColumnIndex(KEY_NICKNAME);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + "First Name = " + c.getString(iFName) + "\nLast Name = " + c.getString(iLName)
					+ "\nNick Name  = " + c.getString(iNickname) + "\n\n";

		}
		return result;
	}

	public String searchdata(String l) throws SQLException {
		// TODO Auto-generated method stub
		String[] column = new String[] { KEY_ROWID, KEY_FNAME, KEY_LNAME, KEY_NICKNAME, KEY_ADDRESS };
		Cursor c = ourDatabase.query(DATABASE_TABLE, column, KEY_NICKNAME + " = " + "'" + l + "'", null, null, null,
				null);
		String result = "";

		if (c != null) {
			// c.moveToFirst();
			int iFName = c.getColumnIndex(KEY_FNAME);
			int iLName = c.getColumnIndex(KEY_LNAME);
			int iAddress = c.getColumnIndex(KEY_ADDRESS);
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				result = result + "First Name = " + c.getString(iFName) + "\nLast Name = " + c.getString(iLName)
						+ "\nAddress  = " + c.getString(iAddress) + "\n\n";

			}
			return result;
		}
		return null;
	}

	public void deleteEntry(long lRowDelete) throws SQLException {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRowDelete, null);
	}

	public String getDeleteRowId(String rowiddelete) throws SQLException {
		// TODO Auto-generated method stub
		String[] column = new String[] { KEY_ROWID, KEY_FNAME, KEY_LNAME, KEY_NICKNAME, KEY_ADDRESS };
		Cursor c = ourDatabase.query(DATABASE_TABLE, column, KEY_NICKNAME + "=" + "'" + rowiddelete + "'", null, null,
				null, null);
		String result = "";

		int iFName = c.getColumnIndex(KEY_FNAME);
		int iLName = c.getColumnIndex(KEY_LNAME);
		int iRowid = c.getColumnIndex(KEY_ROWID);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + "Row ID = " + c.getString(iRowid) + "\nFirst Name = " + c.getString(iFName)
					+ "\nLast Name  = " + c.getString(iLName) + "\n\n";

		}
		return result;
	}
	
	public String getFName(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] column = new String[] { KEY_ROWID, KEY_FNAME, KEY_LNAME, KEY_NICKNAME, KEY_ADDRESS };
		Cursor c = ourDatabase.query(DATABASE_TABLE, column, KEY_ROWID + " = " + l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String fname = c.getString(1);
			return fname;
		}
		return null;
	}
	
	public String getLName(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] column = new String[] { KEY_ROWID, KEY_FNAME, KEY_LNAME, KEY_NICKNAME, KEY_ADDRESS };
		Cursor c = ourDatabase.query(DATABASE_TABLE, column, KEY_ROWID + " = " + l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String lname = c.getString(2);
			return lname;
		}
		return null;
	}
	
	public String getNName(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] column = new String[] { KEY_ROWID, KEY_FNAME, KEY_LNAME, KEY_NICKNAME, KEY_ADDRESS };
		Cursor c = ourDatabase.query(DATABASE_TABLE, column, KEY_ROWID + " = " + l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String nname = c.getString(3);
			return nname;
		}
		return null;
	}
	
	public String getAddress(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] column = new String[] { KEY_ROWID, KEY_FNAME, KEY_LNAME, KEY_NICKNAME, KEY_ADDRESS };
		Cursor c = ourDatabase.query(DATABASE_TABLE, column, KEY_ROWID + " = " + l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String addressdata = c.getString(4);
			return addressdata;
		}
		return null;
	}

	public void getUpdate(long lRow, String mFName, String mLName,String mNName, String mAddress) throws SQLException {
		// TODO Auto-generated method stub
		ContentValues modify = new ContentValues();
		modify.put(KEY_FNAME, mFName);
		modify.put(KEY_LNAME, mLName);
		modify.put(KEY_NICKNAME, mNName);
		modify.put(KEY_ADDRESS, mAddress);
		ourDatabase.update(DATABASE_TABLE, modify, KEY_ROWID + "=" + lRow, null);
	}

}
