package mvp.larin.cash.larinmvp.transactions.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import mvp.larin.cash.larinmvp.R
import mvp.larin.cash.larinmvp.common.ClickAction
import mvp.larin.cash.larinmvp.common.ImageHelper
import mvp.larin.cash.netcore.data.GenericModel
import mvp.larin.cash.netcore.data.Invoice

/**
 * Created by mohandeath on 5/12/2018 AD.
 */
class AdapterInvoice(val ctx: Context, val items: List<Invoice>, val opClick: (ClickAction, Invoice?) -> Unit) : RecyclerView.Adapter<AdapterInvoice.InvoiceViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: InvoiceViewHolder?, position: Int) {
        var item = items[position]
        holder?.txt_label?.text = item.title
        holder?.txt_date?.text = item.created
        holder?.txt_price?.text = item.amount.toString()
        ImageHelper.getInstance(ctx).loadImage(items[position].image, holder?.img_vendor, R.drawable.outline)
        holder?.itemView?.setOnClickListener {
            opClick(ClickAction.INVOICE_CLICKED, item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): InvoiceViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_invoice_list, parent, false)
        return AdapterInvoice.InvoiceViewHolder(view)
    }

    class InvoiceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txt_label: TextView = view.findViewById(R.id.txt_label)
        val txt_date: TextView = view.findViewById(R.id.txt_date)
        val txt_price: TextView = view.findViewById(R.id.txt_price)
        val img_vendor: ImageView = view.findViewById(R.id.img_vendor)

    }
}
