package com.example.microbitcontroller

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var btnForward: Button
    private lateinit var btnRight: Button
    private lateinit var btnLeft: Button
    private lateinit var btnBackward: Button
    private lateinit var btnStop: Button
    private lateinit var btnSound: Button
    private lateinit var btnLights: Button
    private lateinit var btnCircle: Button
    private lateinit var btnMessage: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnForward = findViewById(R.id.btnForward)
        btnRight = findViewById(R.id.btnRight)
        btnLeft = findViewById(R.id.btnLeft)
        btnBackward = findViewById(R.id.btnBackward)
        btnStop = findViewById(R.id.btnStop)
        btnSound = findViewById(R.id.btnSound)
        btnLights = findViewById(R.id.btnLights)
        btnCircle = findViewById(R.id.btnCircle)
        btnMessage = findViewById(R.id.btnMessage)

        btnForward.setOnClickListener {
            sendHttpGetRequest("http://192.168.4.1/Forward")
        }

        btnBackward.setOnClickListener {
            sendHttpGetRequest("http://192.168.4.1/Backward")
        }

        btnRight.setOnClickListener {
            sendHttpGetRequest("http://192.168.4.1/MoveR")
        }

        btnLeft.setOnClickListener {
            sendHttpGetRequest("http://192.168.4.1/MoveL")
        }

        btnStop.setOnClickListener {
            sendHttpGetRequest("http://192.168.4.1/Stop")
        }

        btnSound.setOnClickListener {
            sendHttpGetRequest("http://192.168.4.1/Sound")
        }

        btnMessage.setOnClickListener {
            sendHttpGetRequest("http://192.168.4.1/Message")
        }

        btnLights.setOnClickListener {
            sendHttpGetRequest("http://192.168.4.1/Lights")
        }

        btnCircle.setOnClickListener {
            sendHttpGetRequest("http://192.168.4.1/Circle")
        }
    }

    private fun sendHttpGetRequest(urlString: String) {
        Thread {
            try {
                val url = URL(urlString)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 20000 // Set your desired timeout
                connection.readTimeout = 20000 // Set your desired timeout

                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Successfully sent the GET request
                    val inputStream = connection.inputStream
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    val response = StringBuilder()
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        response.append(line)
                    }
                    // Handle the response data here if needed
                    val responseData = response.toString()
                } else {
                    // Handle the error case here
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle exceptions here
            }
        }.start()
    }






}
