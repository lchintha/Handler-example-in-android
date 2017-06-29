package com.example.chint.samplehandler;

import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb;
    Thread myThread;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = (ProgressBar)findViewById(R.id.pbar);
        myThread = new Thread(new myHandler());
        myThread.start();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                pb.setProgress(msg.arg1);
            }
        };
    }

    public class myHandler implements Runnable{

        @Override
        public void run() {
            for(int i=0; i<100; i++){
                Message msg = Message.obtain();
                msg.arg1 = i;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
