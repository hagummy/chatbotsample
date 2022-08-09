package com.example.chatbotsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.chatbotsample.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private lateinit var binding: ActivityMainBinding;
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btn_msg=binding.btnMsg
        var txt_msg=binding.txtMsg

        var retrofit= Retrofit.Builder().baseUrl("http://192.168.137.1:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var msgservice=retrofit.create(MsgService::class.java)

        btn_msg.setOnClickListener {
            var message=txt_msg.text.toString()

            msgservice.requestMsg(message).enqueue(object: Callback<Message>{
                override fun onResponse(call: Call<Message>, response: Response<Message>) {
                    var msgg=response.body()
                    var dialog=AlertDialog.Builder(this@MainActivity)
                    dialog.setTitle("성공").setMessage(msgg?.msgg).show()
                }

                override fun onFailure(call: Call<Message>, t: Throwable) {
                    var dialog=AlertDialog.Builder(this@MainActivity)
                    dialog.setTitle("실패!").show()
                }

            })

        }
    }
}