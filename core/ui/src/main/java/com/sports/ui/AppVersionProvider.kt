package com.sports.ui

object AppVersionProvider {
    private var versionCode: Long = -1

    fun getVersionCode(): Long {
        return versionCode
    }

    fun setVersionCode(versionCode: Long) {
        this.versionCode = versionCode
    }
}
