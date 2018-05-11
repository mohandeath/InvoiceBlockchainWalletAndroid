package mvp.larin.cash.larinmvp.utils.font;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.util.LruCache;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
public class TypefaceSpan extends MetricAffectingSpan {
    /**
     * An <code>LruCache</code> for previously loaded typefaces.
     */
    @NonNull
    private static LruCache<String, Typeface> sTypefaceCache = new LruCache<>(12);

    @Nullable
    private Typeface mTypeface;

    /**
     * Load the {@link Typeface} and apply to a {@link android.text.Spannable}.
     */
    public TypefaceSpan(@NonNull Context ctx, @NonNull FontNames fontName) {

        switch (fontName) {
            case IRANSans:
                mTypeface = FontFace.getInstance(ctx).getIranSans();
                break;
            case IRANSans_Medium:
                mTypeface = FontFace.getInstance(ctx).getIRANSans_Medium();
                break;
        }

        // Cache the loaded Typeface
        sTypefaceCache.put(fontName.name(), mTypeface);

    }

    /**
     * Note: This flag is required for proper typeface rendering
     *
     * @param p TextPlain parameter
     */
    @Override
    public void updateMeasureState(@NonNull TextPaint p) {
        p.setTypeface(mTypeface);

        // Note: This flag is required for proper typeface rendering
        p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }

    @Override
    public void updateDrawState(@NonNull TextPaint tp) {
        tp.setTypeface(mTypeface);

        // Note: This flag is required for proper typeface rendering
        tp.setFlags(tp.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }
}
