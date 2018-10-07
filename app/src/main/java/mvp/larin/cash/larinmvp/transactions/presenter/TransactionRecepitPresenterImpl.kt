package mvp.larin.cash.larinmvp.transactions.presenter

import android.content.Context
import mvp.larin.cash.larinmvp.common.BasePresenter
import mvp.larin.cash.larinmvp.common.BaseView
import mvp.larin.cash.larinmvp.common.PresenterLayerImpl
import mvp.larin.cash.netcore.common.NetWorkLoadListener
import mvp.larin.cash.netcore.common.PresenterLayer
import mvp.larin.cash.netcore.data.BaseResponse
import mvp.larin.cash.netcore.data.ExtraMessage
import mvp.larin.cash.netcore.data.GenericModel
import mvp.larin.cash.netcore.payment.PaymentNetworker

/**
 * Created by mohandeath on 5/15/2018 AD.
 */
interface TransactionView : BaseView {
    fun onTransactionResult(state: Boolean, data: GenericModel?, message: ExtraMessage?)

}

interface TransactionPresenter : BasePresenter {
    fun payInvoice(invoiceID: String)
}


class TransactionRecepitPresenterImpl(ctx: Context, private val view: TransactionView) : TransactionPresenter, NetWorkLoadListener<GenericModel> {
    private val presenterLayer: PresenterLayer = PresenterLayerImpl(ctx)
    override fun onNetWorkLoad(state: Boolean, data: GenericModel?, message: ExtraMessage?) {
        view.dismissProgress()
        if (state && data != null && data.success)
            view.onTransactionResult(data.success,data,message)
        else
            view.onTransactionResult(false,null,message)


    }

    override fun retry() {

    }


    override fun payInvoice(invoiceID: String) {
        view.showProgress()
        PaymentNetworker(presenterLayer).payInvoice(invoiceID, this)

    }

}