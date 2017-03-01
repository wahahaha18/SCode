package movie.yq.com.share1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

    public class ShareDiyActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_diy);
        mTextView = (TextView) findViewById(R.id.tv);
        mTextView.setOnClickListener(this);

        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
//        Log.i(tag, "屏幕尺寸1: 宽度 = "+display.getWidth()+"高度 = :"+display.getHeight()  )
    }

    @Override
    public void onClick(View v) {
        popWindow(v);
    }

    private void popWindow(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popwindow, null);

        //设置屏幕的高度和宽度
        final PopupWindow pop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //如果不设置背景颜色的话，无法是pop dimiss掉。
        pop.setBackgroundDrawable(getResources().getDrawable(R.drawable.popupwindow_background));
        pop.setOutsideTouchable(true); pop.setAnimationStyle(R.style.MyPopupWindow_anim_style);

        ImageView imageViewW = (ImageView) view.findViewById(R.id.ll_bottom_iv_weixin);
        ImageView imageViewQ = (ImageView) view.findViewById(R.id.ll_bottom_iv_qq);
        ImageView imageViewQqone = (ImageView) view.findViewById(R.id.ll_bottom_iv_friends);
        ImageView imageViewS = (ImageView) view.findViewById(R.id.ll_bottom_iv_weibo);
        Button button = (Button) view.findViewById(R.id.cancel);
        imageViewW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageViewQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageViewQqone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageViewS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShareDiyActivity.this, "取消", Toast.LENGTH_SHORT).show(); pop.dismiss();
            }
        });

        /** * 设置popwindow的弹出的位置. *
         1：首先要判断是否有navigation bar。如果有的的话，要把他们的高度给加起来。 * *
         2：showAtLocation（）；是pop相对于屏幕而言的。 * *
         3：如果是 pop.showAsDropDown();则是相对于你要点击的view的位置。设置的坐标。
         */
//        if(checkDeviceHasNavigationBar2(this)){
//            int heigth_tobottom =100+getNavigationBarHeight();
//            pop.showAtLocation(v, Gravity.BOTTOM,0,heigth_tobottom);
//        }else {
//            pop.showAtLocation(v, Gravity.BOTTOM,0,100);
//        }
////设置 背景的颜色为 0.5f 的透明度
//        backgroundAlpha(0.5f);
//        pop.setOnDismissListener(new PopupWindow.OnDismissListener() { @Override public void onDismiss() {
//            //当popwindow消失的时候，恢复背景的颜色。
//            backgroundAlpha(1.0f); }
//        });
//
//        pop.setBackgroundDrawable(getResources().getDrawable(R.drawable.popupwindow_background));
//
//        pop.setOutsideTouchable(true);
//
//        rubybackgroundAlpha(0.5f);
//
//        rubypop.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() { backgroundAlpha(1.0f); } });

        pop.showAtLocation(v, Gravity.BOTTOM,0,100);
    }


//        /** * /获取是否存在虚拟按键 NavigationBar：如果是有就返回true,如果是没有就是返回的false。第二种方法 */
//    private static boolean checkDeviceHasNavigationBar2(Context context) {
//        boolean hasNavigationBar = false; Resources rs = context.getResources();
//        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
//        if (id > 0) { hasNavigationBar = rs.getBoolean(id); }
//        try { Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
//            Method m = systemPropertiesClass.getMethod("get", String.class);
//            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
//            if ("1".equals(navBarOverride)) { hasNavigationBar = false;
//            } else if ("0".equals(navBarOverride)) {
//                hasNavigationBar = true; }
//        } catch (Exception e) { }
//        return hasNavigationBar; }//``` * 获取navigationbar的高度。代码如下：``` ruby/** * 获取navigationbar的高度。 */

//    private int getNavigationBarHeight() { Resources resources = this.getResources();
//        int resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");
//        int height = resources.getDimensionPixelSize(resourceId); return height;
//    }

//        /**
//         * 设置添加屏幕的背景透明度
//         * @param bgAlpha
//         */
//        public void backgroundAlpha(float bgAlpha)
//        {
//            WindowManager.LayoutParams lp = getWindow().getAttributes();
//            lp.alpha = bgAlpha; //0.0-1.0
//            getWindow().setAttributes(lp);
//        }
//        private void rubybackgroundAlpha(float v) {
//            WindowManager.LayoutParams lp = getWindow().getAttributes();
//            lp.alpha = v; //0.0-1.0
//            getWindow().setAttributes(lp);
//        }

    }
