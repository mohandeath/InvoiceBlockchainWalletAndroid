package mvp.larin.cash.larinmvp.common

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.support.design.widget.Snackbar
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import java.io.IOException
import java.nio.charset.Charset

/**
 * Created by mohandeath on 5/13/2018 AD.
 */
class AppManager{
    companion object {
        fun pxToDp(ctx: Context, px: Int): Int {
            val displayMetrics = ctx.resources.displayMetrics
            return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
        }

        fun dpToPx(ctx: Context, dp: Int): Int {
            val displayMetrics = ctx.resources.displayMetrics
            return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
        }

        fun getScreenHeight(ctx: Context): Int {
            var height = 0
            if (ctx is Activity) {
                val display = ctx.windowManager.defaultDisplay
                val size = Point()
                display.getSize(size)
                height = size.y
            }
            return height
        }

        fun getScreenWidth(ctx: Context): Int {
            var width = 0
            if (ctx is Activity) {
                val display = ctx.windowManager.defaultDisplay
                val size = Point()
                display.getSize(size)
                width = size.x
            }
            return width
        }

        fun loadJSONFromAsset(ctx: Context, jsonFileName: String): String {
            var json: String
            try {
                val `is` = ctx.assets.open(jsonFileName)
                val size = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                json = String(buffer, Charset.forName("UTF-8"))
            } catch (ex: IOException) {
                ex.printStackTrace()
                json = ""
            }
            return json
        }

        fun numberToDigit(num: Int): String {
            return if (num < 10) "0${num}" else "$num"
        }

        fun english2Persian(input: String): String {
            return input.replace("1", "۱")
                    .replace("2", "۲")
                    .replace("3", "۳")
                    .replace("4", "۴")
                    .replace("5", "۵")
                    .replace("6", "۶")
                    .replace("7", "۷")
                    .replace("8", "۸")
                    .replace("9", "۹")
                    .replace("0", "۰")
        }

        fun persian2English(input: String): String {
            return input.replace("۱", "1")
                    .replace("۲", "2")
                    .replace("۳", "3")
                    .replace("۴", "4")
                    .replace("۵", "5")
                    .replace("۶", "6")
                    .replace("۷", "7")
                    .replace("۸", "8")
                    .replace("۹", "9")
                    .replace("۰", "0")
        }

        fun arabicToPersian(str: String): String {
            return str.replace("ي", "ی")
                    .replace("ك", "ک")
                    .replace("٤", "۴")
                    .replace("٥", "۵")
        }

        fun persianToArabic(str: String): String {
            return str.replace("ی", "ي")
                    .replace("ک", "ك")
                    .replace("۴", "٤")
                    .replace("۵", "٥")
        }

        fun closeKeyBoard(ctx: Context, view: View) {
            val imm = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
        }

        fun hideKeyboard(ctx: Context) {
            val inputManager = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val v = (ctx as Activity).currentFocus ?: return
            inputManager.hideSoftInputFromWindow(v.windowToken, 0)
        }

        fun showMessage(view: View, message: String) {
                Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
        }


    }
}