package com.seven.actionbar;

import android.app.ActionBar;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventsDetailActivity extends Activity {

    public MyApp myApp;

    private static String url_detail_events = "http://www.ufgatorevents.com/android_connect/get_events_details.php";
    private static String url_join = "http://www.ufgatorevents.com/android_connect/join_events.php";
    private static String url_leave = "http://www.ufgatorevents.com/android_connect/leave_event.php";
    private ProgressDialog pDialog;

    // JSON Node names
    private static final String TAG_SUCCESS = "success";


    // events JSONArray
    JSONArray events = null;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    ActionBar actionBar;
    TextView tDes;
    TextView tVenue;
    TextView tDate;
    TextView tTime;
    TextView tPdate;
    TextView tPtime;
    TextView tCont;
    TextView tOrg;
    TextView tCount;

    String mCat;
    String mName;
    String mDes;
    String mVenue;
    String mDate;
    String mTime;
    String mPdate;
    String mPtime;
    String mCont;
    String mOrg;
    String mCount;

    // UI Components -  switch and drawable for action bar
    Switch goingSwitch;
    ColorDrawable actionBarColor;

    HashMap<String, String> joinMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventsdetail);

        actionBarColor =
                new ColorDrawable(ContextCompat.getColor(getApplicationContext(), R.color.ufl_orange));

        tDes = (TextView)findViewById(R.id.evt_desc);
        tVenue = (TextView)findViewById(R.id.evt_venue);
        tDate = (TextView)findViewById(R.id.evt_date);
        tTime = (TextView)findViewById(R.id.evt_time);
        tPdate = (TextView)findViewById(R.id.evt_post_date);
        tPtime = (TextView)findViewById(R.id.evt_post_time);
        tCont = (TextView)findViewById(R.id.evt_contact);
        tOrg = (TextView)findViewById(R.id.evt_org);
        tCount = (TextView)findViewById(R.id.evt_count);

        //Intent intent = getIntent();
        //joinMap = (HashMap)intent.getSerializableExtra("e_uMap");
        myApp = (MyApp)getApplication();

        //action bar magic
        actionBarColor.setAlpha(0);
        getActionBar().setBackgroundDrawable(actionBarColor);
        ((NotifyingScrollView) findViewById(R.id.scroll_view))
                .setOnScrollChangedListener(mOnScrollChangedListener);

        new LoadDetail().execute();

        goingSwitch = (Switch) findViewById(R.id.btn_join);
        goingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()

        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                new JoinEvents().execute(String.valueOf(isChecked));
            }
        });
    }

    private NotifyingScrollView.OnScrollChangedListener mOnScrollChangedListener =
            new NotifyingScrollView.OnScrollChangedListener()
            {
                public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt)
                {
                    final int headerHeight =
                            findViewById(R.id.image_header).getHeight() - getActionBar().getHeight();
                    final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
                    final int newAlpha = (int) (ratio * 255);
                    actionBarColor.setAlpha(newAlpha);
            }
    };


    @Override
    public void onStart(){
        super.onStart();
        actionBar = this.getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
    }


    /******************************Join******************************************************/
    /**
     * Background Async Task to Load all event by making HTTP Request
     * */
    class JoinEvents extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(EventsDetailActivity.this);//EventsActivity
            pDialog.setMessage("Attending. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * joinning
         * */
        protected String doInBackground(String... args) {
            //String name = inputName.getText().toString();

            // Building Parameters
            String going = args[0];
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("E_id", myApp.e_uMap.get("E_id")));
            // Building Parameters
            //List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL

            JSONObject json;

            if (going == "true")
            {
                json = jParser.makeHttpRequest(url_join, "GET", params);
            }

            else
            {
                json = jParser.makeHttpRequest(url_leave, "GET", params);
            }

            // Check your log cat for JSON reponse
//            Log.d("All Events: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {

                    Intent mInt = new Intent(getApplicationContext(), LoginActivity.class);
                    PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(),
                            (int) System.currentTimeMillis(),mInt,0);
                    Notification n = new Notification.Builder(getApplicationContext())
                            .setContentTitle("You've joined one event, please check it!")
                            .setContentText("GatorEvents")
                            .setSmallIcon(R.drawable.gatorevents)
                            .setContentIntent(pIntent)
                            .addAction(R.drawable.gatorevents, "Have a look!", pIntent).build();
                    NotificationManager notificationManager = (NotificationManager)
                            getSystemService(NOTIFICATION_SERVICE);
                    n.flags |= Notification.FLAG_AUTO_CANCEL;
                    notificationManager.notify(0,n);


                } else {
                    // no events found, go to home page
                    Intent i = new Intent(getApplicationContext(),
                            MainActivity.class);//HomeAcitivity
                    // Closing all previous activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            catch(NullPointerException e){
                e.printStackTrace();
            }
            catch(RuntimeException e){
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all events
            pDialog.dismiss();
        }

    }

    /****************************************************************************************/

    /**
     * Background Async Task to Load all event by making HTTP Request
     * */
    class LoadDetail extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(EventsDetailActivity.this);//EventsActivity
            pDialog.setMessage("Loading event. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All events from url
         * */
        protected String doInBackground(String... args) {
            //String name = inputName.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("eid", myApp.e_uMap.get("E_id")));
            // Building Parameters
            //List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_detail_events, "GET", params);

            // Check your log cat for JSON reponse
//            Log.d("All Events: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // events found
                    // Getting Array of events
                    events = json.getJSONArray("events");

                    // looping through All Events
                    for (int i = 0; i < events.length(); i++) {
                        JSONObject c = events.getJSONObject(i);

                        // Storing each json item in variable
                        mCat = c.getString("Category");
                        mName =  c.getString("E_name");
                        mDes = c.getString("Description");
                        mVenue =  c.getString("Venue");
                        mDate =  c.getString("EDate");
                        mTime = c.getString("Time");
                        mPdate = c.getString("Post_date");
                        mPtime = c.getString("Post_time");
                        mCont = c.getString("Contact_Person");
                        mOrg = c.getString("Organization");
                        mCount = c.getString("Count");

                        // creating new HashMap
                     /*   HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_EID, id);
                        map.put(TAG_NAME, name);
                        map.put(TAG_DES, decrp);

                        // adding HashList to ArrayList
                        eventsList.add(map);
                     */
                    }
                } else {
                    // no events found, go to home page
                    Intent i = new Intent(getApplicationContext(),
                            MainActivity.class);//HomeAcitivity
                    // Closing all previous activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            catch(NullPointerException e){
                e.printStackTrace();
            }
            catch(RuntimeException e){
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all events
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView   EventsActivity
                     * */
                  /* ListAdapter adapter = new SimpleAdapter(
                            MainActivity.this, eventsList,
                            R.layout.events_detail, new String[] { TAG_EID,
                            TAG_NAME, TAG_DES},
                            new int[] { R.id.eid, R.id.name, R.id.decrp});
                    // updating listview
                    lv.setAdapter(adapter);*/
//                    tCat.setText(mCat);
//                    tName.setText(mName);


                    actionBar.setTitle(mName);
                    tDes.setText(mDes);
                    tVenue.setText(mVenue);
                    tDate.setText(mDate);
                    tTime.setText(mTime);
                    tPdate.setText(mPdate);
                    tPtime.setText(mPtime);
                    tCont.setText(mCont);
                    tOrg.setText(mOrg);
                    tCount.setText(mCount);
                }
            });


        }

    }

}
