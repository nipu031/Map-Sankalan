package com.example.android.imagemerger;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * This class is an activity and will displayed first when application starts
 * This Activity will display small images in a grid view
 * It also shows a button showing "Merge" on it. When clicks merges the small image chunks
 */
public class SmallImagesActivity extends Activity implements OnClickListener {

    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "SmallImagesActivity";
    String path;
    Button btnBrowse, mergeButton, btnMargeFinal;
    ImageView img, RealImage;
    Uri selectedImageUri;
    ArrayList<Bitmap> smallImages;
    Integer[] RandomMarge = new Integer[200];
    String filePath;
    int chunkNumbers = 100;
    int row1 = 10, col1 = 10;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnMargeFinal = (Button) findViewById(R.id.btnMargeFinal);
        mergeButton = (Button) findViewById(R.id.merge_button);
        img = (ImageView) findViewById(R.id.merged_image);
        RealImage = (ImageView) findViewById(R.id.Realimage);
        btnBrowse = (Button) findViewById(R.id.Browse);
        btnBrowse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();

                System.out.println("Path main " + path);
            }
        });

        btnMargeFinal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                marge();
            }
        });

        mergeButton.setOnClickListener(this);
    }


    private ArrayList<Bitmap> splitImage(Drawable image, int chunkNumbers) {

        //For the number of rows and columns of the grid to be displayed
        int rows, cols;
        rows = cols = row1;

        //For height and width of the small image chunks
        int chunkHeight, chunkWidth;

        //To store all the small image chunks in bitmap format in this list
        ArrayList<Bitmap> chunkedImages = new ArrayList<Bitmap>(chunkNumbers);

        //Getting the scaled bitmap of the source image
        BitmapDrawable drawable = (BitmapDrawable) image;
        Bitmap bitmap = drawable.getBitmap();
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);

        rows = cols = (int) Math.sqrt(chunkNumbers);
        chunkHeight = bitmap.getHeight() / rows;
        chunkWidth = bitmap.getWidth() / cols;

        //xCoord and yCoord are the pixel positions of the image chunks
        int yCoord = 0;
        for (int x = 0; x < rows; x++) {
            int xCoord = 0;
            for (int y = 0; y < cols; y++) {
                chunkedImages.add(Bitmap.createBitmap(scaledBitmap, xCoord, yCoord, chunkWidth, chunkHeight));
                xCoord += chunkWidth;
            }
            yCoord += chunkHeight;
        }
        return chunkedImages;
    }

	/*
     * This method actually implements the code for merging the small images
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */

    @Override
    public void onClick(View view) {

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
            Drawable drawable = new BitmapDrawable(bitmap);
            smallImages = splitImage(drawable, chunkNumbers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Get the width and height of the smaller chunks
        int chunkWidth = smallImages.get(0).getWidth();
        int chunkHeight = smallImages.get(0).getHeight();

        //create a bitmap of a size which can hold the complete image after merging
        Bitmap bitmap = Bitmap.createBitmap(chunkWidth * row1, chunkHeight * col1, Bitmap.Config.ARGB_4444);

        //create a canvas for drawing all those small images
        Canvas canvas = new Canvas(bitmap);
        int count = 0;

        ShuffleArray(chunkNumbers - 1); // send value for randomly shuffle

        int j = 0;
        for (int rows = 0; rows < row1; rows++) {
            for (int cols = 0; cols < col1; cols++) {
                canvas.drawBitmap(smallImages.get(RandomMarge[j]), chunkWidth * cols, chunkHeight * rows, null);
                j++;
            }
        }

		/*
		 * The result image is shown in a new Activity
		 */
        img.setImageBitmap(bitmap);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        String timeStamp = dateFormat.format(new Date());
        String imageFileName = "nipu_" + timeStamp + ".jpg";

        storeImage(bitmap, imageFileName);


    }

    public void ShuffleArray(int value) {
        for (int i = 0; i <= value; i++) {
            RandomMarge[i] = i;
        }

        Random rnd = new Random();
        for (int i = value; i >= 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = RandomMarge[index];
            RandomMarge[index] = RandomMarge[i];
            RandomMarge[i] = a;
        }


    }

    private boolean storeImage(Bitmap imageData, String filename) {
        //get path to external storage (SD card)
        String iconsStoragePath = Environment.getExternalStorageDirectory() + "/NipuTesting/Nipu/";
        File sdIconStorageDir = new File(iconsStoragePath);

        //create storage directories, if they don't exist
        sdIconStorageDir.mkdirs();

        try {
            filePath = sdIconStorageDir.toString() + filename;
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);

            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

            //choose another format if PNG doesn't suit you
            imageData.compress(Bitmap.CompressFormat.JPEG, 100, bos);

            bos.flush();
            bos.close();

        } catch (FileNotFoundException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
            return false;
        } catch (IOException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
            return false;
        }

        return true;
    }


    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                // Get the url from data
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    path = getPathFromURI(selectedImageUri);
                    Log.i(TAG, "Image Path : " + path);
                    // Set the image in ImageView
                    img.setImageURI(selectedImageUri);
                }
            }
        }
    }

    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }


    public void marge() {

        if (smallImages != null) {
            smallImages.clear();
        }

        smallImages = splitImage(Drawable.createFromPath(filePath), chunkNumbers);
        //Get the width and height of the smaller chunks
        int chunkWidth = smallImages.get(0).getWidth();
        int chunkHeight = smallImages.get(0).getHeight();

        //create a bitmap of a size which can hold the complete image after merging
        Bitmap bitmap = Bitmap.createBitmap(chunkWidth * row1, chunkHeight * col1, Bitmap.Config.ARGB_4444);

        //create a canvas for drawing all those small images
        Canvas canvas = new Canvas(bitmap);
        int count = 0;
        Integer[] MargeArr = new Integer[200];


        for (int i = 0; i < chunkNumbers; i++)
            for (int j = 0; j < chunkNumbers; j++) {
                if (RandomMarge[j] == count) {
                    MargeArr[i] = j;
                    count++;
                    break;
                }
            }
        System.out.println("Random" + RandomMarge);
        System.out.println("value" + MargeArr);

        int j = 0;

        for (int rows = 0; rows < row1; rows++) {
            for (int cols = 0; cols < col1; cols++) {
                canvas.drawBitmap(smallImages.get(MargeArr[j]), chunkWidth * cols, chunkHeight * rows, null);
                j++;
            }
        }


        RealImage.setImageBitmap(bitmap);


    }


}