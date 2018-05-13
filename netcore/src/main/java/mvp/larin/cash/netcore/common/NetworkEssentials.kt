package mvp.larin.cash.netcore.common

/**
 * Created by mohandeath on 5/11/2018 AD.
 */

import android.util.Log
import com.google.gson.Gson
import mvp.larin.cash.netcore.BuildConfig
import mvp.larin.cash.netcore.data.ErrorModel
import mvp.larin.cash.netcore.data.ErrorType
import mvp.larin.cash.netcore.data.ExtraMessage
import mvp.larin.cash.netcore.data.MainModel
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

object Const {

    object URL {
        private const val VERSION = "1"
        const val INVOICE_LIST = "v$VERSION/invoice/list"

    }

    val AUTH_KEY = "Authorization"
    val IS_GUEST_KEY = "Is-Guest"
}

interface NetWorkLoadListener<T> {

    fun onNetWorkLoad(state: Boolean, data: T?, message: ExtraMessage?)

}

class AuthException : IOException()


interface PresenterLayer {

    fun getDefaultMessage(): String

    fun getAuthExceptionMessage(): String

    fun getNetWorkUnreachableMessage(): String

    fun getBaseUrl(): String

    fun getBaseLoggerUrl(): String

    fun getAuthToken(): String

    fun setAuthToken(token: String)

    fun setAuthBody(body: String, isSubProfile: Boolean)


    fun getCacheDir(): File

    fun showLoginPage(f: () -> Unit)

    fun isReloginAvailable(): Boolean

    fun reLogin()

    fun getUserName(): String

    fun setIsGuestFlag(isGuest: Boolean)

    fun isDebugMode(): Boolean

    fun getSuccessMessage(): String

}

//AES/256/CBC


class MyCallBack<T>(val presenterLayer: PresenterLayer, private val listener: NetWorkLoadListener<T>) : Callback<T> where T : MainModel {
    var loginAttemptCount = 0
    ///api/v2/heartbeat
    override fun onResponse(call: Call<T>?, response: Response<T>?) {

        when {
            response?.code() == 200 -> listener.onNetWorkLoad(true, response.body(), ExtraMessage(presenterLayer.getSuccessMessage(), ErrorType.SUCCESS))
            response?.code() == 401 -> {
                goLogin({})
            }
            response?.code() == 400 -> {
                goLogin({})
            }
            response?.code() == 406 -> listener.onNetWorkLoad(false, response.body(), ExtraMessage(makeReturnMessage(response), ErrorType.BAD_CREDENTIAL))
            response?.code() == 412 -> listener.onNetWorkLoad(false, response.body(), ExtraMessage(makeReturnMessage(response), ErrorType.DEVICE_COUNT_REACHED))
            response?.code() == 409 -> listener.onNetWorkLoad(false, response.body(), ExtraMessage(makeReturnMessage(response), ErrorType.INVALID_CERD))

//            response?.code() == 422 -> {
//            }
            else -> listener.onNetWorkLoad(false, response?.body(), ExtraMessage(makeReturnMessage(response), ErrorType.GENERAL_ERROR))
        }
    }

    private fun goLogin(f: () -> Unit) {
        presenterLayer.setAuthToken("")
        presenterLayer.showLoginPage(f)
    }

    private fun makeReturnMessage(response: Response<T>?): String {
        return if (presenterLayer.isDebugMode()) {
            val strJson = response?.errorBody()?.string()
            try {
                Gson().fromJson(strJson, ErrorModel::class.java).errorMessage
            } catch (e: Exception) {
                presenterLayer.getDefaultMessage()
            }
        } else
            presenterLayer.getDefaultMessage()
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        when (t) {

            is IOException ->

                listener.onNetWorkLoad(false, null, ExtraMessage(presenterLayer.getNetWorkUnreachableMessage(), ErrorType.NETWORK_UNREACHABLE_ERROR))

            is AuthException -> listener.onNetWorkLoad(false, null, ExtraMessage(presenterLayer.getAuthExceptionMessage(), ErrorType.AUTH_ERROR))
            else -> listener.onNetWorkLoad(false, null, ExtraMessage(presenterLayer.getDefaultMessage(), ErrorType.GENERAL_ERROR))
        }
    }

}


open class BaseNetWorker<T>(private val presenterLayer: PresenterLayer, tClass: Class<T>) {

    private val timeOut: Long = 30 * 1000
    private val cacheSize: Long = 10 * 1024 * 1024 // 10 MB
    private var cache = Cache(presenterLayer.getCacheDir(), cacheSize)


    private val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(Logger(presenterLayer))
            .cache(cache)
            .followRedirects(false)
            .followSslRedirects(false)
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .writeTimeout(timeOut, TimeUnit.SECONDS)
            .build()


    private val retrofit = Retrofit
            .Builder()
            .baseUrl(presenterLayer.getBaseUrl())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val service = retrofit.create(tClass)

    fun <K> execute(call: Call<K>, listener: NetWorkLoadListener<K>) where K : MainModel {
        call.enqueue(MyCallBack(presenterLayer, listener))
    }




}

class TokenInterceptor(val presenterLayer: PresenterLayer) : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): okhttp3.Response {
        val request = chain?.request()
        val newRequestBuilder = request?.newBuilder()
        if (presenterLayer.getAuthToken() != "") {
            newRequestBuilder?.addHeader(Const.AUTH_KEY, presenterLayer.getAuthToken())
        }
        newRequestBuilder?.addHeader("Device-Type", "Android")
        val newRequest = newRequestBuilder?.build()
        val response = chain?.proceed(newRequest)
        if (response != null) {
            if (response.headers() != null) {
                if (response.headers().names() != null) {
                    if (response.headers().names().any { it == Const.AUTH_KEY }) {
                        val authToken = response.header(Const.AUTH_KEY)
                        if (presenterLayer.getAuthToken() != authToken) {
                            presenterLayer.setAuthToken(authToken!!)
                        }
                    }
                    if (response.headers().names().any { it == Const.IS_GUEST_KEY }) {
                        val isGuest = response.header(Const.IS_GUEST_KEY)?.toBoolean()
                        if (isGuest != null)
                            presenterLayer.setIsGuestFlag(isGuest)
                    }
                }
            }
        }
        return response!!
    }
}

class RedirectInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): okhttp3.Response {
        val oldRequest = chain?.request()
        val response = chain?.proceed(oldRequest)
        return if (response?.code() == 302 || response?.code() == 301) {
            val newRequest = oldRequest
                    ?.newBuilder()
                    ?.url(response.header("Location"))
                    ?.build()
            chain.proceed(newRequest)
        } else
            response!!
    }
}

class Logger(val presenterLayer: PresenterLayer) : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): okhttp3.Response {
        if (!presenterLayer.isDebugMode()) {
            return chain?.proceed(chain.request())!!
        } else {
            val request = chain?.request()
            val headers = request?.headers()
            headers?.names()?.forEach {
                Log.i("XXXX-headers", "$it : ${headers.get(it)}")
            }
            Log.i(BuildConfig.APPLICATION_ID, "call ==> " + request?.url())
            val response = chain?.proceed(request)
            val responseBody = response?.body()
            val source = responseBody!!.source()
            source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
            val buffer = source.buffer()
            val strResponse = buffer.clone().readString(Charset.forName("UTF8")).toString()
            Log.e(BuildConfig.APPLICATION_ID, "status code : ${response.code()} and ret ==> " + strResponse)
            return response
        }
    }


}
