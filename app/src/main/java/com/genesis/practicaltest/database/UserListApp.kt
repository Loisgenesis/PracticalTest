package com.genesis.practicaltest.database

import android.app.Application

class UserListApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}