package org.nibienvenu.fleetmanager

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform