package mvp.larin.cash.larinmvp.utils.font;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by mohammad on 1/22/14.
 */
public class FontFace {

    @Nullable
    private static FontFace instance;
    @Nullable
    private Typeface IranSans = null;
    private Typeface IRANSans_Medium = null;
    private Typeface IRANSansMobile_Bold = null;

    private FontFace(@NonNull Context ctx) {
        try {
            if (IranSans == null)
                IranSans = Typeface.createFromAsset(ctx.getResources().getAssets(), "fonts/IRANSans.ttf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (IRANSans_Medium == null)
                IRANSans_Medium = Typeface.createFromAsset(ctx.getResources().getAssets(), "fonts/IRANSans_Medium.ttf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (IRANSansMobile_Bold == null)
                IRANSansMobile_Bold = Typeface.createFromAsset(ctx.getResources().getAssets(), "fonts/IRANSansMobile_Bold.ttf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public static FontFace getInstance(@NonNull Context _ctx) {
        if (instance == null) {
            instance = new FontFace(_ctx);
        }
        return instance;
    }

    @Nullable
    public Typeface getIranSans() {
        return IranSans;
    }

    public Typeface getIRANSans_Medium() {
        return IRANSans_Medium;
    }

    public Typeface getIRANSansMobile_Bold() {
        return IRANSansMobile_Bold;
    }
}
