package mvp.larin.cash.larinmvp.utils.widgets.imageview

import android.content.Context
import android.os.Build
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

/**
 * Created by mohammadmoradyar on 8/2/17.
 */

class ImageViewRoundedCornerBase : AppCompatImageView {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    protected fun init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            clipToOutline = true

        }
    }
}
