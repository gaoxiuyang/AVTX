package com.lanye.gxy.avandroid.presenters.viewinface;



import com.lanye.gxy.avandroid.model.LiveInfoJson;

import java.util.ArrayList;


/**
 *  列表页面回调
 */
public interface LiveListView extends MvpView{

    void showFirstPage(ArrayList<LiveInfoJson> livelist);
}
