package mvp.larin.cash.larinmvp.common

import android.content.Context
import android.widget.ImageView
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import mvp.larin.cash.larinmvp.R
import okhttp3.OkHttpClient

/**
 * Created by mohandeath on 5/13/2018 AD.
 */


/**
 * Created by mohammadmoradyar on 9/18/17.
 */
class ImageHelper private constructor() {

    private lateinit var picasso: Picasso
    private lateinit var ctx: Context
    private val cropSize = 500

    companion object {
        @Volatile private var INSTANCE: ImageHelper? = null
        fun getInstance(context: Context): ImageHelper = INSTANCE ?: synchronized(this) { INSTANCE ?: ImageHelper(context).also { INSTANCE = it } }
    }

    constructor(ctx: Context) : this() {
        this.ctx = ctx
        val client = OkHttpClient.Builder()
                .build()
        picasso = Picasso.Builder(ctx).downloader(OkHttp3Downloader(client)).build()

    }

    fun loadImage(url: String, imgView: ImageView?, placeHolder: Int) {
        try {
            try {
                imgView?.setImageResource(R.drawable.outline)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            picasso
                    .load(url)
                    .placeholder(placeHolder)
                    .into(imgView)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadImageFit(url: String, imgView: ImageView?, placeHolder: Int) {
        try {
            try {
                imgView?.setImageResource(R.drawable.outline)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            picasso
                    .load(url)
                    .placeholder(placeHolder)
                    .fit()
                    .into(imgView)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadImageWithResize(url: String, imgView: ImageView?) {
        try {
            picasso
                    .load(url)
                    .resize(cropSize, cropSize)
                    .centerCrop()
                    .into(imgView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadImageWithOutCenterCrop(url: String, imgView: ImageView?, placeHolder: Int) {
        try {
            picasso
                    .load(url)
                    .placeholder(placeHolder)
                    .into(imgView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadImage(url: String, imgView: ImageView?) {
        try {
            picasso
                    .load(url)
                    .into(imgView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadImageWithOutPlaceHolder(url: String, imgView: ImageView?) {
        try {
            picasso
                    .load(url)
                    .into(imgView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadImageRoundedCorner(url: String, imgView: ImageView?, placeHolder: Int) {
        try {
            picasso
                    .load(url)
                    .placeholder(placeHolder)
                    .transform(RoundedCornersTransformation(AppManager.dpToPx(ctx, 5), 0))
                    .into(imgView)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}