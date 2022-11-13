package com.kennedfer.football_bv_championship_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class SplashScreenActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        Context context = this;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor sharedEditor = sharedPreferences.edit();

        RequestQueue queue = Volley.newRequestQueue(this);
        final String URL ="https://kennedfer.github.io//bv/results.txt";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                response -> {
                    sharedEditor.putString(getString(R.string.shared_prefs_matches_key), response);
                    sharedEditor.apply();

                    initMainActivity();
                }, error -> {
                    Toast.makeText(context, "Unable to get newest data", Toast.LENGTH_LONG).show();
                    initMainActivity();
                });


        queue.add(stringRequest);
    }

    private void initMainActivity(){
        Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(i);
    }
}
