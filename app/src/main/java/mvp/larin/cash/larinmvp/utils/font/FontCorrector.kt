package com.likotv.common.utils.font

import android.content.Context
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AlignmentSpan
import android.text.style.UnderlineSpan
import mvp.larin.cash.larinmvp.utils.font.TypefaceSpan

/**
 * Created by mohammad on 8/6/14.
 */
object FontCorrector {

    fun corrector(ctx: Context, input: String, fontName: FontNames): SpannableString {
        val s = SpannableString(input)
        s.setSpan(AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE), 0, s.length - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        s.setSpan(TypefaceSpan(ctx, fontName), 0, s.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return s
    }

    fun underliner(input: String): SpannableString {
        val s = SpannableString(input)
        s.setSpan(UnderlineSpan(), 0, s.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return s
    }

}
