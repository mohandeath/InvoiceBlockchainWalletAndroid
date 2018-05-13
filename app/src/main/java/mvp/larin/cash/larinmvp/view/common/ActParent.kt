package mvp.larin.cash.larinmvp.view.common

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager

/**
 * Created by mohandeath on 5/11/2018 AD.
 */
open class ActParent : AppCompatActivity() {
    protected var isLoaded = false


    protected val ctx: Context = this

    fun lockScreen() {
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    fun unlockScreen() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

}