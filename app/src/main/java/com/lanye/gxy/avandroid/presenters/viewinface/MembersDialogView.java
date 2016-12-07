package com.lanye.gxy.avandroid.presenters.viewinface;

import com.lanye.gxy.avandroid.model.MemberInfo;

import java.util.ArrayList;


/**
 * 成员列表回调
 */
public interface MembersDialogView extends MvpView {

    void showMembersList(ArrayList<MemberInfo> data);

}
