package com.kennedfer.football_bv_championship_app;

public class Match {
    private final String firstTeamName;
    private final String secondTeamName;
    private final String result;

    public Match(String fn, String sn, String r){
        this.firstTeamName = fn;
        this.secondTeamName = sn;
        this.result = r;
    }

    public String getFirstTeamName() {
        return firstTeamName;
    }
    public String getSecondTeamName() {
        return secondTeamName;
    }
    public String getResult() {
        return result;
    }

}
