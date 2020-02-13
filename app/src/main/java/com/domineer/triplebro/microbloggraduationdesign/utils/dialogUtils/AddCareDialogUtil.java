package com.domineer.triplebro.microbloggraduationdesign.utils.dialogUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.microbloggraduationdesign.R;
import com.domineer.triplebro.microbloggraduationdesign.activities.ChatContentActivity;
import com.domineer.triplebro.microbloggraduationdesign.fragments.IssueFragment;
import com.domineer.triplebro.microbloggraduationdesign.fragments.MyselfFragment;
import com.domineer.triplebro.microbloggraduationdesign.managers.CareManager;
import com.domineer.triplebro.microbloggraduationdesign.models.ChatInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;
import com.domineer.triplebro.microbloggraduationdesign.properties.ProjectProperties;
import com.domineer.triplebro.microbloggraduationdesign.utils.PermissionUtil;

import java.io.File;

public class AddCareDialogUtil {

    public static void showDialog(final Context context, final UserInfo userInfo) {
        View view = View.inflate(context, R.layout.dialog_add_care, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.setView(view).create();
        final ImageView iv_user_head = (ImageView) view.findViewById(R.id.iv_user_head);
        TextView tv_nickname = (TextView) view.findViewById(R.id.tv_nickname);
        final TextView tv_add_care = (TextView) view.findViewById(R.id.tv_add_care);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        if (userInfo.getUserHead() != null && userInfo.getUserHead().length() != 0) {
            Glide.with(context).load(userInfo.getUserHead()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head);
        }else{
            Glide.with(context).load(R.drawable.user_head_default).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head);
        }

        tv_nickname.setText(userInfo.getNickname());
        SharedPreferences localUserInfo = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final int user_id = localUserInfo.getInt("user_id", 0);
        final CareManager careManager = new CareManager(context);
        final boolean isCared = careManager.queryIsCared(userInfo.get_id(), user_id);
        if (isCared) {
            tv_add_care.setText("取消关注");
            tv_add_care.setClickable(false);
        } else {
            tv_add_care.setText("关注");
            tv_add_care.setClickable(true);
        }
        tv_add_care.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (tv_add_care.getText().equals("关注")) {
//                    if(userInfo.get_id()== user_id){
//                        Toast.makeText(context, "不能关注自己哦", Toast.LENGTH_SHORT).show();
//                        return;
//                    }else{
                    careManager.addCare(userInfo.get_id(), user_id);
                    Toast.makeText(context, "关注成功", Toast.LENGTH_SHORT).show();
                    //}
                } else {
                    careManager.deleteCare(userInfo.get_id(), user_id);
                    Toast.makeText(context, "取消关注成功", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        tv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCared) {
                    Toast.makeText(context, "还没关注呢，不能留言", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (userInfo.get_id() == user_id) {
                    Toast.makeText(context, "不能给自己留言哦", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent chat_content = new Intent(context, ChatContentActivity.class);
                chat_content.putExtra("chatUserInfo", userInfo);
                context.startActivity(chat_content);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
