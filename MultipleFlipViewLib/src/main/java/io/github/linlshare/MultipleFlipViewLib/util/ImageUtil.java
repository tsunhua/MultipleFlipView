package io.github.linlshare.MultipleFlipViewLib.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

/**
 * @author Lshare
 * @date 2016/10/24
 * <p>
 * Copyright (c) 2016. WUDE All rights reserved.
 */
public class ImageUtil {

    /**
     * 图像重叠
     *
     * @param context
     * @param materialRes
     * @return
     */
    public static Bitmap overlay(Context context, @DrawableRes int materialRes, Bitmap cover) {
        Bitmap bmp = BitmapFactory.decodeResource(context.getApplicationContext().getResources
                (), materialRes);
        return overlay(context, bmp, cover);
    }

    /**
     * 加上一层黑色的前景
     *
     * @param context
     * @param materialRes
     * @return
     */
    public static Drawable overlayBlack(Context context, @DrawableRes int materialRes) {
        Bitmap bmp = BitmapFactory.decodeResource(context.getApplicationContext().getResources
                (), materialRes);
        BitmapDrawable drawable = new BitmapDrawable(overlay(context, bmp, generateBlackCover(bmp.getWidth(), bmp.getHeight())));
        return drawable;
    }

    /**
     * 图片效果叠加
     *
     * @param bmp 限制了尺寸大小的Bitmap
     * @return
     */
    public static Bitmap overlay(Context context, Bitmap bmp, Bitmap cover) {
        long start = System.currentTimeMillis();
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        // 对边框图片进行缩放
        int w = cover.getWidth();
        int h = cover.getHeight();
        float scaleX = width * 1F / w;
        float scaleY = height * 1F / h;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);

        Bitmap overlayCopy = Bitmap.createBitmap(cover, 0, 0, w, h, matrix, true);

        int pixColor = 0;
        int layColor = 0;

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;
        int pixA = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;
        int newA = 0;

        int layR = 0;
        int layG = 0;
        int layB = 0;
        int layA = 0;

        final float alpha = 0.5F;

        int[] srcPixels = new int[width * height];
        int[] layPixels = new int[width * height];
        bmp.getPixels(srcPixels, 0, width, 0, 0, width, height);
        overlayCopy.getPixels(layPixels, 0, width, 0, 0, width, height);

        int pos = 0;
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                pos = i * width + k;
                pixColor = srcPixels[pos];
                layColor = layPixels[pos];

                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                pixA = Color.alpha(pixColor);

                layR = Color.red(layColor);
                layG = Color.green(layColor);
                layB = Color.blue(layColor);
                layA = Color.alpha(layColor);

                newR = (int) (pixR * alpha + layR * (1 - alpha));
                newG = (int) (pixG * alpha + layG * (1 - alpha));
                newB = (int) (pixB * alpha + layB * (1 - alpha));
                layA = (int) (pixA * alpha + layA * (1 - alpha));

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));
                newA = Math.min(255, Math.max(0, layA));

                srcPixels[pos] = Color.argb(newA, newR, newG, newB);
            }
        }

        bitmap.setPixels(srcPixels, 0, width, 0, 0, width, height);
        long end = System.currentTimeMillis();
//        Log.d("may", "overlayAmeliorate used time=" + (end - start));
        return bitmap;
    }

//    public static Bitmap drawableToBitmap(Drawable drawable) {
//        if (drawable instanceof BitmapDrawable) {
//            return ((BitmapDrawable) drawable).getBitmap();
//        }
//        Bitmap bitmap;
//        if (drawable.getIntrinsicWidth() > 0 && drawable.getIntrinsicHeight() > 0) {
//            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable
//                    .getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//        } else {
//            int defaultWidth = 720;
//            int defaultHeight = 480;
//            bitmap = Bitmap.createBitmap(defaultWidth, defaultHeight, Bitmap.Config.ARGB_8888);
//        }
//        Canvas canvas = new Canvas(bitmap);
//        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
//        drawable.draw(canvas);
//
//        return bitmap;
//    }

    /**
     * 生成一个半透明的黑色覆盖层Bitmap
     *
     * @param width
     * @param height
     * @return
     */
    public static Bitmap generateBlackCover(int width, int height) {
        return generateCoverBitmap(width, height, Color.parseColor("#06000000"));
    }

    /**
     * 通过一个颜色值生成一个Bitmap
     *
     * @param width
     * @param height
     * @param colorInt
     * @return
     */
    public static Bitmap generateCoverBitmap(int width, int height, int colorInt) {
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        canvas.drawColor(colorInt);
        return bmp;
    }
}
