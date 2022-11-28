package com.example.clienttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.clienttest.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.ObjectInputStream
import java.net.Socket
import java.io.PrintWriter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root

        setContentView(view)

        binding.textView.text="100/60/60/30/0/0/0/0"
        binding.button.setOnClickListener(listener)
    }
    val listener= View.OnClickListener {
//        binding.textView2.text="동작 확인"
//      버튼 누르면 스레드 동작 시켜서 서버와 통신하는 것
        ClientThread().start()
    }
    inner class ClientThread: Thread(){
        override fun run() {
            try {
                
//              소켓 생성
                val socket = Socket("192.168.110.59", 80)
                
//              서버에 보낼 값 쓰는 것. println에 값 넣으면 된다. 
                val printWriter=PrintWriter(socket.getOutputStream())
                printWriter.println(binding.textView.text.toString())
                printWriter.flush()

//              서버에서 보낸 값 읽어오는 것
                val reader=InputStreamReader(socket.getInputStream())
                val bufReader=BufferedReader(reader)
                val buf=StringBuffer()
                
                var str:String?=null

                while(bufReader.readLine().also{str= it}!=null){
                    buf.append("""$str""")
                }
                
            runOnUiThread {
//              여기서 buf.toString이 읽어온 값이다. 
                binding.textView2.text=(buf.toString())
            }

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }
}

