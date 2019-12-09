package smartdevelop.ir.eram.showcaseviewlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Spannable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.Gravity.CENTER;

/**
 * Created by Mohammad Reza Eram  on 20/01/2018.
 */

class GuideMessageView extends LinearLayout {

    private Paint mPaint;
    private RectF mRect;

    private TextView mTitleTextView;
    private TextView mContentTextView;
    private LinearLayout navContainer;
    private TextView next;
    private TextView skip;


    GuideMessageView(Context context) {
        super(context);

        float density = context.getResources().getDisplayMetrics().density;
        setWillNotDraw(false);
        setOrientation(VERTICAL);
        setGravity(CENTER);

        mRect = new RectF();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        final int padding = (int) (10 * density);
        final int paddingBetween = (int) (3 * density);

        addTitle(context, padding, paddingBetween);
        addContent(context, padding, paddingBetween);
        addButtons(context, padding, paddingBetween);
    }

    private void addButtons(Context context, int padding, int paddingBetween) {
        navContainer = new LinearLayout(context);
        navContainer.setOrientation(HORIZONTAL);
        navContainer.setPadding(padding, paddingBetween, padding, padding);

        skip = new TextView(context);
        skip.setText(context.getResources().getString(R.string.label_skip));
        LayoutParams skipParam =
                new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        skipParam.weight = .5f;
        skip.setLayoutParams(skipParam);
        skip.setGravity(CENTER);
        navContainer.addView(skip);

        next = new TextView(context);
        next.setText(context.getResources().getString(R.string.label_next));
        LayoutParams nextParams = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        nextParams.weight = .5f;
        next.setLayoutParams(nextParams);
        next.setGravity(CENTER);
        navContainer.addView(next);

        addView(navContainer, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private void addContent(Context context, int padding, int paddingBetween) {
        mContentTextView = new TextView(context);
        mContentTextView.setTextColor(Color.BLACK);
        mContentTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        mContentTextView.setPadding(padding, paddingBetween, padding, padding);
        mContentTextView.setGravity(CENTER);
        addView(mContentTextView, new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private void addTitle(Context context, int padding, int paddingBetween) {
        mTitleTextView = new TextView(context);
        mTitleTextView.setPadding(padding, padding, padding, paddingBetween);
        mTitleTextView.setGravity(CENTER);
        mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        mTitleTextView.setTextColor(Color.BLACK);
        addView(mTitleTextView, new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }


    public void setTitle(String title) {
        if (title == null) {
            removeView(mTitleTextView);
            return;
        }
        mTitleTextView.setText(title);
    }


    public void setContentText(String content) {
        mContentTextView.setText(content);
    }

    public void setContentSpan(Spannable content) {
        mContentTextView.setText(content);
    }

    public void setContentTypeFace(Typeface typeFace) {
        mContentTextView.setTypeface(typeFace);
    }

    public void setTitleTypeFace(Typeface typeFace) {
        mTitleTextView.setTypeface(typeFace);
    }

    public void setTitleTextSize(int size) {
        mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    public void setContentTextSize(int size) {
        mContentTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    public void setColor(int color) {

        mPaint.setAlpha(255);
        mPaint.setColor(color);

        invalidate();
    }

    int location[] = new int[2];

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        this.getLocationOnScreen(location);


        mRect.set(getPaddingLeft(),
                getPaddingTop(),
                getWidth() - getPaddingRight(),
                getHeight() - getPaddingBottom());


        canvas.drawRoundRect(mRect, 15, 15, mPaint);
    }

    protected void setOnNextClicked(OnClickListener clickListener) {
        if (next != null)
            next.setOnClickListener(clickListener);
    }

    protected void setOnSkipClicked(OnClickListener clickListener) {
        if (skip != null)
            skip.setOnClickListener(clickListener);
    }
}
