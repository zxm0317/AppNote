package com.example.appnote;

import java.io.File;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import com.example.db.Config;
import com.example.db.NoteDB;
import com.example.util.DateUtils;

/**
 * 添加内容
 * 
 * @author Administrator
 * 
 */
public class AtyContent extends Activity implements OnClickListener {

	private String val;
	private EditText editText;
	private Button saveBtn, chanelBtn;
	private SQLiteDatabase sqlWriter;
	private NoteDB db;
	private ImageView img;
	private VideoView video;
	private File phoneFile, videoFile;
	private Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_content);
		val = getIntent().getStringExtra(com.example.db.Config.FG);
		editText = (EditText) findViewById(R.id.edit);
		saveBtn = (Button) findViewById(R.id.save);
		chanelBtn = (Button) findViewById(R.id.chanel);
		img = (ImageView) findViewById(R.id.add_img);
		video = (VideoView) findViewById(R.id.add_video);
		saveBtn.setOnClickListener(this);
		chanelBtn.setOnClickListener(this);
		db = new NoteDB(this);
		sqlWriter = db.getWritableDatabase();
		initView();

	}

	private void addDB() {

		ContentValues cv = new ContentValues();
		cv.put(NoteDB.CONTENT, editText.getText().toString());
		cv.put(NoteDB.TIME,
				DateUtils.formatDate("yyyy年MM月dd日 HH:mm:ss", new Date()));
		cv.put(NoteDB.PATH, phoneFile + "");
		cv.put(NoteDB.VIDEO, videoFile + "");
		sqlWriter.insert(NoteDB.TABLE_NAME, null, cv);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.save:
			addDB();
			finish();
			extracted();
			break;

		case R.id.chanel:
			finish();
			extracted();
			break;
		}
	}

	private void initView() {
		String tmp = getIntent().getStringExtra(Config.FG);
		if (tmp.equals("1")) {
			img.setVisibility(View.GONE);
			video.setVisibility(View.GONE);
		}
		if (tmp.equals("2")) {
			video.setVisibility(View.GONE);
			img.setVisibility(View.VISIBLE);
			Intent iimage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			phoneFile = new File(Environment.getExternalStorageDirectory()
					.getAbsoluteFile() + "/" + getTime() + ".jpg");
			iimage.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(phoneFile));
			startActivityForResult(iimage, 0);
		}
		if (tmp.equals("3")) {
			img.setVisibility(View.GONE);
			video.setVisibility(View.VISIBLE);
			Intent iivideo = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
			videoFile = new File(Environment.getExternalStorageDirectory()
					.getAbsoluteFile() + "/" + getTime() + ".mp4");
			iivideo.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(videoFile));
			startActivityForResult(iivideo, 1);
		}
	}

	public static Long getTime() {
		Date d = new Date();
		return d.getTime();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0) {
			BitmapFactory.Options opts = new BitmapFactory.Options();
			 
			opts.inSampleSize = 4;

			bitmap = BitmapFactory.decodeFile(phoneFile.getAbsolutePath(),opts);
			img.setImageBitmap(bitmap);
		}
		if (requestCode == 1) {
			video.setVideoURI(Uri.fromFile(videoFile));
			video.start();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		extracted();

	}

	/**
	 * 垃圾回收
	 */
	private void extracted() {
		if (bitmap != null){
			bitmap.recycle();
		bitmap = null;
		System.gc();
		}
	}
}
