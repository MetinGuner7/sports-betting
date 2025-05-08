package com.sports.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.sports.core.datastore.UserInfo
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

/** An [androidx.datastore.core.Serializer] for the [UserInfo] proto. */
class UserInfoSerializer @Inject constructor() : Serializer<UserInfo> {
    override val defaultValue: UserInfo = UserInfo.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserInfo =
        try {
            // readFrom is already called on the data store background thread
            UserInfo.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    override suspend fun writeTo(t: UserInfo, output: OutputStream) {
        // writeTo is already called on the data store background thread
        t.writeTo(output)
    }
}
