package com.domineer.triplebro.microbloggraduationdesign.providers;

import android.os.Message;

import com.domineer.triplebro.microbloggraduationdesign.handlers.AdPictureHandler;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;
import com.domineer.triplebro.microbloggraduationdesign.properties.ProjectProperties;
import com.domineer.triplebro.microbloggraduationdesign.utils.httpUtils.HttpUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author Domineer
 * @data 2019/8/25,7:20
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class HttpProvider implements DataProvider {
    @Override
    public UserInfo queryUserInfoById(int userId) {
        return null;
    }

    @Override
    public List<IssueInfo> getAllIssueInfoList() {
        return null;
    }

    @Override
    public List<IssueInfo> getIssueInfoListByType(String type) {
        return null;
    }

    @Override
    public List<List<IssueImageInfo>> getIssueImageInfoListByIssueInfoList(List<IssueInfo> issueInfoList) {
        return null;
    }

    public void getAdPicture(String adPicturePath, final AdPictureHandler adPictureHandler) {
        HttpUtils.sendOkHttpRequest(adPicturePath, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String adPicture = response.body().string();
                Message message = new Message();
                message.obj = adPicture;
                message.what = ProjectProperties.AD_PICTURE;
                adPictureHandler.sendMessage(message);
                try {
                    Thread.sleep(2500);
                    adPictureHandler.sendEmptyMessage(ProjectProperties.SKIP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
