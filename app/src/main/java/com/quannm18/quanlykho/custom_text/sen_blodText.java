package com.quannm18.quanlykho.custom_text;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class sen_blodText extends AppCompatTextView {
    public sen_blodText(@NonNull Context context) {

        super(context);
        setFontText();
    }

    public sen_blodText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFontText();
    }

    public sen_blodText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontText();
    }

    private void setFontText() {
        Typeface typeface = Utils.getSen_bold(getContext());
        setTypeface(typeface);
    }
}
