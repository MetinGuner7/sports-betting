package com.sports.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.sports.core.datastore.BetBasketProto
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class BetBasketSerializer @Inject constructor() : Serializer<BetBasketProto> {
    override val defaultValue: BetBasketProto = BetBasketProto.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): BetBasketProto {
        try {
            return BetBasketProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: BetBasketProto, output: OutputStream) {
        t.writeTo(output)
    }
}