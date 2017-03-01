package movie.yq.com.countdown;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {

    private Button mButton;
    private int time = 30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.b);

        mButton.setText("倒计时("+time+")");

        new Thread(new Runnable() {

            @Override

            public void run() {

                for(;time>0;time--) {

                    mHandler.sendEmptyMessage(1);
                    Log.e("onClick", "i: "+time);

                    if(time<=0) {

                        break;

                    }

                    try{

                        Thread.sleep(1000);

                    }catch(InterruptedException e) {

                        e.printStackTrace();

                    }

                }

                mHandler.sendEmptyMessage(2);

            }

        }).start();


    }

//    @Override
//    public void onClick(View v) {
//        mButton.setClickable(false);
//
//        mButton.setText("重新发送("+time+")");
//
//        new Thread(new Runnable() {
//
//            @Override
//
//            public void run() {
//
//                for(;time>0;time--) {
//
//                    mHandler.sendEmptyMessage(1);
//                    Log.e("onClick", "i: "+time);
//
//                    if(time<=0) {
//
//                        break;
//
//                    }
//
//                    try{
//
//                        Thread.sleep(1000);
//
//                    }catch(InterruptedException e) {
//
//                        e.printStackTrace();
//
//                    }
//
//                }
//
//                mHandler.sendEmptyMessage(2);
//
//            }
//
//        }).start();
//
//
//
//
//
//    }
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1) {

                mButton.setText("倒计时"+time+"s");
                Log.e("handleMessage", "ii: "+time);

            }else if(msg.what==2) {

//                mButton.setText("获取验证码");
//
//                mButton.setClickable(true);
                Intent intent = new Intent(MainActivity.this,SecondActivivty.class);
                startActivity(intent);

            }

        }
    };
}
