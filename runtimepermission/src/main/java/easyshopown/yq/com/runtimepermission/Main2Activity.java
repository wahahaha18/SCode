package easyshopown.yq.com.runtimepermission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    ArrayList<String> mStrings = new ArrayList<>();
    private ArrayAdapter<String> mStringArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ListView listView = (ListView) findViewById(R.id.line);
        mStringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStrings);
        listView.setAdapter(mStringArrayAdapter);

        if (ContextCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Main2Activity.this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }else {
            requestContect();
        }

    }
    public void requestContect(){
        Cursor query = null;
        try {
            query = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (query!=null){
                while (query.moveToNext()){
                    String string = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String string1 = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    mStrings.add(string + "\n" +string1);
                }
                mStringArrayAdapter.notifyDataSetChanged();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (query != null){
                query.close();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    requestContect();
                }else {
                    Toast.makeText(this, "you  denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
