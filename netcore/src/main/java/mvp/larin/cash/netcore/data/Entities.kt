package mvp.larin.cash.netcore.data

import com.google.gson.Gson
import java.util.*

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

data class GenericModel(val success:Boolean,val error:String="",val data:String=""):MainModel()
data class BaseResponse<Tt>(val count:Int,val next: Int,val previous:Int,val results:List<Tt>):MainModel()
data class Invoice(val is_paid:Boolean=false,val vendor_name:String, val created:String, val owner:BlockchainAccount, val to:String="", val vendor:String="", val image:String="", val title:String="", val description:String="", val invoice_code:String="", val amount:Int, val phone:String="" )
data class BlockchainAccount(val name:String,val wallet_id:String,val balance:Int)