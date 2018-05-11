package com.likotv.common.presenter

/**
 * Created by mohandeath on 8/6/17.
 */
interface BaseView {

    fun showProgress()

    fun dismissProgress()

    fun onError(basePresenter: BasePresenter, message: String)

}