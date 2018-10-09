package com.eros.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by viva on 16/5/31.
 */
public class CellPointTextView extends TextView {

    private Context context;
    private int backgroundColor = 0;
    private int textColor = 0;
    private Paint paint;

    public CellPointTextView(Context context) {
        this(context, null);
        this.context = context;
    }

    public CellPointTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPaint();
    }

    public CellPointTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, null);
        this.context = context;
        initPaint();

    }

    public CellPointTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, null);
        this.context = context;
        initPaint();
    }

    private void initPaint() {
        try {
            Drawable background = getBackground();
            ColorDrawable colorDrawable = (ColorDrawable) background;
            int color = colorDrawable.getColor();

            paint = new Paint();
            paint.setColor(color);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);//抗锯齿
            paint.setDither(true);//防抖动

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (text == null) {
            setVisibility(GONE);
        } else if (text.toString().equals("")) {
            setVisibility(GONE);
        } else if (text.toString().equals("⋅⋅⋅")) {

        } else {
            int textInt = Integer.parseInt(text.toString());
            if (textInt == 0) {
                setVisibility(GONE);
            } else if (textInt == 1) {
                setVisibility(VISIBLE);
            } else if (textInt >= 100) {
                setVisibility(VISIBLE);
                text = "⋅⋅⋅";
            } else {
                setVisibility(VISIBLE);
            }
        }
        super.setText(text, type);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        if (paint != null) {
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.max(getWidth(), getHeight()) / 2, paint);
        }
        super.onDraw(canvas);
    }
}
