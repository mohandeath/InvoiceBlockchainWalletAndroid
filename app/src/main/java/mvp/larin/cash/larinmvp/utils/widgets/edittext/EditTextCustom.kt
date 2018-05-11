package mvp.larin.cash.larinmvp.utils.widgets.edittext

import android.content.Context
import android.support.design.widget.TextInputEditText
import android.util.AttributeSet
import android.widget.EditText
import mvp.larin.cash.larinmvp.utils.font.FontFace

/**
 * Created by mohammadmoradyar on 9/15/17.
 */
class EditTextCustom : TextInputEditText {

    constructor(context: Context?) : super(context) {
        initialize()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initialize()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
    }

    private fun initialize() {
        typeface = FontFace.getInstance(context)?.iranSans
    }
}