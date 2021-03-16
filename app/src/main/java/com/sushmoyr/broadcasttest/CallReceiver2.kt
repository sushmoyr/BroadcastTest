package com.sushmoyr.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import android.view.Gravity
import android.widget.Toast


//USE THIS
class CallReceiver2 : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val telephony = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        telephony.listen(object : PhoneStateListener() {
            override fun onCallStateChanged(state: Int, incomingNumber: String) {
                super.onCallStateChanged(state, incomingNumber)

                var msgPrefix = ""
                when (state) {
                    TelephonyManager.CALL_STATE_RINGING -> msgPrefix="Ringing"
                    TelephonyManager.CALL_STATE_IDLE -> msgPrefix="Idle"
                    TelephonyManager.CALL_STATE_OFFHOOK -> msgPrefix="Off hook"
                }

                showToast(context, "$msgPrefix : $incomingNumber")
                Log.d("CALLTEST", "$msgPrefix = $incomingNumber")
            }
        }, PhoneStateListener.LISTEN_CALL_STATE)
    }

    private fun showToast(context: Context?, message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}