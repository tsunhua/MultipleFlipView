package io.github.linlshare.MultipleFlipViewLib;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Random;

/**
 * 随机翻转视图
 *
 * @author Lshare
 * @date 2016/10/25
 * <p>
 * Copyright (c) 2016. WUDE All rights reserved.
 */
public class RandomFlipLayout extends LinearLayout {

    private static final int FLIP_COUNT = 9;
    private static final int ROW_COUNT = 3;
    private static final int COLUMN_COUNT = 3;

    private int[] currentFlipPosition = new int[2];
    private Handler handler;
    private Random random;

    public RandomFlipLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.MATCH_PARENT));
        setOrientation(VERTICAL);
        for (int i = 0; i < ROW_COUNT; i++) {
            addFlipRow();
        }
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    startRandomFlip();
                }
            }
        };
        random = new Random(System.currentTimeMillis());
    }

    /**
     * 填充图片
     *
     * @param centerImage 位于中间的图片
     * @param images      其他图片
     */
    public void setupWithImage(@DrawableRes int centerImage, @DrawableRes int... images) {
        getFlipViewByPosition(FLIP_COUNT / 2).addImage(false, false, centerImage);
        int[] randomArray = {0, 1, 2, 3, 5, 6, 7, 8};
        for (int i = 0; i < images.length; i++) {
            int position = i % randomArray.length;
            getFlipViewByPosition(randomArray[position]).addImage(true, true, images[i]);
        }
    }

    public void startRandomFlip() {
        int[] randomArray = {3, 1, 2, 0, 7, 6, 8, 5};
        currentFlipPosition[0] = randomArray[random.nextInt(randomArray.length)];
        currentFlipPosition[1] = calculateNextPosition(currentFlipPosition[0]);
        for (int i = 0; i < FLIP_COUNT; i++) {
            if (i == currentFlipPosition[0] || i == currentFlipPosition[1]) {
                //show next
                showNextAtPosition(i);
            }
        }
        handler.sendEmptyMessageDelayed(0, 4000);
    }

    private int calculateNextPosition(int lastPosition) {
        int nextPosition;
        int row = lastPosition / ROW_COUNT;
        int column = lastPosition % COLUMN_COUNT;

        if (row == column) { // 对角
            int newRow = (row + ROW_COUNT - 1) > (ROW_COUNT - 1) ? 0 : (row + ROW_COUNT - 1);
            int newColumn = (column + COLUMN_COUNT - 1) > (COLUMN_COUNT - 1) ? 0 : (column +
                    COLUMN_COUNT - 1);
            nextPosition = getPosition(newRow, newColumn);
        } else { // 转置
            nextPosition = getPosition(column, row);
        }
        return nextPosition;
    }

    private int getPosition(int row, int column) {
        return row * ROW_COUNT + column;
    }

    private void showNextAtPosition(int position) {
        getFlipViewByPosition(position).showNext();
    }

    private ImageFlipView getFlipViewByPosition(int position) {
        int row = position / ROW_COUNT;
        int column = position % COLUMN_COUNT;
        LinearLayout flipRow = (LinearLayout) getChildAt(row);
        return (ImageFlipView) flipRow.getChildAt(column);
    }

    private void addFlipRow() {
        LinearLayout flipRow = new LinearLayout(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        params.weight = 1;
        flipRow.setOrientation(HORIZONTAL);
        for (int i = 0; i < COLUMN_COUNT; i++) {
            addFlipViewToRow(flipRow);
        }
        addView(flipRow, params);
    }

    private void addFlipViewToRow(LinearLayout flipRow) {

        ImageFlipView imageFlipView = ImageFlipView.newInstance(getContext(), ImageFlipView
                .FlipStyle.STYLE_3D);
        LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;

        // handle topMargin
        if (getChildCount() == 0) {
            params.topMargin = 0;
        } else {
            params.topMargin = getResources().getDimensionPixelSize(R.dimen.small_margin_size);
        }

        // handle leftMargin and rightMargin
        if (flipRow.getChildCount() % COLUMN_COUNT == 0) {
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen
                    .login_cell_negative_left_width);
            params.rightMargin = 0;
        } else if (flipRow.getChildCount() % COLUMN_COUNT == COLUMN_COUNT - 1) {
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.small_margin_size);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen
                    .login_cell_negative_width);
        } else {
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.small_margin_size);
            params.rightMargin = 0;
        }
        flipRow.addView(imageFlipView, params);
    }
}
