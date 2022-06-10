package com.quannm18.quanlykho.custom_text;

import android.content.Context;
import android.graphics.Typeface;

public class Utils {
    private static Typeface sen_bold;
    private static Typeface sen_extrabold;
    private static Typeface sen_regular;

    public static Typeface getSen_bold(Context context) {
        if (sen_bold == null){
            sen_bold = Typeface.createFromAsset(context.getAssets(),"font/sen_bold.otf");
        }
        return sen_bold;
    }

    public static Typeface getSen_extrabold(Context context) {
        if (sen_extrabold == null){
            sen_extrabold = Typeface.createFromAsset(context.getAssets(),"font/sen_extrabold.otf");
        }
        return sen_extrabold;
    }

    public static Typeface getSen_regular(Context context) {
        if (sen_regular == null){
            sen_regular = Typeface.createFromAsset(context.getAssets(),"font/sen_regular.otf");
        }
        return sen_regular;
    }
}
