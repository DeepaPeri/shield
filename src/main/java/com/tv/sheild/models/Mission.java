package com.tv.sheild.models;

import java.util.Set;

public class Mission {

    private String missionName;

    private String missionDetails;

    private MissionStatus missionStatus;

    private Set<Avenger> avengers;

    public String getMissionName() {
        return missionName;
    }

    public Mission(String missionName, String missionDetails, MissionStatus missionStatus) {
        this.missionName = missionName;
        this.missionDetails = missionDetails;
        this.missionStatus = missionStatus;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getMissionDetails() {
        return missionDetails;
    }

    public void setMissionDetails(String missionDetails) {
        this.missionDetails = missionDetails;
    }

    public MissionStatus getMissionStatus() {
        return missionStatus;
    }

    public void setMissionStatus(MissionStatus missionStatus) {
        this.missionStatus = missionStatus;
    }

    public Set<Avenger> getAvengers() {
        return avengers;
    }

    public void setAvengers(Set<Avenger> avengers) {
        this.avengers = avengers;
    }

    @Override
    public String toString() {
        return "Mission Name : " + missionName +  ", details : " + missionDetails + " status : "+ missionStatus;
    }
}
