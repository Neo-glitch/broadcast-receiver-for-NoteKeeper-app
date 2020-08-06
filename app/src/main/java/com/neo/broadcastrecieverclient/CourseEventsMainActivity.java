package com.neo.broadcastrecieverclient;

import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;


/**
 * Activity just displays info about the broadcast received
 */
public class CourseEventsMainActivity extends AppCompatActivity
        implements EventDisplayCallbacks {

    ArrayList<String> mCourseEvents;
    ArrayAdapter<String> mCourseEventsAdapter;
    private CourseEventsReceiver mCourseEventsReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_events_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupListView();

        setupCourseEventReceiver();

    }

    private void setupCourseEventReceiver() {               // handles setting up this app's broadcastReceiver
        mCourseEventsReceiver = new CourseEventsReceiver();
        mCourseEventsReceiver.setEventDisplayCallbacks(this);    // specifies that interface obj is this class

        IntentFilter intentFilter = new IntentFilter(CourseEventsReceiver.ACTION_COURSE_EVENT);        // specifies the intent action to match on
        registerReceiver(mCourseEventsReceiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mCourseEventsReceiver);  // unregs the broadcastReceiver
        super.onDestroy();
    }

    protected void setupListView() {
        mCourseEvents = new ArrayList<String>();
        mCourseEventsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mCourseEvents);

        final ListView listView = (ListView) findViewById(R.id.list_course_events);
        listView.setAdapter(mCourseEventsAdapter);
    }

    @Override
    public void onEventReceived(String courseId, String courseMessage) {
        if(courseMessage != null) {
            String displayText = courseId + ": " + courseMessage;
            mCourseEvents.add(displayText);
            mCourseEventsAdapter.notifyDataSetChanged();
        }
    }

}
