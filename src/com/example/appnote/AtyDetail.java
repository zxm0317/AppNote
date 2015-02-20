package com.example.appnote;

import com.example.db.NoteDB;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * ฯ๊ว้าณ
 * 
 * @author zxm
 * 
 */
public class AtyDetail extends Activity implements OnClickListener{
	private Button s_delete, s_back;
	private ImageView s_img;
	private VideoView s_video;
	private TextView s_tv;
	private NoteDB notesDB;
	private SQLiteDatabase dbWriter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		s_delete = (Button) findViewById(R.id.s_delete);
		s_back = (Button) findViewById(R.id.s_back);
		s_img = (ImageView) findViewById(R.id.s_img);
		s_video = (VideoView) findViewById(R.id.s_video);
		s_tv = (TextView) findViewById(R.id.s_tv);
		notesDB = new NoteDB(this);
		dbWriter = notesDB.getWritableDatabase();
		s_back.setOnClickListener(this);
		s_delete.setOnClickListener(this);
		
		if (getIntent().getStringExtra(NoteDB.PATH).equals("null")) {
			s_img.setVisibility(View.GONE);
		} else {
			s_img.setVisibility(View.VISIBLE);
		}
		if (getIntent().getStringExtra(NoteDB.VIDEO).equals("null")) {
			s_video.setVisibility(View.GONE);
		} else {
			s_video.setVisibility(View.VISIBLE);
		}
		s_tv.setText(getIntent().getStringExtra(NoteDB.CONTENT));
		BitmapFactory.Options opts = new BitmapFactory.Options();
		
		opts.inSampleSize = 4;
		Bitmap bitmap=BitmapFactory.decodeFile(getIntent().getStringExtra(NoteDB.PATH),opts);
		s_img.setImageBitmap(bitmap);
		s_video.setVideoURI(Uri
				.parse(getIntent().getStringExtra(NoteDB.VIDEO)));
		s_video.start();
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.s_delete:
			deleteDate();
			finish();
			break;

		case R.id.s_back:
			finish();
			break;
		}
	}
	private void deleteDate() {
		dbWriter.delete(NoteDB.TABLE_NAME, "_id="+getIntent().getIntExtra(NoteDB.ID, 0), null);
	}
}
