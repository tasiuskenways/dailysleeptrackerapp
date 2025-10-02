package my.id.tasius.dailysleeptracker.core.common.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.runBlocking

actual fun createDataStore(): DataStore<Preferences> {
    return runBlocking {
        getDataStore(producePath = {
            ContextUtils.dataStoreApplicationContext?.filesDir?.resolve(
                dataStoreFileName
            )?.absolutePath ?: ""
        })
    }
}