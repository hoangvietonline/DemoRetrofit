package com.hoangviet.demoui;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;

public class ReadFilePdfActivity extends AppCompatActivity {
    private static final String TAG = "ReadFilePdfActivity";
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_file_pdf);
        seekBar = findViewById(R.id.seekBar);
        final PDFView pdfView = findViewById(R.id.pdfView);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        pdfView.fromAsset("pdfnna.pdf")
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .defaultPage(0)
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(final int page, int pageCount) {
                        seekBar.setMax(pageCount);
                        seekBar.setProgress(page);
                    }
                })
                .enableAnnotationRendering(true)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        Log.d(TAG, "loadComplete: " + nbPages);
                    }
                })
                .scrollHandle(new DefaultScrollHandle(this))
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .onTap(new OnTapListener() {
                    @Override
                    public boolean onTap(MotionEvent e) {
                        return true;
                    }
                })
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(5)
                .pageFitPolicy(FitPolicy.WIDTH)
                .pageSnap(true)
                .autoSpacing(true)
                .pageFling(true)
                .nightMode(true)
                .load();
    }
}
