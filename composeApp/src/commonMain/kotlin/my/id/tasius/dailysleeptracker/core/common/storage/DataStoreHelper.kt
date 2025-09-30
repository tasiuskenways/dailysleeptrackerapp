package my.id.tasius.dailysleeptracker.core.common.storage

import kotlinx.serialization.KSerializer

interface DataStoreHelper {
    suspend fun getString(key: String): String?
    suspend fun setString(key: String, value: String)
    suspend fun getBoolean(key: String): Boolean?
    suspend fun setBoolean(key: String, value: Boolean)
    suspend fun <T: Any>getObject(key: String, serializer: KSerializer<T>): T?
    suspend fun <T: Any>setObject(key: String, value: T, serializer: KSerializer<T>)
}