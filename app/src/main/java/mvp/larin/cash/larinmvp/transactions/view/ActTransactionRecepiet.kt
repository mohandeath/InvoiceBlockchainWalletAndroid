package mvp.larin.cash.larinmvp.transactions.view

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.act_transaction_recepit.*
import mvp.larin.cash.larinmvp.R
import mvp.larin.cash.larinmvp.common.AppManager
import mvp.larin.cash.larinmvp.common.BasePresenter
import mvp.larin.cash.larinmvp.common.DialogHelper
import mvp.larin.cash.larinmvp.transactions.presenter.HomePagePresenterIMPL
import mvp.larin.cash.larinmvp.transactions.presenter.TransactionPresenter
import mvp.larin.cash.larinmvp.transactions.presenter.TransactionRecepitPresenterImpl
import mvp.larin.cash.larinmvp.transactions.presenter.TransactionView
import mvp.larin.cash.larinmvp.view.common.ActParent
import mvp.larin.cash.netcore.data.ExtraMessage
import mvp.larin.cash.netcore.data.GenericModel

/**
 * Created by mohandeath on 5/15/2018 AD.
 */
class ActTransactionRecepiet : ActParent(), TransactionView {
    lateinit var presenter:TransactionPresenter
    lateinit var dlg:Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_transaction_recepit)
        var invoice_id = intent.getStringExtra("invoice_id")
        if (!isLoaded) {
            presenter = TransactionRecepitPresenterImpl(ctx, this)
            presenter.payInvoice(invoice_id)
            isLoaded = true
        }

    }

    override fun showProgress() {
        dlg = DialogHelper.makeLoadingDialog(ctx)
        rec_overlay.visibility = View.VISIBLE
        dlg.show()
    }

    override fun dismissProgress() {
        rec_overlay.animate().alpha(0f).rotation(3000f)
        dlg.dismiss()

    }

    override fun onError(basePresenter: BasePresenter, message: String) {
        view.setBackgroundColor(Color.RED)
        AppManager.showMessage(rec_overlay,message)
    }

    override fun onTransactionResult(state: Boolean, data: GenericModel?, message: ExtraMessage?) {
        if(state)
            txt_tx_hash.text = data!!.data
    }

}