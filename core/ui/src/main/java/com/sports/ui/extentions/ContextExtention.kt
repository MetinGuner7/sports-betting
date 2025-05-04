package com.sports.ui.extentions

import android.content.Context

fun Context.getResourceIdFromName(name: String, type: String): Int {
    return resources.getIdentifier(name, type, packageName)
}