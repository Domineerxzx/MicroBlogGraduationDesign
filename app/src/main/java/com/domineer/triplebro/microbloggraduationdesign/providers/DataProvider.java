package com.domineer.triplebro.microbloggraduationdesign.providers;

import com.domineer.triplebro.microbloggraduationdesign.models.IssueImageInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;
import com.domineer.triplebro.microbloggraduationdesign.models.UserInfo;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/8/15,23:26
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
interface DataProvider {
    UserInfo queryUserInfoById(int userId);

    List<IssueInfo> getAllIssueInfoList();

    List<IssueInfo> getIssueInfoListByType(String type);

    List<List<IssueImageInfo>> getIssueImageInfoListByIssueInfoList(List<IssueInfo> issueInfoList);
}
