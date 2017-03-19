package utils;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Icon;

/**
 * Created by Melad Khajepour
 */

public class TileUtils {

    private static Integer count = 0;
    private static String label = "";

    public TileUtils(SharedPreferences prefs) {
        count = prefs.getInt("count", 0);
        label = prefs.getString("label", "");
    }

    public static Icon createIcon() {

        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(144, 144, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        Rect bounds = new Rect();
        String txt = count.toString();

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
        count += 1;
    }

    static void resetCount() {
        count = 0;
    }

    public static Integer getCount () {
        return count;
    }

    static void setLabel(String newLabel) {
        label = newLabel;
    }

    public static String getLabel() {
        return label;
    }
}
