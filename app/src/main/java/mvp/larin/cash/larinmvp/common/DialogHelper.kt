package mvp.larin.cash.larinmvp.common

import android.app.Dialog
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.widget.TextView
import com.afollestad.materialdialogs.GravityEnum
import com.afollestad.materialdialogs.MaterialDialog
import mvp.larin.cash.larinmvp.R
import mvp.larin.cash.larinmvp.utils.font.FontFace
import mvp.larin.cash.netcore.data.Invoice

/**
 * Created by mohandeath on 5/13/2018 AD.
 */
class DialogHelper{
    companion object {
        fun showActionableDialog(ctx: Context, positiveButtonText: String, negativeButtonText: String, message: String, positiveClick: () -> Unit, negativeClick: () -> Unit) {
            val dlg = makeBaseDialog(ctx, message)
                    .positiveText(positiveButtonText)
                    .negativeText(negativeButtonText)
                    .onPositive { dialog, which ->
                        positiveClick()
                        dialog.dismiss()
                    }
                    .onNegative { dialog, which ->
                        dialog.dismiss()
                        negativeClick()
                    }
                    .dismissListener { negativeClick() }

                    .build()
            dlg.show()
        }

        fun showInvoiceDetailDialog(ctx: Context,invoice:Invoice, walletPayClicked: () -> Unit, instantPayClicked: () -> Unit){
            val view = LayoutInflater.from(ctx).inflate(R.layout.dlg_invoice_confirm, null)
            val dlg = Dialog(ctx)
            dlg.setContentView(view)
            var label_txt:TextView = dlg.findViewById(R.id.txt_lael)
            var txt_vendor_name:TextView = dlg.findViewById(R.id.txt_vendor_name)
            var txt_invoice_code:TextView = dlg.findViewById(R.id.txt_invoice_code)
            var support_phone:TextView = dlg.findViewById(R.id.support_phone)
            var txt_vendor_wallet:TextView = dlg.findViewById(R.id.txt_vendor_wallet)
            var txt_desc:TextView = dlg.findViewById(R.id.txt_desc)
            var btn_pay_wallet:CardView = dlg.findViewById(R.id.btn_pay_wallet)
            var btn_instant_pay:CardView = dlg.findViewById(R.id.btn_instant_pay)
            label_txt.text = "Title : ${invoice.title}"
            txt_vendor_name.text = "Vendor's name: ${invoice.vendor_name}"
            txt_invoice_code.text = "Code : ${invoice.invoice_code}"
            support_phone.text = "Support: ${invoice.phone}"
            var address_len = invoice.vendor_wallet.length
            txt_vendor_wallet.text = "Dest Address: ${invoice.vendor_wallet.substring(0,5)}...${invoice.vendor_wallet.substring(address_len-3,address_len-1)}"
            txt_desc.text = invoice.description
            btn_pay_wallet.setOnClickListener { walletPayClicked.invoke() }
            btn_instant_pay.setOnClickListener { instantPayClicked.invoke() }

            /*
                here it goes main codes of dialog
            * */

            dlg.show()
        }
        private fun makeBaseDialog(ctx: Context, message: String): MaterialDialog.Builder {
            return MaterialDialog.Builder(ctx)
                    .content(message)
                    .titleGravity(GravityEnum.END)
                    .titleColorRes(R.color.white)
                    .contentColorRes(R.color.white)
                    .contentGravity(GravityEnum.END)
                    .backgroundColorRes(R.color.colorPrimary)
                    .buttonsGravity(GravityEnum.END)
                    .negativeColor(ContextCompat.getColor(ctx, R.color.colorRed))
                    .typeface(FontFace.getInstance(ctx)?.iranSans_Medium, FontFace.getInstance(ctx)?.iranSans)
                    .positiveText("Approve")
        }

    }
}