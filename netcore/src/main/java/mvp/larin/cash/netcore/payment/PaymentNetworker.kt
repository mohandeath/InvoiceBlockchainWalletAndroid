package mvp.larin.cash.netcore.payment

import mvp.larin.cash.netcore.common.BaseNetWorker
import mvp.larin.cash.netcore.common.Const
import mvp.larin.cash.netcore.common.NetWorkLoadListener
import mvp.larin.cash.netcore.common.PresenterLayer
import mvp.larin.cash.netcore.data.BaseResponse
import mvp.larin.cash.netcore.data.Invoice
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by mohandeath on 5/13/2018 AD.
 */
interface PaymentService{
    @GET(Const.URL.INVOICE_LIST)
    fun getInvoiceList(@Query("customer_id") customer_wallet:String): Call<BaseResponse<Invoice>>

}
class PaymentNetworker(presenterLayer: PresenterLayer):BaseNetWorker<PaymentService>(presenterLayer, PaymentService::class.java){
    fun invoiceList( customer_wallet: String,listener:NetWorkLoadListener<BaseResponse<Invoice>>){
        execute(service.getInvoiceList(customer_wallet),listener)

    }

}
