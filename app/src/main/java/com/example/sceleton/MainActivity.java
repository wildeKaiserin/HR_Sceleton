package com.example.sceleton;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // these will return the actual dpi horizontally and vertically
        float xDpi = dm.xdpi;
        float yDpi = dm.ydpi;
        //diagonal dpi
        float cDpi = (float)Math.sqrt(xDpi*xDpi+yDpi*yDpi);

        //NATIVE CALL
        setResolution(cDpi);

        //NATIVE CALL
        //file is dummy data, is ignored in the backend
        openFile(new File("/page"));

        //NATIVE CALL
        mvFirstPage();

        //create image object
        Object pageImage = new Object();
        ImageView imageView = findViewById(R.id.sample_image);
        //NATIVE CALL
        pageImage = getPage(imageView.getHeight(), imageView.getWidth());

        //write image object into file
        try {
            FileWriter fw = new FileWriter("/page");
            fw.write((String)pageImage);
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //decoding the saved image to display in the image view
        Bitmap bitmap = BitmapFactory.decodeFile("/page");
        imageView.setImageBitmap(bitmap);

        //NATIVE CALL
        closeFile();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //public native String stringFromJNI();

    public native Object getPage(int height, int width);

    public native boolean setResolution(float dpi);

    public native boolean openFile(File hintfile);

    public native boolean mvFirstPage();

    public native boolean closeFile();
}
