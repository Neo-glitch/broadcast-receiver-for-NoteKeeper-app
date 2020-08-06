package com.neo.broadcastrecieverclient;

/**
 * Created by Jim.
 */

/**
 * used to pass info about received broadcast to the apps main Activity.
 */
interface EventDisplayCallbacks {
    void onEventReceived(String courseId, String courseMessage);
}
