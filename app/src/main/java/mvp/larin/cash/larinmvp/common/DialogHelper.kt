package mvp.larin.cash.larinmvp.common

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
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

        fun showInvoiceDetailDialog(ctx: Context,invoice:Invoice, positiveClick: () -> Unit, negativeClick: () -> Unit){
            val view = LayoutInflater.from(ctx).inflate(R.layout.dlg_invoice_confirm, null)
            val dlg = MaterialDialog.Builder(ctx)
                    .title("Invoice Details")
                    .titleColorRes(R.color.white)
                    .titleGravity(GravityEnum.START)
                    .typeface(FontFace.getInstance(ctx)?.iranSans, FontFace.getInstance(ctx)?.iranSansMobile_Bold)
                    .contentGravity(GravityEnum.END)
                    .backgroundColorRes(R.color.colorPrimary)
                    .buttonsGravity(GravityEnum.END)
                    .customView(view, false)
                    .build()

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