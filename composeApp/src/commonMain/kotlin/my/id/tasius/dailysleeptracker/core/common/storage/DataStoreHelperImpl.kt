package my.id.tasius.dailysleeptracker.core.common.storage

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class DataStoreHelperImpl : DataStoreHelper {
    private val dataStore = createDataStore()

    override suspend fun getString(key: String): String? {
        val prefKey = stringPreferencesKey(key)
        return dataStore.data
            .map { prefs -> prefs[prefKey] ?: "" }
            .firstOrNull()
    }

    override suspend fun setString(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[prefKey] = value
        }
    }

    @Serializable
    data class UserPreferences(
        val username: String,
        val email: String,
        val phoneNumber: String,
        val password: String?,
    )

    override suspend fun getBoolean(key: String): Boolean? {
        val prefKey = booleanPreferencesKey(key)
        return dataStore.data
            .map { prefs -> prefs[prefKey] ?: false }
            .firstOrNull()
    }

    override suspend fun setBoolean(key: String, value: Boolean) {
        val prefKey = booleanPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[prefKey] = value
        }
    }

    override suspend fun <T : Any> getObject(
        key: String,
        serializer: KSerializer<T>
    ): T? {
        val jsonString = getString(key) ?: return null
        return try {
            Json.decodeFromString(serializer, jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun <T : Any> setObject(
        key: String,
        value: T,
        serializer: KSerializer<T>
    ) {
        val jsonString = Json.encodeToString(serializer, value)
        setString(key, jsonString)
    }
}
