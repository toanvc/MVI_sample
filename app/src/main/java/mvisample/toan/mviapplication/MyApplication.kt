package mvisample.toan.mviapplication

import android.app.Application
import mvisample.toan.mviapplication.dependencies.AppComponent
import mvisample.toan.mviapplication.dependencies.DaggerAppComponent


/**
 * Created by ToanVu on 7/11/17.
 */

class MyApplication : Application(){
    companion object {
        var instance: MyApplication? = null
    }

    var appComponent: AppComponent? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
                .build()

    }


}