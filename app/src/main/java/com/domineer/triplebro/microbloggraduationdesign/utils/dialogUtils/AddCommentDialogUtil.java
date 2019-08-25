package com.domineer.triplebro.microbloggraduationdesign.utils.dialogUtils;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.adapters.CommentAdapter;
import com.domineer.triplebro.microbloggraduationdesign.managers.CommentManager;
import com.domineer.triplebro.microbloggraduationdesign.models.CommentInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;
import com.domineer.triplebro.microbloggraduationdesign.properties.ProjectProperties;
import com.domineer.triplebro.microbloggraduationdesign.views.MyListView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Domineer
 * @data 2019/8/26,3:06
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class AddCommentDialogUtil {

    public static void showAddCommentDialog(final Context context, final int tag, final Serializable serializable, final MyListView lv_comment, final RelativeLayout rl_comment, final RelativeLayout rl_comment_null){
        final Dialog addCommentDialog = new Dialog(context, R.style.dialog_add_comment);
        addCommentDialog.setCanceledOnTouchOutside(true);
        addCommentDialog.setCancelable(true);
        Window window = addCommentDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        View view = View.inflate(context, R.layout.dialog_add_comment, null);
        final EditText et_add_comment = (EditText) view.findViewById(R.id.et_add_comment);
        TextView tv_add_comment = (TextView) view.findViewById(R.id.tv_add_comment);
        final CommentManager commentManager = new CommentManager(context);
        tv_add_comment.setOnClickListener(new View.OnClickListener() {

            private CommentAdapter commentAdapter;
            private List<CommentInfo> commentInfoList;
            private ContentValues contentValues;
            private String time;
            private long timeStamp;
            private SimpleDateFormat simpleDateFormat;
            private int user_id;
            private SharedPreferences userInfo;

            @Override
            public void onClick(View v) {
                String comment_content = et_add_comment.getText().toString().trim();
                switch (tag){
                    case ProjectProperties.ADD_COMMENT:
                        IssueInfo issueInfo = (IssueInfo) serializable;
                        userInfo = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                        int user_id = userInfo.getInt("user_id", 0);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                        long timeStamp = System.currentTimeMillis();
                        String time = simpleDateFormat.format(timeStamp);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("user_id",user_id);
                        contentValues.put("issue_id",issueInfo.get_id());
                        contentValues.put("comment_content",comment_content);
                        contentValues.put("time", time);
                        contentValues.put("comment_id",0);
                        commentManager.addCommentInfo(contentValues);
                        commentInfoList = commentManager.getCommentInfoList(issueInfo.get_id());
                        commentAdapter = new CommentAdapter(context, commentInfoList);
                        lv_comment.setAdapter(commentAdapter);
                        if(commentInfoList.size()==0){
                            rl_comment_null.setVisibility(View.VISIBLE);
                            rl_comment.setVisibility(View.GONE);
                        }else{
                            rl_comment_null.setVisibility(View.GONE);
                            rl_comment.setVisibility(View.VISIBLE);
                        }
                        addCommentDialog.dismiss();
                        break;
                    case ProjectProperties.ADD_COMMENT_IN_COMMENT:
                        CommentInfo commentInfo = (CommentInfo) serializable;
                        SharedPreferences userInfo = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                        user_id = userInfo.getInt("user_id", 0);
                        simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                        timeStamp = System.currentTimeMillis();
                        time = simpleDateFormat.format(timeStamp);
                        contentValues = new ContentValues();
                        contentValues.put("user_id", user_id);
                        contentValues.put("issue_id",commentInfo.getIssueId());
                        contentValues.put("comment_content",comment_content);
                        contentValues.put("time", time);
                        contentValues.put("comment_id",commentInfo.get_id());
                        commentManager.addCommentInfo(contentValues);
                        commentInfoList = commentManager.getCommentInfoList(commentInfo.getIssueId());
                        commentAdapter = new CommentAdapter(context, commentInfoList);
                        lv_comment.setAdapter(commentAdapter);
                        if(commentInfoList.size()==0){
                            rl_comment_null.setVisibility(View.VISIBLE);
                            rl_comment.setVisibility(View.GONE);
                        }else{
                            rl_comment_null.setVisibility(View.GONE);
                            rl_comment.setVisibility(View.VISIBLE);
                        }
                        addCommentDialog.dismiss();
                        break;
                }
            }
        });
        window.setContentView(view);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        addCommentDialog.show();
    }
}
