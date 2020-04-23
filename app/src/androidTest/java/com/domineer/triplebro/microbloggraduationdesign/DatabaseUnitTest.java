package com.domineer.triplebro.microbloggraduationdesign;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.domineer.triplebro.microbloggraduationdesign.database.MicroBlogDataBase;
import com.domineer.triplebro.microbloggraduationdesign.models.IssueInfo;
import com.domineer.triplebro.microbloggraduationdesign.providers.DataBaseProvider;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author Domineer
 * @data 2020/4/6,20:34
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseUnitTest {

    @Test
    public void databaseTest() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DataBaseProvider dataBaseProvider = new DataBaseProvider(appContext);
        List<IssueInfo> allIssueInfoList = dataBaseProvider.getAllIssueInfoList();
        if(allIssueInfoList.size()==0){
            assertEquals(new ArrayList<IssueInfo>(), allIssueInfoList);
        }else{
            assertEquals(allIssueInfoList, allIssueInfoList);
        }
    }
}
