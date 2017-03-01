package easyshopown.yq.com.materailtest;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FruitActivity extends AppCompatActivity {
    public static final String FRUIT_NAME = "name";
    public static final String FRUIT_IMGID = "img_id";

    public static void openData(Context context,String name,int id){

        Intent intent = new Intent(context,FruitActivity.class);
        intent.putExtra(FRUIT_NAME,name);
        intent.putExtra(FRUIT_IMGID,id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        String str_name = getIntent().getStringExtra(FRUIT_NAME);
        int int_id = getIntent().getIntExtra(FRUIT_IMGID, 0);
        NestedScrollView nestedScrollView = (NestedScrollView) findViewById(R.id.nsv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.ctl);
        ImageView imageView = (ImageView) findViewById(R.id.iv);
        TextView textView = (TextView) findViewById(R.id.fc_tv);

        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(str_name);
        Glide.with(this).load(int_id).into(imageView);
        textView.setText(content(str_name));
    }

    private String content(String str_name) {
        StringBuffer stringBuffer = new StringBuffer(str_name);
        for (int i = 0; i < 50; i++) {
            stringBuffer.append(str_name);
        }

        return stringBuffer.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

        }
        return true;
    }
}
