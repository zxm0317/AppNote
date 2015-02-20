package com.example.adapter;

import com.example.appnote.R;
import com.example.db.NoteDB;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

public class MyAdapter extends BaseAdapter {

	private Context context;
	private Cursor cursor;

	public MyAdapter(Context context, Cursor cursor) {
		this.context = context;
		this.cursor = cursor;
	}

	@Override
	public int getCount() {
		return cursor.getCount();
	}

	@Override
	public Object getItem(int position) {
		return cursor.getPosition();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.cell,
				null);
		TextView tv = (TextView) layout.findViewById(R.id.content);
		TextView time = (TextView) layout.findViewById(R.id.content_time);
		ImageView imageView = (ImageView) layout.findViewById(R.id.img_pic);
		ImageView videoView=(ImageView) layout.findViewById(R.id.img_video);
		cursor.moveToPosition(position);
		String url=cursor.getString(cursor.getColumnIndex(NoteDB.PATH));
		String vedio_url=cursor.getString(cursor.getColumnIndex(NoteDB.VIDEO));
		tv.setText(cursor.getString(cursor.getColumnIndex(NoteDB.CONTENT)));
		time.setText(cursor.getString(cursor.getColumnIndex(NoteDB.TIME)));
		if(url!=null){
			
			imageView.setImageBitmap(getImageBitmap(url, 200, 200));
		}
		if(vedio_url!=null){
			
			videoView.setImageBitmap(getVideoBitmap(vedio_url, 200, 200,
					MediaStore.Images.Thumbnails.MICRO_KIND));
		}
		return layout;
	}

	private Bitmap getImageBitmap(String uri, int width, int height) {
		Bitmap bitmap = null;
		BitmapFactory.Options options=new BitmapFactory.Options();
		options.inJustDecodeBounds=true;
		bitmap=BitmapFactory.decodeFile(uri, options);
		options.inJustDecodeBounds = false;
		int beWidth=options.outWidth/width;
		int beHeight=options.outWidth/height;
		int be=1;
		if(beWidth<beHeight){
			be=beHeight;
		}
		if(beHeight<beWidth){
			be=beWidth;
		}
		if(be<=0){
			be=1;
		}
		options.inSampleSize=be;
		bitmap=BitmapFactory.decodeFile(uri,options);
		bitmap=ThumbnailUtils.extractThumbnail(bitmap, width, height,ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		return bitmap;
	}
	private Bitmap getVideoBitmap(String uri, int width, int height,int kid) {
		Bitmap bitmap = null;
		bitmap=ThumbnailUtils.createVideoThumbnail(uri, kid);
		bitmap=ThumbnailUtils.extractThumbnail(bitmap, width, height,ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		return bitmap;
	}
}
