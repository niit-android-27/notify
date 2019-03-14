package com.luan.session10_view.notifyuser;

import android.app.Application;

import com.onesignal.OneSignal;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
}
