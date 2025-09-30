package my.id.tasius.dailysleeptracker

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform