package mvp.larin.cash.larinmvp

import android.content.Context
import android.os.Environment
import com.orhanobut.hawk.Hawk

/**
 * Created by mohandeath on 5/11/2018 AD.
 */
import android.support.multidex.MultiDexApplication

/**
 * Created by mohammadmoradyar on 8/23/17.
 */
class RootApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        val context = this

        Hawk.init(this)
                .build()
    }



}
