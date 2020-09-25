package mobi.mobileforce.temperaturesdk

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val br = object: BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            val action = p1?.action
            if(action.equals("marvel.intent.action.external.execute")){
                val bundle = p1?.extras
                val obj1: Any? = bundle?.get("names")
                val obj2: Any? = bundle?.get("datas")
                if(obj1 != null && obj2 != null){
                    val name = obj1.toString()
                    val data = obj2.toString()
                    nametext.text = name
                    datatext.text = data
                }
            }
        }
    }

    private val filter = IntentFilter().apply {
        addAction("marvel.intent.action.external.execute")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver(br, filter)
        val intent = Intent()
        intent.action = "marvel.intent.action.external.omcexc"
        intent.putExtra("names", "GetAllTemp")
        sendBroadcast(intent)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(br)
    }
}