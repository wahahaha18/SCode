package movie.yq.com.share1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQWebShareAdapter;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;


public class ShareMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mMButton;
    private static final int WEBCHAT = 1;
    private static final int WEBCHATMOMENTS = 2;
    private static final int QQ = 3;
    private static final int SINA = 4;
    ImageView rl_iv_unlogin,ll_bottom_iv_weixin,ll_bottom_iv_qq,ll_bottom_iv_friends,ll_bottom_iv_weibo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShareSDK.initSDK(this);
        setContentView(R.layout.activity_share_message);
        mMButton = (Button) findViewById(R.id.c);
        mMButton.setOnClickListener(this);
        ll_bottom_iv_weixin = (ImageView) findViewById(R.id.ll_bottom_iv_weixin);
        ll_bottom_iv_qq = (ImageView) findViewById(R.id.ll_bottom_iv_qq);
        ll_bottom_iv_friends = (ImageView) findViewById(R.id.ll_bottom_iv_friends);
        ll_bottom_iv_weibo = (ImageView) findViewById(R.id.ll_bottom_iv_weibo);
        ll_bottom_iv_weixin.setOnClickListener(this);
        ll_bottom_iv_qq.setOnClickListener(this);
        ll_bottom_iv_friends.setOnClickListener(this);
        ll_bottom_iv_weibo.setOnClickListener(this);
    }
    private void showShare(int platforms) {

        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        switch (platforms){
            case WEBCHAT:
                oks.setPlatform(Wechat.NAME);
                break;
            case WEBCHATMOMENTS:
                oks.setPlatform(QZone.NAME);
                break;
            case QQ:
                oks.setPlatform(cn.sharesdk.tencent.qq.QQ.NAME);
                break;
            case SINA:
                oks.setPlatform(SinaWeibo.NAME);
                break;
        }

// 启动分享GUI
        oks.show(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_bottom_iv_weixin:
                showShare(WEBCHAT);
                break;
            case R.id.ll_bottom_iv_qq:
                showShare(QQ);
                break;
            case R.id.ll_bottom_iv_friends:
                showShare(WEBCHATMOMENTS);
                break;
            case R.id.ll_bottom_iv_weibo:
                showShare(SINA);
                break;
        }


    }
}
