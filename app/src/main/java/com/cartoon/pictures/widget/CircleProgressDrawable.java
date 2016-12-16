package com.cartoon.pictures.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 * Created by chenxunlin01 on 2016/12/16.
 */

public class CircleProgressDrawable extends Drawable {

    private final Ring mRing;

    public CircleProgressDrawable() {
        this.mRing = new Ring(mCallback);
    }

    @Override
    public void draw(Canvas canvas) {
        final Rect bounds = getBounds();
        final int saveCount = canvas.save();
        mRing.draw(canvas, bounds);
        canvas.restoreToCount(saveCount);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public void setProgress(int progress) {
        mRing.setProgress(progress);
    }

    private final Callback mCallback = new Callback() {
        @Override
        public void invalidateDrawable(Drawable d) {
            invalidateSelf();
        }

        @Override
        public void scheduleDrawable(Drawable d, Runnable what, long when) {
            scheduleSelf(what, when);
        }

        @Override
        public void unscheduleDrawable(Drawable d, Runnable what) {
            unscheduleSelf(what);
        }
    };

    private static class Ring {

        private Paint mPaint;
        private int progress;
        private final Callback mCallback;

        public Ring(Callback callback) {
            this.mCallback = callback;
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(0x88616161);
        }

        public void draw(Canvas c, Rect bounds) {
            int space = 16;
            RectF rectF = new RectF(bounds.left + space, bounds.top + space, bounds.right - space, bounds.bottom +
                    space);
            float rotate = progress * 360 / 100;
            c.drawArc(rectF, 90f, rotate, true, mPaint);
        }

        public void setProgress(int progress) {
            this.progress = progress;
            mCallback.invalidateDrawable(null);
        }
    }


}
