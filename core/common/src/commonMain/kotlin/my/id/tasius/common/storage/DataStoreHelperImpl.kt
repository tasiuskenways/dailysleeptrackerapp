package my.id.tasius.common.storage

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

/**
 * Implementation of [DataStoreHelper] that uses Jetpack DataStore to store and retrieve data.
 *
 * This class provides methods to store and retrieve primitive data types (String, Boolean)
 * and complex objects (using Kotlinx Serialization).
 *
 * @property dataStore The underlying DataStore instance used for data persistence.
 */
class DataStoreHelperImpl : DataStoreHelper {
    private val dataStore = createDataStore()

    /**
     * Retrieves a string value from DataStore.
     *
     * @param key The key to retrieve the string value for.
     * @return The string value associated with the key, or an empty string if the key is not found or null.
     */
    override suspend fun getString(key: String): String? {
        val prefKey = stringPreferencesKey(key)
        return dataStore.data
            .map { prefs -> prefs[prefKey] ?: "" }
            .firstOrNull()
    }

    /**
     * Sets a string value for the given key in the DataStore.
     *
     * @param key The key to associate with the value.
     * @param value The string value to store.
     */
    override suspend fun setString(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[prefKey] = value
        }
    }

    /**
     * Retrieves a boolean value from DataStore.
     *
     * @param key The key to retrieve the boolean value for.
     * @return The boolean value associated with the key, or false if the key is not found or null.
     */
    override suspend fun getBoolean(key: String): Boolean? {
        val prefKey = booleanPreferencesKey(key)
        return dataStore.data
            .map { prefs -> prefs[prefKey] }
            .firstOrNull()
    }

    /**
     * Sets a boolean value for the given key in the DataStore.
     *
     * @param key The key to associate with the value.
     * @param value The boolean value to store.
     */
    override suspend fun setBoolean(key: String, value: Boolean) {
        val prefKey = booleanPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[prefKey] = value
        }
    }

    /**
     * Retrieves a complex object from DataStore.
     *
     * The object is deserialized from a JSON string using the provided [serializer].
     *
     * @param T The type of the object to retrieve.
     * @param key The key to retrieve the object for.
     * @param serializer The [KSerializer] to use for deserializing the object.
     * @return The deserialized object associated with the key, or null if the key is not found,
     * the stored value is not a valid JSON string for the given type, or an error occurs during deserialization.
     */
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

    /**
     * Sets an object value for the given key in the DataStore.
     *
     * The object is serialized to a JSON string before being stored.
     *
     * @param T The type of the object to store.
     * @param key The key to associate with the value.
     * @param value The object to store.
     * @param serializer The Kotlinx KSerializer for the object type.
     */
    override suspend fun <T : Any> setObject(
        key: String,
        value: T,
        serializer: KSerializer<T>
    ) {
        val jsonString = Json.encodeToString(serializer, value)
        setString(key, jsonString)
    }
}
