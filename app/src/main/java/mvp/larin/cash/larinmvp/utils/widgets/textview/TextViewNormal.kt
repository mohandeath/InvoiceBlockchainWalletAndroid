package mvp.larin.cash.larinmvp.utils.widgets.textview

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v7.content.res.AppCompatResources
import android.support.v7.widget.AppCompatTextView
import android.text.TextUtils
import android.util.AttributeSet
import mvp.larin.cash.larinmvp.R
import mvp.larin.cash.larinmvp.utils.font.FontFace

/**
 * Created by mohammadmoradyar on 8/2/17.
 */

class TextViewNormal : AppCompatTextView {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttrs(context,attrs)
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttrs(context,attrs)
        init()
    }

    protected fun init() {
        typeface = FontFace.getInstance(context)!!.iranSans
        ellipsize = TextUtils.TruncateAt.END
        maxLines = 1
    }

    private fun initAttrs(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextViewNormal)

        var drawableRight : Drawable? = null
        var drawableLeft  : Drawable? = null
        var drawableBottom: Drawable? = null
        var drawableTop   : Drawable? = null

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            drawableRight = typedArray.getDrawable(R.styleable.TextViewNormal_drawableRightCompat)
            drawableLeft  = typedArray.getDrawable(R.styleable.TextViewNormal_drawableLeftCompat)
            drawableBottom= typedArray.getDrawable(R.styleable.TextViewNormal_drawableBottomCompat)
            drawableTop   = typedArray.getDrawable(R.styleable.TextViewNormal_drawableTopCompat)
        } else { // KITKAT ( SDK v19 ) Support
            val drID = typedArray.getResourceId(R.styleable.TextViewNormal_drawableRightCompat,-1)
            val dlID = typedArray.getResourceId(R.styleable.TextViewNormal_drawableLeftCompat,-1)
            val dbID = typedArray.getResourceId(R.styleable.TextViewNormal_drawableBottomCompat,-1)
            val dtID = typedArray.getResourceId(R.styleable.TextViewNormal_drawableTopCompat,-1)

            if( drID != -1 )
                drawableRight = AppCompatResources.getDrawable(context,drID)
            if( dlID != -1 )
                drawableLeft  = AppCompatResources.getDrawable(context,dlID)
            if( dbID != -1 )
                drawableBottom= AppCompatResources.getDrawable(context,dbID)
            if( dtID != -1 )
                drawableTop   = AppCompatResources.getDrawable(context,dtID)
        }

        setCompoundDrawables(drawableLeft,drawableTop,drawableRight,drawableBottom)
        typedArray.recycle()
    }
}
