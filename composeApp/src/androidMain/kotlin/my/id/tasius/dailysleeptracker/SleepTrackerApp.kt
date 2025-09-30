package my.id.tasius.dailysleeptracker

import android.app.Application
import my.id.tasius.dailysleeptracker.di.initKoin
import org.koin.android.ext.koin.androidContext

class SleepTrackerApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@SleepTrackerApp)
        }
    }
}