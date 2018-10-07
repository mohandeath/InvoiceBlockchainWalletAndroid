package mvp.larin.cash.larinmvp.view.common

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import mvp.larin.cash.larinmvp.R
import kotlinx.android.synthetic.main.act_home.*
import mvp.larin.cash.larinmvp.common.AppManager
import mvp.larin.cash.larinmvp.common.BasePresenter
import mvp.larin.cash.larinmvp.common.DialogHelper
import mvp.larin.cash.larinmvp.transactions.view.AdapterInvoice
import mvp.larin.cash.larinmvp.transactions.presenter.HomePagePresenter
import mvp.larin.cash.larinmvp.transactions.presenter.HomePagePresenterIMPL
import mvp.larin.cash.larinmvp.transactions.presenter.HomePageView
import mvp.larin.cash.larinmvp.transactions.view.ActTransactionRecepiet
import mvp.larin.cash.netcore.data.BaseResponse
import mvp.larin.cash.netcore.data.ExtraMessage
import mvp.larin.cash.netcore.data.GenericModel
import mvp.larin.cash.netcore.data.Invoice

class MainActivity : ActParent(), HomePageView, SwipeRefreshLayout.OnRefreshListener {
    override fun onBalanceSynched(tate: Boolean, data: GenericModel?, message: ExtraMessage?) {
        if (tate)
            balance.text = "${data!!.data} Rials"
    }

    override fun onRefresh() {
        presenter.retry()
    }

    lateinit var presenter: HomePagePresenter

    override fun showProgress() {
        refreshLayout.isRefreshing = true
    }

    override fun dismissProgress() {
        refreshLayout.isRefreshing = false
    }

    override fun onError(basePresenter: BasePresenter, message: String) {
        AppManager.showMessage(refreshLayout,"Error : $message")
    }

    override fun retriveInvoiceList(state: Boolean, data: BaseResponse<Invoice>?, message: ExtraMessage?) {
        if (data != null && state){
            rv.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
            rv.adapter = AdapterInvoice(ctx,data!!.results,{action,invoice->
                DialogHelper.showInvoiceDetailDialog(ctx,invoice!!,{
                    var intent = Intent(ctx,ActTransactionRecepiet::class.java)
                    intent.putExtra("invoice_id",invoice.invoice_code)
                    startActivity(intent)

                },{})
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_home)
        refreshLayout.setOnRefreshListener(this)

        if (!isLoaded) {
            presenter = HomePagePresenterIMPL(ctx, this)
            presenter.loadInvoiceList("")
            presenter.synchBalance("0x910751e05318989bbe9fd15448448c9c9c9d4e98")
            isLoaded = true
        }

    }

    override fun onResume() {
        super.onResume()
        presenter.synchBalance("0x910751e05318989bbe9fd15448448c9c9c9d4e98")
    }
}
