package com.sports.component.ui

import android.os.Bundle
import androidx.navigation.NavType
import com.sports.common.base.CustomNavTypes
import com.sports.component.domain.model.EventDetailDomainModel
import kotlinx.serialization.json.Json
import android.net.Uri

val CustomNavTypes.BulletinNavType: NavType<EventDetailDomainModel>
    get() =
        object : NavType<EventDetailDomainModel>(isNullableAllowed = false) {
            override fun get(bundle: Bundle, key: String): EventDetailDomainModel? {
                return bundle.getString(key)?.let { Json.decodeFromString(it) }
            }

            override fun parseValue(value: String): EventDetailDomainModel {
                return Json.decodeFromString(Uri.decode(value))
            }

            override fun serializeAsValue(value: EventDetailDomainModel): String {
                return Uri.encode(Json.encodeToString(value))
            }

            override fun put(bundle: Bundle, key: String, value: EventDetailDomainModel) {
                bundle.putString(key, Json.encodeToString(value))
            }
        }
