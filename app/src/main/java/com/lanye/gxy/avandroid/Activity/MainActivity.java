package com.lanye.gxy.avandroid.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lanye.gxy.avandroid.R;
import com.lanye.gxy.avandroid.model.CurLiveInfo;
import com.lanye.gxy.avandroid.model.MySelfInfo;
import com.lanye.gxy.avandroid.utils.Constants;
import com.tencent.av.sdk.AVContext;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AVContext .createInstance(this);
        Log.i("HelloSDK", "SDK Version = " + AVContext.getVersion());
        Toast.makeText(this,"hah",Toast.LENGTH_SHORT).show();
        init();
    }
    private void init() {
        Button btnLive = (Button) findViewById(R.id.btnLive);
        Button btnGetHomeList = (Button) findViewById(R.id.btnGetHomeList);
        Button btnAddTheHome = (Button) findViewById(R.id.btnAddTheHome);
        btnAddTheHome.setOnClickListener(this);
        btnGetHomeList.setOnClickListener(this);
        btnLive.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLive:
                Intent intent = new Intent(this, MyLiveActivity.class);
                intent.putExtra(Constants.ID_STATUS, Constants.HOST);
                MySelfInfo.getInstance().setIdStatus(Constants.HOST);
                MySelfInfo.getInstance().setJoinRoomWay(true);
                CurLiveInfo.setTitle("我的直播");
                CurLiveInfo.setHostID(MySelfInfo.getInstance().getId());
                CurLiveInfo.setRoomNum(MySelfInfo.getInstance().getMyRoomNum());
                startActivity(intent);
                break;
            case R.id.btnGetHomeList:
                Intent intents = new Intent(this,LiveListActivity.class);
                startActivity(intents);
                break;
            case R.id.btnAddTheHome:
                Intent intent3 = new Intent(MainActivity.this, LiveActivity.class);
                intent3.putExtra(Constants.ID_STATUS, Constants.MEMBER);
                MySelfInfo.getInstance().setIdStatus(Constants.MEMBER);
                MySelfInfo.getInstance().setJoinRoomWay(false);
                CurLiveInfo.setHostID("dksjjnn");
                CurLiveInfo.setRoomNum(271317);
                startActivity(intent3);
                break;
        }
    }
}
