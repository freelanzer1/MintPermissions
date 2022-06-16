package ru.mintrocket.mintpermissions

import android.app.Application
import android.preference.PreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.mintrocket.lib.mintpermissions.MintPermissionsConfig
import ru.mintrocket.lib.mintpermissions.ext.initMintPermissions
import ru.mintrocket.mintpermissions.koin.exampleModule
import ru.mintrocket.mintpermissions.koin.libraryModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initMintPermissions(MintPermissionsConfig(autoInitManagers = true))
        startKoin {
            androidContext(this@App)
            modules(exampleModule, libraryModule)
        }
        avoidEmptyUserData()
    }

    private fun avoidEmptyUserData() {
        PreferenceManager.getDefaultSharedPreferences(this)
            .edit()
            .putString("hello", "world")
            .apply()
    }
}