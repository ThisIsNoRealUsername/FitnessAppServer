package bsd.com.fitnessapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class PowerActivity extends AppCompatActivity {
    private String workout;
    private Database db;
    private User u;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // used to align the text of the action bar to center
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_power_activity);

        super.onCreate(savedInstanceState);
        db = new Database(this);
        setContentView(R.layout.activity_power);
        Intent it = getIntent();
        if (it.hasExtra("workout")){
            workout = it.getStringExtra("workout");
            workout.toLowerCase();
        }
    }

    public void onClick(View view) {
        EditText wei = (EditText) findViewById(R.id.editTextWeights);
        EditText rea = (EditText) findViewById(R.id.editTextRepeats);
        db.open();
        Cursor c = db.selectUsers();
        if (c.moveToFirst()){
            do{
                if(c.getString(4).equals("loggedin")){
                    u = new User(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
                }
            } while (c.moveToNext());
        }
        db.close();
        RestTask.get("/get" + workout, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (id < response.getInt("id")){
                        id = response.getInt("id");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray arr) {
                try {
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject firstEvent = arr.getJSONObject(i);
                        if(id < firstEvent.getInt("id")){
                            id = firstEvent.getInt("id");
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        id += 1;
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        RequestParams params = new RequestParams();
        params.put("id", id);
        params.put("actpoint", 0);
        params.put("id_user", u.getId());
        params.put("username", u.getUsername());
        params.put("email", u.getEmail());
        params.put("date", date);
        params.put("gewichte", wei.getText().toString());
        params.put("wiederholungen", rea.getText().toString());
        params.setUseJsonStreamer(true);
        RestTask.put("/" + workout, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //Toast t = Toast.makeText(getApplicationContext(), "Failure:" + responseString, Toast.LENGTH_SHORT);
                //t.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                //Toast t = Toast.makeText(getApplicationContext(), "Success:" + responseString, Toast.LENGTH_SHORT);
                //t.show();
            }
        });
    }
}
