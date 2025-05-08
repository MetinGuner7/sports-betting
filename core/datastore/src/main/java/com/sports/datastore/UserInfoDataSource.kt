package com.sports.datastore

import androidx.datastore.core.DataStore
import com.sports.core.datastore.UserInfo
import com.sports.core.datastore.copy
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserInfoDataSource @Inject constructor(private val dataStore: DataStore<UserInfo>) {
    suspend fun clearUserInfo() {
        dataStore.updateData {
            UserInfo.getDefaultInstance()
        }
    }

    suspend fun updateTokens(accessToken: String, refreshToken: String) {
        dataStore.updateData {
            it.copy {
                this.accessToken = accessToken
                this.refreshToken = refreshToken
            }
        }
    }


    val accessToken = dataStore.data.map { it.accessToken }
    val refreshToken = dataStore.data.map { it.refreshToken }

}
