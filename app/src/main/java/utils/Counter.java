package utils;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Icon;

import static utils.Settings.COUNT;
import static utils.Settings.LABEL;
import static utils.Settings.count;
import static utils.Settings.label;

/**
 * Created by Melad Khajepour
 */

public class Counter {

    public static Icon createIcon() {

        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(144, 144, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        Rect bounds = new Rect();
        String txt = getCount().toString();

        paint.setTextSize(140.0f);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.getTextBounds(txt, 0, txt.length(), bounds);

        canvas.setBitmap(bitmap);
        canvas.drawText(txt, 0, txt.length(), 72, 72 + (bounds.bottom - bounds.top) / 2, paint);

        return Icon.createWithBitmap(bitmap);
    }

    public static void incrementCount() {
        count++;
    }

    public static void resetCount() {
        count = 0;
    }

    public static Integer getCount () {

        if(count == null) {
            count = 0;
        }

        return count;
    }

    public static void setLabel(String newLabel) {
        label = newLabel;
    }

    public static void resetLabel() {
        label = "";
    }

    public static String getLabel() {

        if(label == null) {
            label = "";
        }

        return label;
    }

    public static void saveTile(SharedPreferences prefs) {

        prefs.edit()
                .putInt(COUNT, getCount())
                .putString(LABEL, getLabel())
                .apply();
    }
}
