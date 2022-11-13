package com.kennedfer.football_bv_championship_app;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listMatches;
    FloatingActionButton scrollUpdFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        scrollUpdFab = findViewById(R.id.scrollUpFab);
        listMatches = this.findViewById(R.id.listMatchs);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String response = sharedPreferences.getString(getString(R.string.shared_prefs_matches_key),"");

        List<Match> matches = createMatchesList(response);
        AdapterMatchs adapter = new AdapterMatchs(matches, this);

        listMatches.setAdapter(adapter);
        scrollUpdFab.setOnClickListener(view -> listMatches.smoothScrollToPosition(0));

        listMatches.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {}

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if(listMatches.getLastVisiblePosition() >1){
                    if(scrollUpdFab.getVisibility() == View.INVISIBLE){
                        scrollUpdFab.setVisibility(View.VISIBLE);
                        scrollUpdFab.animate().setDuration(200).scaleX(1).scaleY(1);
                    }
                }else{
                    if(scrollUpdFab.getVisibility() == View.VISIBLE){
                        scrollUpdFab.animate().setDuration(200).scaleX(0).scaleY(0).withEndAction(() -> scrollUpdFab.setVisibility(View.INVISIBLE));
                    }
                }
            }
        });
    }

    private List<Match> createMatchesList(String resp){
        List<Match> matches = new ArrayList<>();

        if(!resp.equals("")) {
            String[] matchs = resp.split("\n");

            for (String s : matchs) {
                String[] match = s.split(",");
                matches.add(new Match(match[0], match[1], match[2]));
            }
        }

        return matches;
    }
}