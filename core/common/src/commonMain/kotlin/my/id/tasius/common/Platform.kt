package my.id.tasius.common

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform