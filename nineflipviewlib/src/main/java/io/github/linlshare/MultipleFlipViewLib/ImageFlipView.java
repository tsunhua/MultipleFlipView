package io.github.linlshare.MultipleFlipViewLib;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import io.github.linlshare.MultipleFlipViewLib.animation.Rotate3dAnimation;
import io.github.linlshare.MultipleFlipViewLib.util.ImageUtil;

/**
 * 自动轮换图片的视图
 *
 * @author Lshare
 * @date 2016/10/25
 * <p>
 * Copyright (c) 2016. WUDE All rights reserved.
 */
public class ImageFlipView extends ViewFlipper {

    private boolean flippable; //是否可以轮换

    private FlipStyle flipStyle = FlipStyle.STYLE_3D;

    public enum FlipStyle {
        STYLE_3D, STYLE_SLIDE
    }

    public ImageFlipView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isFlippable() {
        return flippable;
    }

    public void setFlippable(boolean flippable) {
        this.flippable = flippable;
    }

    public static ImageFlipView newInstance(Context context, FlipStyle flipStyle) {
        ImageFlipView imageFlipView = new ImageFlipView(context);
        AnimationSet inAnimationSet = new AnimationSet(false);
        AnimationSet outAnimationSet = new AnimationSet(false);
        switch (flipStyle) {
            case STYLE_3D:
                Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(0, 90, imageFlipView
                        .getWidth() / 2.0f, imageFlipView.getWidth() / 2.0f, 0, false);
                rotate3dAnimation.setDuration(1000);
                outAnimationSet.addAnimation(rotate3dAnimation);
                break;
            case STYLE_SLIDE:
                break;
        }
        inAnimationSet.addAnimation(AnimationUtils.loadAnimation(context, R.anim
                .slide_in_right));
        outAnimationSet.addAnimation(AnimationUtils.loadAnimation(context, R.anim
                .slide_out_right));
        imageFlipView.setInAnimation(inAnimationSet);
        imageFlipView.setOutAnimation(outAnimationSet);
        return imageFlipView;
    }

    public ImageFlipView(Context context) {
        super(context);
    }

    /**
     * 添加圖片到FlipView中
     *
     * @param shouldOverlay 是否需要遮罩层
     * @param flippable     是否可以轮换
     * @param resIds
     */
    public void addImage(boolean shouldOverlay, boolean flippable, @DrawableRes int... resIds) {
        this.flippable = flippable;
        for (int resId : resIds) {
            ImageView imageView = newImageView(shouldOverlay, resId);
            addView(imageView);
        }
    }

    /**
     * 新建一个ImageView
     *
     * @param shouldOverlay 是否需要遮罩层
     * @param resId
     * @return
     */
    private ImageView newImageView(boolean shouldOverlay, @DrawableRes int resId) {
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (shouldOverlay) {
            imageView.setImageDrawable(ImageUtil.overlayBlack(getContext(), resId));
        } else {
            imageView.setImageResource(resId);
        }
        return imageView;
    }

    @Override
    public void showPrevious() {
        if (flippable) {
            super.showPrevious();
        }
    }

    @Override
    public void showNext() {
        if (flippable) {
            super.showNext();
        }
    }

    @Override
    public void startFlipping() {
        if (flippable) {
            super.startFlipping();
        }
    }

    @Override
    public void stopFlipping() {
        if (flippable) {
            super.stopFlipping();
        }
    }
}
