package mvp.larin.cash.netcore.data

import com.google.gson.Gson

/**
 * Created by mohandeath on 5/11/2018 AD.
 */
data class ExtraMessage(val message: String?, val errorType: ErrorType)
data class ErrorModel(val errorCode: Int = 0, val errorMessage: String = "")

enum class ErrorType(value: Int, desc: String) {
    SUCCESS(0, "SUCCESS"),
    GENERAL_ERROR(1, "GENERAL_ERROR"),
    NETWORK_UNREACHABLE_ERROR(2, "NETWORK_UNREACHABLE_ERROR"),
    AUTH_ERROR(3, "AUTH_ERROR"),
    BAD_CREDENTIAL(4, "BAD_CREDENTIAL"),
    DEVICE_COUNT_REACHED(5, "DEVICE_COUNT_REACHED"),
    INVALID_CERD(6, "INVALID_CERD"),
    DIALOG_REJECT(7, "DIALOG_REJECT")
}

open class MainModel {
    fun toJson(): String {
        return Gson().toJson(this)
    }
}
