package mvp.larin.cash.larinmvp.view.common

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import mvp.larin.cash.larinmvp.R
import kotlinx.android.synthetic.main.act_home.*
import mvp.larin.cash.larinmvp.common.AppManager
import mvp.larin.cash.larinmvp.common.BasePresenter
import mvp.larin.cash.larinmvp.transactions.view.AdapterInvoice
import mvp.larin.cash.larinmvp.transactions.view.HomePagePresenter
import mvp.larin.cash.larinmvp.transactions.view.HomePagePresenterIMPL
import mvp.larin.cash.larinmvp.transactions.view.HomePageView
import mvp.larin.cash.netcore.data.BaseResponse
import mvp.larin.cash.netcore.data.ExtraMessage
import mvp.larin.cash.netcore.data.Invoice

class MainActivity : ActParent(),HomePageView, SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        presenter.retry()
    }

    lateinit var presenter:HomePagePresenter

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
                Toast.makeText(ctx,"clicked on ${invoice?.title}",Toast.LENGTH_LONG).show()
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
            isLoaded = true
        }

    }
}
