package mvp.larin.cash.larinmvp.common
/*
 * Created by mohandeath on 8/6/17.
 */
enum class ClickAction {
    CARD_CLICKED,
    ADS_CLICKED,
    BACK_BUTTON,
    SORT_ICON_CLICKED,
    FILTER_ICON_CLICKED,
    MORE_MENU_CLICKED,
    SHOW_MORE_CLICKED,
    FAV_CLICK,
    RATE_CLICK,
    DELETE_CLICK,
    LOCK_CLICK,
    INVOICE_LIST,
    BOTTOM_SHEET_ITEM_CLICK,

    INVOICE_CLICKED,
}
interface BaseView {

    fun showProgress()

    fun dismissProgress()

    fun onError(basePresenter: BasePresenter, message: String)

}