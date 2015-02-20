package com.example.appnote;

import com.example.adapter.MyAdapter;
import com.example.db.Config;
import com.example.db.NoteDB;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {
	private Button txtBtn, picBtn, videoBtn;
	private ListView listView;
	private Intent intent;
	private SQLiteDatabase sqlReader;
	private NoteDB db;
	private MyAdapter adapter;
	private Cursor cursor ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		txtBtn = (Button) findViewById(R.id.btn_word);
		picBtn = (Button) findViewById(R.id.btn_pic);
		videoBtn = (Button) findViewById(R.id.btn_video);
		listView = (ListView) findViewById(R.id.list);
		txtBtn.setOnClickListener(this);
		picBtn.setOnClickListener(this);
		videoBtn.setOnClickListener(this);
		db = new NoteDB(this);
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i=new Intent(MainActivity.this,AtyDetail.class);
				cursor.moveToPosition(position);
				i.putExtra(NoteDB.ID,
						cursor.getInt(cursor.getColumnIndex(NoteDB.ID)));
				i.putExtra(NoteDB.CONTENT, cursor.getString(cursor
						.getColumnIndex(NoteDB.CONTENT)));
				i.putExtra(NoteDB.TIME,
						cursor.getString(cursor.getColumnIndex(NoteDB.TIME)));
				i.putExtra(NoteDB.PATH,
						cursor.getString(cursor.getColumnIndex(NoteDB.PATH)));
				i.putExtra(NoteDB.VIDEO,
						cursor.getString(cursor.getColumnIndex(NoteDB.VIDEO)));
				startActivity(i);
			}
			
		});
	}

	@Override
	public void onClick(View v) {
		intent = new Intent(MainActivity.this, AtyContent.class);
		switch (v.getId()) {
		case R.id.btn_word:
			intent.putExtra(Config.FG, "1");
			startActivity(intent);
			break;
		case R.id.btn_pic:
			intent.putExtra(Config.FG, "2");
			startActivity(intent);
			break;
		case R.id.btn_video:
			intent.putExtra(Config.FG, "3");
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		selectDb();
	}

	private void selectDb() {
		sqlReader = db.getReadableDatabase();
		 cursor = sqlReader.query(NoteDB.TABLE_NAME, null, null, null,
				null, null, null);
		adapter = new MyAdapter(this, cursor);
		listView.setAdapter(adapter);
	}
}
