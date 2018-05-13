package mvp.larin.cash.larinmvp.transactions.view

import android.content.Context
import android.util.Log
import mvp.larin.cash.larinmvp.common.BasePresenter
import mvp.larin.cash.larinmvp.common.BaseView
import mvp.larin.cash.larinmvp.common.PresenterLayerImpl
import mvp.larin.cash.netcore.common.NetWorkLoadListener
import mvp.larin.cash.netcore.common.PresenterLayer
import mvp.larin.cash.netcore.data.BaseResponse
import mvp.larin.cash.netcore.data.ExtraMessage
import mvp.larin.cash.netcore.data.Invoice
import mvp.larin.cash.netcore.payment.PaymentNetworker

/**
 * Created by mohandeath on 5/13/2018 AD.
 */
interface HomePageView :BaseView{
    fun retriveInvoiceList(state: Boolean, data: BaseResponse<Invoice>?, message: ExtraMessage?)



}

interface HomePagePresenter:BasePresenter{
    fun loadInvoiceList(walletId: String)
}


class HomePagePresenterIMPL(ctx: Context, private val view: HomePageView):HomePagePresenter,NetWorkLoadListener<BaseResponse<Invoice>>{

    private val presenterLayer: PresenterLayer = PresenterLayerImpl(ctx)


    override fun retry() {
        loadInvoiceList("")
    }

    override fun loadInvoiceList(walletId: String) {
        view.showProgress()
        PaymentNetworker(presenterLayer).invoiceList(walletId,this)
    }

    override fun onNetWorkLoad(state: Boolean, data: BaseResponse<Invoice>?, message: ExtraMessage?) {
        view.dismissProgress()
        if (state && data!= null){
            view.retriveInvoiceList(state,data,message)
        }else {
            Log.e("ERR","here's the cause of error :  ")
            view.onError(this,"there's an error here ")
        }
    }

}