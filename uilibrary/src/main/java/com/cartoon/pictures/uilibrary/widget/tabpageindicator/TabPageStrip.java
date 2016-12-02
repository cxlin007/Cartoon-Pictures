package com.cartoon.pictures.uilibrary.widget.tabpageindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;

import com.cartoon.pictures.uilibrary.R;


/**
 * ActionBar TabPageIndicator中的内部容器，包含各tab，绘制分隔线以及底部指示条
 *
 * @author cjh
 */
public class TabPageStrip extends LinearLayout {

    private static final int[] LL = new int[]{
            /* 0 */android.R.attr.divider,
            /* 1 */android.R.attr.showDividers,
            /* 2 */android.R.attr.dividerPadding,
            /* 3 */R.attr.tabPageBackground,
    };
    private static final int LL_DIVIDER = 0;
    private static final int LL_SHOW_DIVIDER = 1;
    private static final int LL_DIVIDER_PADDING = 2;
    private static final int LL_TAB_PAGE_BACKGROUND = 3;

    private Drawable tabPageBackground;
    private Drawable mDivider;
    private int mDividerWidth;
    private int mDividerHeight;
    private int mShowDividers;
    private int mDividerPadding;

    private int mIndexForSelection;
    private float mSelectionOffset;

    private Drawable mSelectedUnderlineDrawable;

    private int underLineWidth = -1;

    public TabPageStrip(Context context, int themeAttr) {
        super(context);

        TypedArray a = context.obtainStyledAttributes(null, LL, themeAttr, 0);
        setDividerDrawable(a.getDrawable(LL_DIVIDER));
        mDividerPadding = a.getDimensionPixelSize(LL_DIVIDER_PADDING, 0);
        mShowDividers = a.getInteger(LL_SHOW_DIVIDER, SHOW_DIVIDER_NONE);
        tabPageBackground = a.getDrawable(LL_TAB_PAGE_BACKGROUND);
        if (tabPageBackground != null) {
            if (tabPageBackground instanceof ColorDrawable && Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                tabPageBackground = new IcsColorDrawable((ColorDrawable) tabPageBackground);
            }
            setBackgroundDrawable(tabPageBackground);
        }
        a.recycle();

        setWillNotDraw(false);
        a = context.obtainStyledAttributes(null, R.styleable.ViewPagerIndicator, themeAttr, 0);
        mSelectedUnderlineDrawable = a.getDrawable(R.styleable.ViewPagerIndicator_tabPageIndicator);
        a.recycle();
    }

    public void setDividerDrawable(Drawable divider) {
        if (divider == mDivider) {
            return;
        }
        mDivider = divider;
        if (divider != null) {
            mDividerWidth = divider.getIntrinsicWidth();
            mDividerHeight = divider.getIntrinsicHeight();
        } else {
            mDividerWidth = 0;
            mDividerHeight = 0;
        }
        setWillNotDraw(divider == null);
        requestLayout();
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed,
                                           int parentHeightMeasureSpec, int heightUsed) {
        final int index = indexOfChild(child);
        final int orientation = getOrientation();
        final LayoutParams params = (LayoutParams) child.getLayoutParams();
        if (hasDividerBeforeChildAt(index)) {
            if (orientation == VERTICAL) {
                // Account for the divider by pushing everything up
                params.topMargin = mDividerHeight;
            } else {
                // Account for the divider by pushing everything left
                params.leftMargin = mDividerWidth;
            }
        }

        final int count = getChildCount();
        if (index == count - 1) {
            if (hasDividerBeforeChildAt(count)) {
                if (orientation == VERTICAL) {
                    params.bottomMargin = mDividerHeight;
                } else {
                    params.rightMargin = mDividerWidth;
                }
            }
        }
        super.measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // draw indicator
        int height = getHeight();
        int childCount = getChildCount();
        if (childCount > 0) {
            View childView = getChildAt(mIndexForSelection);
            // TODO 华为Mate 8（移动4G）Android 6.0 错误一次 暂不处理 mtj NullPointerException 'int android.view.View.getLeft()'
            int left = childView.getLeft();
            int right = childView.getRight();
            if (mSelectionOffset > 0.0f && mIndexForSelection < (getChildCount() - 1)) {
                View nextView = getChildAt(mIndexForSelection + 1);
                int nextLeft = nextView.getLeft();
                int nextRight = nextView.getRight();
                left = (int) (mSelectionOffset * nextLeft + (1.0F - mSelectionOffset) * left);
                right = (int) (mSelectionOffset * nextRight + (1.0F - mSelectionOffset) * right);
            }
            //设置下划线宽度
            if (underLineWidth > 0 && (right - left - underLineWidth) > 0) {
                int padding = (right - left - underLineWidth) / 2;
                mSelectedUnderlineDrawable.setBounds(left + padding, height - mSelectedUnderlineDrawable.getIntrinsicHeight(), right - padding,
                        height);

            } else {
                mSelectedUnderlineDrawable.setBounds(left, height - mSelectedUnderlineDrawable.getIntrinsicHeight(), right,
                        height);
            }
            mSelectedUnderlineDrawable.draw(canvas);
        }

        // draw divider
        if (mDivider != null) {
            if (getOrientation() == VERTICAL) {
                drawDividersVertical(canvas);
            } else {
                drawDividersHorizontal(canvas);
            }
        }

        super.onDraw(canvas);
    }

