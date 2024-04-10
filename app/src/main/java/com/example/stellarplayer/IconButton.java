package com.example.stellarplayer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.drawable.DrawableCompat;

public class IconButton extends AppCompatButton {

    public IconButton(Context context) {
        super(context);
    }

    public IconButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IconButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        adjustIconSize();
    }

    private void adjustIconSize() {
        Drawable drawable = getCompoundDrawables()[0]; // Assuming the icon is set to the start (left)
        if (drawable != null) {
            drawable = DrawableCompat.wrap(drawable);
            drawable.setBounds(0, 0, getHeight(), getHeight());
            setCompoundDrawables(drawable, null, null, null);
        }
    }
}
