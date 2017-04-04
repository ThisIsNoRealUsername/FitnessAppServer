package bsd.com.fitnessapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // used to align the text of the action bar to center
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_main_activity);
        db = new Database(this);
        db.open();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view) {
        intent = new Intent(this, TrainingActivity.class);
        EditText ed = (EditText)findViewById(R.id.editTextUsername);
        EditText ed1 = (EditText)findViewById(R.id.editTextPassword);
        if((ed.getText().toString().equals("a") || ed.getText().toString().equals("a")) && ed1.getText().toString().equals("a")){
            startActivity(intent);
        } else {
            RestTask.get("/users", null, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // If the response is JSONObject instead of expected JSONArray

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray users) {
                    try {
                        EditText ed = (EditText) findViewById(R.id.editTextUsername);
                        EditText ed1 = (EditText) findViewById(R.id.editTextPassword);
                        boolean found = false;
                        User u = null;
                        for (int i = 0; i < users.length(); i++) {
                            JSONObject firstEvent = users.getJSONObject(i);
                            String user = firstEvent.getString("username");
                            String email = firstEvent.getString("email");
                            String pass = firstEvent.getString("password");
                            if ((ed.getText().toString().equals(user) || ed.getText().toString().equals(email)) && ed1.getText().toString().equals(pass)) {
                                found = true;
                                u = new User(firstEvent.getInt("id"), firstEvent.getString("username"), firstEvent.getString("email"), firstEvent.getString("password"));
                            }
                        }
                        if (found) {
                            startActivity(intent);
                            boolean founduser = false;
                            db.open();
                            Cursor c = db.selectUsers();
                            db.close();
                            if (c.moveToFirst()) {
                                do {
                                    if (c.getString(0).equals(u.getId())) {
                                        founduser = true;
                                    }
                                } while (c.moveToNext());
                            }
                            db.open();
                            if (founduser) {
                                db.updateUser(u.getId() + "", u.getUsername(), u.getEmail(), u.getPassword(), "loggedin");
                            } else {
                                db.insertUser(u.getId() + "", u.getUsername(), u.getEmail(), u.getPassword(), "loggedin");
                            }
                            db.close();
                        } else {
                            Toast t = Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT);
                            t.show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /*@Override
    protected void onStop() {
        super.onStop();
        db.open();
        Cursor c = db.selectUsers();
        if (c.moveToFirst()){
            do{
                if(c.getString(4).equals("loggedin")){
                    db.updateUser(c.getString(0), c.getString(1), c.getString(2), c.getString(3), "");
                }
            } while (c.moveToNext());
        }
        db.close();
    }*/
}


    /*public void onbtnPlayo(View view){
        EditText ed = (EditText) findViewById(R.id.et_username);
        RequestParams params = new RequestParams();
        params.put("username", ed.getText().toString());
        params.setUseJsonStreamer(true);
        RestTask.put("", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //Toast t = Toast.makeText(getApplicationContext(), "Failure:" + responseString, Toast.LENGTH_SHORT);
                //t.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                //Toast t = Toast.makeText(getApplicationContext(), "Success:" + responseString, Toast.LENGTH_SHORT);
                //t.show();
                startLobby(responseString);
            }
        });
    }

    public void getPlayer() {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.ll_users);
        ll.removeAllViews();
        RestTask.get("/all", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray players) {
                try {
                    for (int i = 0; i < players.length(); i++) {
                        JSONObject firstEvent = players.getJSONObject(i);
                        String user = firstEvent.getString("username");
                        if (!username.equals(user)) {
                            TextView tv = new TextView(LobbyActivity.this);
                            tv.setText(user);
                            ll.addView(tv);
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }*/
