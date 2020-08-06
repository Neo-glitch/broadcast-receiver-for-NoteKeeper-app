package com.neo.broadcastrecieverclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// broadcast receiver for this app
public class CourseEventsReceiver extends BroadcastReceiver {
    public static final String ACTION_COURSE_EVENT = "com.neo.notekeeperpluralsight.action.COURSE_EVENT";   // app defined action

    public static final String EXTRA_COURSE_ID = "com.neo.notekeeperpluralsight.extra.COURSE_ID";
    public static final String EXTRA_COURSE_MESSAGE = "com.neo.notekeeperpluralsight.extra.COURSE_MESSAGE";

    private EventDisplayCallbacks mEventDisplayCallbacks;    // callback listener

    public void setEventDisplayCallbacks(EventDisplayCallbacks eventDisplayCallbacks) {
        mEventDisplayCallbacks = eventDisplayCallbacks;
    }



    @Override
    public void onReceive(Context context, Intent intent) {  // runs on mainThread, so work gonna be quick
        if(ACTION_COURSE_EVENT.equals(intent.getAction())){  // checks to see if action passed to intent is same as what receiver can handle

            String courseId = intent.getStringExtra(EXTRA_COURSE_ID);
            String courseMessage = intent.getStringExtra(EXTRA_COURSE_MESSAGE);
            if(mEventDisplayCallbacks != null){
                mEventDisplayCallbacks.onEventReceived(courseId, courseMessage);    // calls this method of the class implementing interface
            }
        }
    }
}
