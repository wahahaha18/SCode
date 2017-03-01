package movie.yq.com.scode.a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import movie.yq.com.scode.OkActivity;
import movie.yq.com.scode.R;

public class MainActivity extends Activity implements View.OnClickListener {

        String APPKEY="1b20581be0011";

        String APPSECRETE="4f35b116e5908abfe84df6b075e38eef";

//电话号码输入框

        private EditText mPhoneEdt;

//验证码输入框

        private EditText mCodeEdt;

//发送验证码按钮

        private Button mCodeRequestBtn;

//注册按钮

        private Button mRegisterBtn;

private int i =30;

@Override

protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_register);

        init();

        }

/**

 * 初始化控件

 */

private void init() {

        mPhoneEdt= (EditText) findViewById(R.id.input_phone_et);

        mCodeEdt= (EditText) findViewById(R.id.input_code_et);

        mCodeRequestBtn= (Button) findViewById(R.id.request_code_btn);

        mRegisterBtn= (Button) findViewById(R.id.register_btn);

        mCodeRequestBtn.setOnClickListener(this);

        mRegisterBtn.setOnClickListener(this);

        SMSSDK.initSDK(this,APPKEY,APPSECRETE);

        EventHandler eventHandler =new EventHandler() {

@Override

public void afterEvent(int event, int result,Object data) {

        Message message =new Message();

        message.arg1= event;

        message.arg2= result;

        message.obj= data;

        handler.sendMessage(message);

        }

        };

//注册回调监听接口

        SMSSDK.registerEventHandler(eventHandler);

        }

@Override

public void onClick(View view) {

        String mIphone =mPhoneEdt.getText().toString();

        switch(view.getId()) {

        case R.id.request_code_btn:

        if(judgePhoneNums(mIphone)) {

        SMSSDK.getVerificationCode("86",mIphone);

        mCodeRequestBtn.setClickable(false);

        mCodeRequestBtn.setText("重新发送("+i+")");

        new Thread(new Runnable() {

@Override

public void run() {

        for(;i>0;i--) {

        handler.sendEmptyMessage(1);
                Log.e("onClick", "i: "+i);

        if(i<=0) {

        break;

        }

        try{

        Thread.sleep(1000);

        }catch(InterruptedException e) {

        e.printStackTrace();

        }

        }

        handler.sendEmptyMessage(2);

        }

        }).start();

        break;

        }else{

        }

        break;

        case R.id.register_btn:

//将收到的验证码和手机号提交再次核对

        SMSSDK.submitVerificationCode("86",mIphone,mCodeEdt.getText().toString());

        break;

        }

        }

/**

 * 判断手机号码是否合理

 *

 *@paramphoneNums

 */

private boolean judgePhoneNums(String phoneNums) {

        if(isMatchLength(phoneNums,11)

        &&isMobileNO(phoneNums)) {

        return true;

        }

        Toast.makeText(this,"手机号码输入有误！",Toast.LENGTH_SHORT).show();

        return false;

        }

/**

 * 判断一个字符串的位数

 *

 *@paramstr

 *@paramlength

 *@return

 */

public static boolean isMatchLength(String str, int length) {

        if(str.isEmpty()) {

        return false;

        }else{

        return str.length() == length ?true:false;

        }

        }

/**

 * 验证手机格式

 */

public static boolean isMobileNO(String mobileNums) {

/*

* 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188

* 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）

* 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9

*/

        String telRegex ="[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。

        if(TextUtils.isEmpty(mobileNums))

        return false;

        else

        return mobileNums.matches(telRegex);

        }

        Handler handler=new Handler() {

@Override

public void handleMessage(Message msg) {

        if(msg.what==1) {

        mCodeRequestBtn.setText("重新发送"+i+"s");
                Log.e("handleMessage", "ii: "+i);

        }else if(msg.what==2) {

        mCodeRequestBtn.setText("获取验证码");

        mCodeRequestBtn.setClickable(true);

        }else{

        int event = msg.arg1;

        int result = msg.arg2;

        Object data = msg.obj;

        Log.e("event","event="+ event);

        if(result == SMSSDK.RESULT_COMPLETE) {

//回调完成

        if(event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {

//提交验证码成功

        Toast.makeText(getApplicationContext(),"提交验证码成功",

        Toast.LENGTH_SHORT).show();

        Intent intent =new Intent(MainActivity.this, OkActivity.class);

        startActivity(intent);

        }else if(event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {

//获取验证码成功

        Toast.makeText(getApplicationContext(),"正在获取验证码",

        Toast.LENGTH_SHORT).show();

        }else if(event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {

//返回支持发送验证码的国家列表

        }

        }

        }

        }

        };

@Override

protected void onDestroy() {

        SMSSDK.unregisterAllEventHandler();

        super.onDestroy();

        }

        }

