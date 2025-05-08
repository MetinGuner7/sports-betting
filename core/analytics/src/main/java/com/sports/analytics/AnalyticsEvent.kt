package com.sports.analytics


data class AnalyticsEvent(val type: String, val extras: List<Param> = emptyList()) {
    object Types {
        const val SCREEN_VIEW = "screen_view"
        const val BUTTON_CLICK = "button_click"
        const val ADD_TO_BASKET = "add_to_basket"
        const val REMOVE_FROM_BASKET = "remove_from_basket"
        const val MATCH_DETAIL = "match_detail"
    }

    data class Param(val key: String, val value: String)

    object ParamKeys {
        const val SCREEN_NAME = "screen_name"
        const val BUTTON_ID = "button_id"
        const val EVENT_ID = "event_id" // Maçın ID'si
        const val MARKET_KEY = "market_key" // Pazarın anahtarı (örn: "h2h", "totals")
        const val OUTCOME_NAME = "outcome_name" // Seçimin adı (örn: "Ev Sahibi Kazanır")
        const val OUTCOME_PRICE = "outcome_price" // Seçimin oranı
        const val BOOKMAKER_KEY = "bookmaker_key" // Bahis şirketinin anahtarı
        const val SELECTION_ID = "selection_id" // Bahsin benzersiz ID'si
        const val HOME_TEAM = "home_team"
        const val AWAY_TEAM = "away_team"
    }
}
