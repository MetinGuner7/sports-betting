package com.sports.analytics

import com.google.firebase.analytics.FirebaseAnalytics

data class AnalyticsEvent(val type: String, val extras: List<Param> = emptyList()) {
    // Standard analytics types.
    object Types {
        const val SCREEN_VIEW = "screen_view" // (extras: SCREEN_NAME)
        const val BUTTON_CLICK = "button_click"
        const val VIEW_PROMOTION = FirebaseAnalytics.Event.VIEW_PROMOTION
        const val SELECT_PROMOTION = FirebaseAnalytics.Event.SELECT_PROMOTION
        const val VIEW_ITEM_LIST = FirebaseAnalytics.Event.VIEW_ITEM_LIST
        const val SELECT_ITEM = FirebaseAnalytics.Event.SELECT_ITEM
        const val ADD_TO_CART = FirebaseAnalytics.Event.ADD_TO_CART
        const val REMOVE_FROM_CART = FirebaseAnalytics.Event.REMOVE_FROM_CART
        const val VIEW_ITEM = FirebaseAnalytics.Event.VIEW_ITEM
        const val VIEW_CART = FirebaseAnalytics.Event.VIEW_CART
        const val BEGIN_CHECKOUT = FirebaseAnalytics.Event.BEGIN_CHECKOUT
        const val ADD_SHIPPING_INFO = FirebaseAnalytics.Event.ADD_SHIPPING_INFO
        const val ADD_PAYMENT_INFO = FirebaseAnalytics.Event.ADD_PAYMENT_INFO
        const val PURCHASE = FirebaseAnalytics.Event.PURCHASE
    }

    /**
     * A key-value pair used to supply extra context to an analytics event.
     *
     * @param key - the parameter key. Wherever possible use one of the standard `ParamKeys`,
     *   however, if no suitable key is available you can define your own as long as it is
     *   configured in your backend analytics system (for example, by creating a Firebase Analytics
     *   custom parameter).
     * @param value - the parameter value.
     */
    data class Param(val key: String, val value: String)

    // Standard parameter keys.
    object ParamKeys {
        const val SCREEN_NAME = "screen_name"
        const val BUTTON_ID = "button_id"
        const val ITEM_ID = "item_id"
        const val ITEM_NAME = "item_name"
        const val ITEMS = FirebaseAnalytics.Param.ITEMS
        const val QUANTITY = FirebaseAnalytics.Param.QUANTITY
        const val CURRENCY = FirebaseAnalytics.Param.CURRENCY
        const val VALUE = FirebaseAnalytics.Param.VALUE
        const val SHIPPING_TIER = FirebaseAnalytics.Param.SHIPPING_TIER
        const val PAYMENT_TYPE = FirebaseAnalytics.Param.PAYMENT_TYPE
        const val TRANSACTION_ID = FirebaseAnalytics.Param.TRANSACTION_ID
        const val AFFILIATION = FirebaseAnalytics.Param.AFFILIATION
        const val TAX = FirebaseAnalytics.Param.TAX
        const val SHIPPING = FirebaseAnalytics.Param.SHIPPING
    }
}
