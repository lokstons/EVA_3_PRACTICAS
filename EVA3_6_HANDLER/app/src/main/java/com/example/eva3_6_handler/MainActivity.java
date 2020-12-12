package com.example.eva3_6_handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtVwMsg;
    Handler miHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            // AQUÍ PODEMOS MODIFICAR LA UI
            int valor_i = (int) msg.obj;
            txtVwMsg.append("Hilo: " + msg.what + "valor i = " + valor_i + "\n");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtVwMsg.findViewById(R.id.txtVwMsg);

        Thread tHilo = new Thread(){
            @Override
            public void run() {
                super.run();
                int i = 0;
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg = miHandler.obtainMessage(1, i);
                    miHandler.sendMessage(msg);
                    Log.wtf("tHilo", i + "");
                    i++;
                }
            }
        };
        tHilo.start();
    }
}