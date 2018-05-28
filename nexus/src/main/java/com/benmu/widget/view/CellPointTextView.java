package com.benmu.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by viva on 16/5/31.
 */
public class CellPointTextView extends TextView {

    private Context context;
    private int backgroundColor = 0;
    private int textColor = 0;

    public CellPointTextView(Context context) {
        this(context,null);
        this.context = context;
    }

    public CellPointTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CellPointTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context,null);
        this.context = context;
    }

    public CellPointTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context,null);
        this.context = context;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if(text == null){
            setVisibility(GONE);
        } else if (text.toString().equals("")) {
            setVisibility(GONE);
        } else if(text.toString().equals("⋅⋅⋅")){

        } else {
            int textInt = Integer.parseInt(text.toString());
            if(textInt == 0){
                setVisibility(GONE);
            }else if(textInt==1) {
                setVisibility(VISIBLE);
            }else if(textInt>=100) {
                setVisibility(VISIBLE);
                text = "⋅⋅⋅";
            }else {
                setVisibility(VISIBLE);
            }
        }
        super.setText(text, type);
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
