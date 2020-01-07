package com.hoangviet.demoui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class DemoGetFile extends AppCompatActivity {
    private static final String TAG = "DemoGetFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_get_file);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int lenght = getFileSize(new URL("https://video.xx.fbcdn.net/v/t42.9040-2/81833073_1257805527750331_1678037292981157888_n.mp4?_nc_cat=110&efg=eyJybHIiOjM3NiwicmxhIjo1MTIsInZlbmNvZGVfdGFnIjoic3ZlX3NkIn0%3D&_nc_eui2=AeG7qeqTvD0EGzpRtk4wFF2VjlG5y4dvAw6fGXuDFH8y0i592k7g2iZ0BA11bGmbpR6T_yYd_Ag2cYoZ5if-vLGSu_n0IfEoMnlDUvy8hu_leQ&_nc_ohc=CYKV5vZURDYAQnHfdxwNo8_Up5jlihx_lshc_0KzC2ZhICSHMBFWjU-HA&_nc_ht=video.fdad2-1.fna&oh=69d3458f893ec11256831c132945fdbc&oe=5E0B30E3"));
                    Log.d(TAG, "run: " + lenght);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private int getFileSize(URL url) throws IOException {
        URLConnection connection = url.openConnection();
        return connection.getContentLength();
    }
}
