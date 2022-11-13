package com.kennedfer.football_bv_championship_app;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterMatchs extends BaseAdapter {
    private List<Match> matches;
    private Activity act;

    public AdapterMatchs(List<Match> matches, Activity act){
        this.matches = matches;
        this.act = act;
    }

    @Override
    public int getCount() {
        return matches.size();
    }

    @Override
    public Object getItem(int i) {
        return matches.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = act.getLayoutInflater().inflate(R.layout.list_match, viewGroup, false);
        Match match = matches.get(i);

        TextView txtFirstTeam = view.findViewById(R.id.txtFirstTeam);
        TextView txtSecondTeam = view.findViewById(R.id.txtSecondTeam);
        TextView txtResult = view.findViewById(R.id.txtResult);
        ImageView imgFirstTeam = view.findViewById(R.id.imgFirstTeam);
        ImageView imgSecondTeam = view.findViewById(R.id.imgSecondTeam);

        txtFirstTeam.setText(match.getFirstTeamName());
        txtSecondTeam.setText(match.getSecondTeamName());

        imgFirstTeam.setBackground(act.getDrawable(R.drawable.image_background));
        imgSecondTeam.setBackground(act.getDrawable(R.drawable.image_background));

        imgFirstTeam.setImageDrawable(act.getResources().getDrawable(R.drawable.default_team_image));
        imgSecondTeam.setImageDrawable(act.getResources().getDrawable(R.drawable.default_team_image));

        txtResult.setText(match.getResult());

        return view;
    }
}
