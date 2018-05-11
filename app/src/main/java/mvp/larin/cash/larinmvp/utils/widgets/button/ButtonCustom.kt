package mvp.larin.cash.larinmvp.utils.widgets.button

import android.content.Context
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import mvp.larin.cash.larinmvp.utils.font.FontFace


/**
 * Created by mohammadmoradyar on 9/2/17.
 */

class ButtonCustom : AppCompatButton {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        typeface = FontFace.getInstance(context)?.iranSans
    }
}
