package my.id.tasius.dailysleeptracker.core.common.storage

import android.content.Context

object ContextUtils {

    var dataStoreApplicationContext: Context? = null

    fun setContext(context: Context) {
        dataStoreApplicationContext = context.applicationContext
    }
}