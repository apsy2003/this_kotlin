package com.apsy2003.myservicetest

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.view.View

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int{
        val action = intent?.action
        Log.d("StartedService", "action = $action")
        return super.onStartCommand(intent, flags, startId)
    }

    companion object{
        val ACTION_START ="com.apsy2003.servicetest.START"
        val ACTION_RUN ="com.apsy2003.servicetest.RUN"
        val ACTION_STOP ="com.apsy2003.servicetest.STOP"
    }

    override fun onDestroy(){
        Log.d("Service","서비스가 종료되었습니다.")
        super.onDestroy()
    }

    inner class MyBinder: Binder(){
        fun getService(): MyService{
            return this@MyService
        }
    }
    val binder = MyBinder()

    fun serviceMessage(): String{
        return "Hello Activity! I am Service!"
    }

}