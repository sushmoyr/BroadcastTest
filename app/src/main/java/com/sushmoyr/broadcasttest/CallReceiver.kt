package com.sushmoyr.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import android.view.Gravity
import android.widget.Toast

class CallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when {
            intent.getStringExtra(TelephonyManager.EXTRA_STATE) == TelephonyManager.EXTRA_STATE_OFFHOOK -> {
                showToast(context, "Call started...")
                val number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
                Log.d("CallRecord", "Call started...$number")
            }
            intent.getStringExtra(TelephonyManager.EXTRA_STATE) == TelephonyManager.EXTRA_STATE_IDLE -> {
                showToast(context, "Call ended...")

            }
            intent.getStringExtra(TelephonyManager.EXTRA_STATE) == TelephonyManager.EXTRA_STATE_RINGING -> {

                val number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
                showToast(context, "Incoming call...$number")
                Log.d("CallRecord", "Incoming call...$number")
            }
        }
    }

    private fun showToast(context: Context?, message: String?) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast.show()
    }

    private fun getPhoneNumber(context: Context, msg: String){
        val telephony = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        telephony.listen(object : PhoneStateListener() {
            override fun onCallStateChanged(state: Int, incomingNumber: String) {
                super.onCallStateChanged(state, incomingNumber)

                Log.d("CallRecord", "$msg = $incomingNumber")
            }
        }, PhoneStateListener.LISTEN_CALL_STATE)
    }

}