    private void drawDividersVertical(Canvas canvas) {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);

            if (child != null && child.getVisibility() != GONE) {
                if (hasDividerBeforeChildAt(i)) {
                    final LayoutParams lp = (LayoutParams) child
                            .getLayoutParams();
                    final int top = child.getTop() - lp.topMargin/*
                                                                 * -
																 * mDividerHeight
																 */;
                    drawHorizontalDivider(canvas, top);
                }
            }
        }

        if (hasDividerBeforeChildAt(count)) {
            final View child = getChildAt(count - 1);
            int bottom = 0;
            if (child == null) {
                bottom = getHeight() - getPaddingBottom() - mDividerHeight;
            } else {
                // final LayoutParams lp = (LayoutParams)
                // child.getLayoutParams();
                bottom = child.getBottom()/* + lp.bottomMargin */;
            }
            drawHorizontalDivider(canvas, bottom);
        }
    }

    private void drawDividersHorizontal(Canvas canvas) {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);

            if (child != null && child.getVisibility() != GONE) {
                if (hasDividerBeforeChildAt(i)) {
                    final LayoutParams lp = (LayoutParams) child
                            .getLayoutParams();
                    final int left = child.getLeft() - lp.leftMargin/*
                                                                     * -
																	 * mDividerWidth
																	 */;
                    drawVerticalDivider(canvas, left);
                }
            }
        }

        if (hasDividerBeforeChildAt(count)) {
            final View child = getChildAt(count - 1);
            int right = 0;
            if (child == null) {
                right = getWidth() - getPaddingRight() - mDividerWidth;
            } else {
                // final LayoutParams lp = (LayoutParams)
                // child.getLayoutParams();
                right = child.getRight()/* + lp.rightMargin */;
            }
            drawVerticalDivider(canvas, right);
        }
    }

    private void drawHorizontalDivider(Canvas canvas, int top) {
        mDivider.setBounds(getPaddingLeft() + mDividerPadding, top,
                getWidth() - getPaddingRight() - mDividerPadding, top + mDividerHeight);
        mDivider.draw(canvas);
    }

    private void drawVerticalDivider(Canvas canvas, int left) {
        mDivider.setBounds(left, getPaddingTop() + mDividerPadding,
                left + mDividerWidth, getHeight() - getPaddingBottom() - mDividerPadding);
        mDivider.draw(canvas);
    }

    private boolean hasDividerBeforeChildAt(int childIndex) {
        if (childIndex == 0 || childIndex == getChildCount()) {
            return false;
        }
        if ((mShowDividers & SHOW_DIVIDER_MIDDLE) != 0) {
            boolean hasVisibleViewBefore = false;
            for (int i = childIndex - 1; i >= 0; i--) {
                if (getChildAt(i).getVisibility() != GONE) {
                    hasVisibleViewBefore = true;
                    break;
                }
            }
            return hasVisibleViewBefore;
        }
        return false;
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mIndexForSelection = position;
        mSelectionOffset = positionOffset;
        invalidate();
    }

    public void onPageSelected(int position) {

    }

    public void onPageChanged(int position) {
        mIndexForSelection = position;
        mSelectionOffset = 0.0F;
        invalidate();
    }

    public void setUnderlineWidth(int widthPX) {
        this.underLineWidth = widthPX;
    }
}