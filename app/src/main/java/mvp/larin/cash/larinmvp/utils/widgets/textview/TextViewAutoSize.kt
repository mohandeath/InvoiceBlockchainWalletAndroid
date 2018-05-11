package mvp.larin.cash.larinmvp.utils.widgets.textview

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import mvp.larin.cash.larinmvp.utils.font.FontFace

/**
 * Created by mohammadmoradyar on 8/2/17.
 */

class TextViewAutoSize : AppCompatTextView {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    protected fun init() {
        typeface = FontFace.getInstance(context)!!.iranSans
        maxLines = 1
//        AutofitHelper.create(this)
    }
}