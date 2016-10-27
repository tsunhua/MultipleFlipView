package io.github.linlshare.nineflipviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.github.linlshare.MultipleFlipViewLib.RandomFlipLayout;

public class MainActivity extends AppCompatActivity {

    private RandomFlipLayout randomFlipLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        randomFlipLayout = (RandomFlipLayout) findViewById(R.id.rflayout);
        randomFlipLayout.setupWithImage(
                R.drawable.android_logo,
                R.drawable.login_cell_1, R.drawable.login_cell_1,
                R.drawable.login_cell_3, R.drawable.login_cell_4,
                R.drawable.login_cell_5, R.drawable.login_cell_6,
                R.drawable.login_cell_7, R.drawable.login_cell_8,
                R.drawable.login_cell_9, R.drawable.login_cell_10,
                R.drawable.login_cell_11, R.drawable.login_cell_12,
                R.drawable.login_cell_13, R.drawable.login_cell_14,
                R.drawable.login_cell_15, R.drawable.login_cell_16,
                R.drawable.login_cell_17, R.drawable.login_cell_18
        );
        randomFlipLayout.startRandomFlip();
    }
}
