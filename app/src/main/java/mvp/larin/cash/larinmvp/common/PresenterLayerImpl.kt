package mvp.larin.cash.larinmvp.common

import android.content.Context
import mvp.larin.cash.larinmvp.BuildConfig
import mvp.larin.cash.netcore.common.PresenterLayer
import java.io.File

/**
 * Created by mohandeath on 5/13/2018 AD.
 */
class PresenterLayerImpl(val ctx: Context) : PresenterLayer {
    override fun getDefaultMessage(): String {
        return "Error!"
    }

    override fun getAuthExceptionMessage(): String {
        return "There's an issue with your authentication"
    }

    override fun getNetWorkUnreachableMessage(): String {
        return "Network is unreachable"
    }

    override fun getBaseUrl(): String {
        return "${BuildConfig.HTTP_BASE_URL}:${BuildConfig.PORT}/"

    }

    override fun getBaseLoggerUrl(): String {
        return ""
    }

    override fun getAuthToken(): String {
        return ""
    }

    override fun setAuthToken(token: String) {
    }

    override fun setAuthBody(body: String, isSubProfile: Boolean) {
    }

    override fun getCacheDir(): File {
        return ctx.cacheDir
    }

    override fun showLoginPage(f: () -> Unit) {

    }

    override fun isReloginAvailable(): Boolean {
        return true
    }

    override fun reLogin() {
    }

    override fun getUserName(): String {
        return "AlirezaKian"
    }


    override fun setIsGuestFlag(isGuest: Boolean) {

    }

    override fun isDebugMode(): Boolean {
        return true
    }

    override fun getSuccessMessage(): String {
        return "your transaction has been completed succesfully"
    }

}
