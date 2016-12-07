package com.lanye.gxy.avandroid.Activity.customviews;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.widget.ListView;
import com.lanye.gxy.avandroid.R;
import com.lanye.gxy.avandroid.adapters.MembersAdapter;
import com.lanye.gxy.avandroid.model.MemberInfo;
import com.lanye.gxy.avandroid.presenters.GetMemberListHelper;
import com.lanye.gxy.avandroid.presenters.viewinface.LiveView;
import com.lanye.gxy.avandroid.presenters.viewinface.MembersDialogView;
import java.util.ArrayList;
/**
 * 成员列表
 */
public class MembersDialog extends Dialog implements MembersDialogView {
    private Context mContext;
    private GetMemberListHelper mGetMemberListHelper;
    private ListView mMemberList;
    private MembersAdapter mMembersAdapter;
    private ArrayList<MemberInfo> data = new ArrayList<MemberInfo>();
    public MembersDialog(Context context, int theme, LiveView view) {
        super(context, theme);
        mContext = context;
        setContentView(R.layout.members_layout);
        mMemberList = (ListView) findViewById(R.id.member_list);
        mMembersAdapter = new MembersAdapter(mContext, R.layout.members_item_layout, data, view, this);
        mMemberList.setAdapter(mMembersAdapter);
        Window window = getWindow();
        window.setGravity(Gravity.TOP);
        setCanceledOnTouchOutside(true);
    }
    @Override
    protected void onStart() {
        //获取成员信息
        mGetMemberListHelper = new GetMemberListHelper(mContext, this);
        mGetMemberListHelper.getMemberList();
        super.onStart();
    }
    @Override
    protected void onStop() {
        mGetMemberListHelper.onDestory();
        super.onStop();
    }
    /**
     * 通过Helper获得数据
     * @param data
     */
    @Override
    public void showMembersList(ArrayList<MemberInfo> data) {
        if (data == null) return;
        mMembersAdapter.clear();
        for (int i = 0; i < data.size(); i++) {
            mMembersAdapter.insert(data.get(i), i);
        }
        mMembersAdapter.notifyDataSetChanged();
    }
}
