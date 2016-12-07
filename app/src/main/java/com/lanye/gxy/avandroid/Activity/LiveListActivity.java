package com.lanye.gxy.avandroid.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lanye.gxy.avandroid.Activity.customviews.BaseActivity;
import com.lanye.gxy.avandroid.R;
import com.lanye.gxy.avandroid.adapters.LiveShowAdapter;
import com.lanye.gxy.avandroid.model.CurLiveInfo;
import com.lanye.gxy.avandroid.model.LiveInfoJson;
import com.lanye.gxy.avandroid.model.MySelfInfo;
import com.lanye.gxy.avandroid.presenters.LiveListViewHelper;
import com.lanye.gxy.avandroid.presenters.viewinface.LiveListView;
import com.lanye.gxy.avandroid.utils.Constants;
import com.lanye.gxy.avandroid.utils.SxbLog;

import java.util.ArrayList;
/**
 * Created by Administrator on 2016/11/28.
 */
public class LiveListActivity extends BaseActivity implements View.OnClickListener, LiveListView, SwipeRefreshLayout.OnRefreshListener {
    private ListView mLiveList;
    private ArrayList<LiveInfoJson> liveList = new ArrayList<LiveInfoJson>();
    private LiveShowAdapter adapter;
    private LiveListViewHelper mLiveListViewHelper;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liveframent_layout);
        mLiveList = (ListView)findViewById(R.id.live_list);
        mLiveListViewHelper = new LiveListViewHelper(this);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout_list);
//        mSwipeRefreshLayout.setColorSchemeColors(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
//                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        adapter = new LiveShowAdapter(this, R.layout.item_liveshow, liveList);
        mLiveList.setAdapter(adapter);
        mLiveList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LiveInfoJson item = liveList.get(i);
                //如果是自己
                if (item.getHost().getUid().equals(MySelfInfo.getInstance().getId())) {
                    Intent intent = new Intent(LiveListActivity.this, LiveActivity.class);
                    intent.putExtra(Constants.ID_STATUS, Constants.HOST);
                    MySelfInfo.getInstance().setIdStatus(Constants.HOST);
                    MySelfInfo.getInstance().setJoinRoomWay(false);
                    CurLiveInfo.setHostID(item.getHost().getUid());
                    CurLiveInfo.setHostName(item.getHost().getUsername());
                    CurLiveInfo.setHostAvator(item.getHost().getAvatar());
                    CurLiveInfo.setRoomNum(item.getAvRoomId());
                    CurLiveInfo.setMembers(item.getWatchCount() + 1); // 添加自己
                    CurLiveInfo.setAdmires(item.getAdmireCount());
                    CurLiveInfo.setAddress(item.getLbs().getAddress());
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(LiveListActivity.this,LiveActivity.class);
                    intent.putExtra(Constants.ID_STATUS, Constants.MEMBER);
                    MySelfInfo.getInstance().setIdStatus(Constants.MEMBER);
                    MySelfInfo.getInstance().setJoinRoomWay(false);
                    CurLiveInfo.setHostID(item.getHost().getUid());
                    CurLiveInfo.setHostName(item.getHost().getUsername());
                    CurLiveInfo.setHostAvator(item.getHost().getAvatar());
                    CurLiveInfo.setRoomNum(item.getAvRoomId());
                    CurLiveInfo.setMembers(item.getWatchCount() + 1); // 添加自己
                    CurLiveInfo.setAdmires(item.getAdmireCount());
                    CurLiveInfo.setAddress(item.getLbs().getAddress());
                    startActivity(intent);
                }
              //  SxbLog.i(TAG, "PerformanceTest  join Live     " + SxbLog.getTime());
            }
        });
    }

    @Override
    public void onStart() {
        mLiveListViewHelper.getPageData();
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void onDestroy() {
        mLiveListViewHelper.onDestory();
        super.onDestroy();
    }
    @Override
    public void showFirstPage(ArrayList<LiveInfoJson> result) {
        mSwipeRefreshLayout.setRefreshing(false);
        liveList.clear();
        if (null != result) {
            for (LiveInfoJson item : result) {
                liveList.add(item);
            }
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onClick(View view) {

    }
    @Override
    public void onRefresh() {
        mLiveListViewHelper.getPageData();
    }
}
