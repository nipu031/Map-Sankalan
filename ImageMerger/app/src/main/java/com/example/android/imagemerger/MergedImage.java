package com.example.android.imagemerger;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class MergedImage extends Activity {

	@Override
	public void onCreate(Bundle bundle){
		
		super.onCreate(bundle);
		setContentView(R.layout.main);
		
		Bitmap bitmap = getIntent().getParcelableExtra("merged_image");
		ImageView image = (ImageView) findViewById(R.id.merged_image);
		image.setImageBitmap(bitmap);
		
	}
}
