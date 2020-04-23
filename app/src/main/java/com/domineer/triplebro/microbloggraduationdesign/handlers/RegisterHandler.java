package com.domineer.triplebro.microbloggraduationdesign.handlers;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Message;

import com.domineer.triplebro.microbloggraduationdesign.activities.AdminManagerActivity;
import com.domineer.triplebro.microbloggraduationdesign.activities.RegisterActivity;
import com.domineer.triplebro.microbloggraduationdesign.properties.ProjectProperties;


public class RegisterHandler extends Handler {

    private Context context;
    private ServiceConnection serviceConnection;

    public RegisterHandler(Context context, ServiceConnection serviceConnection) {
        this.context = context;
        this.serviceConnection = serviceConnection;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case ProjectProperties.REGISTER_SUCCESS:
                context.unbindService(serviceConnection);
                ((RegisterActivity) context).finish();
                break;
            case ProjectProperties.GET_REQUEST_CODE_SUCCESS:
            case ProjectProperties.GET_REQUEST_CODE_FAILED:
            case ProjectProperties.REGISTER_FAILED:
            case ProjectProperties.REGISTER_ADMIN_SUCCESS:
                context.unbindService(serviceConnection);
                break;
        }
    }
}
