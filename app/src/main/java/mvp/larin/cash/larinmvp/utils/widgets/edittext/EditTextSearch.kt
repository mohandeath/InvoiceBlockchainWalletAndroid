package mvp.larin.cash.larinmvp.utils.widgets.edittext

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.View.OnFocusChangeListener
import mvp.larin.cash.larinmvp.utils.font.FontFace

/**
 * Created by mohammadmoradyar on 8/20/17.
 */

class EditTextSearch : AppCompatEditText, TextWatcher {

    var onTextChangedOp: (str: String) -> Unit = {}

    var onFocusChangedOp: (hasFocus: Boolean) -> Unit = {}
    private var focusListener: View.OnFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
        onFocusChangedOp(hasFocus)
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        typeface = FontFace.getInstance(context)!!.iranSans
        onFocusChangeListener = focusListener
        addTextChangedListener(this)
    }

    val trimText: String
        get() = text.toString().trim { it <= ' ' }

    override fun afterTextChanged(editable: Editable?) {
        onTextChangedOp(editable.toString().trim())
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
    }
}
