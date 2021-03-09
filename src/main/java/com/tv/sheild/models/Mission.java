package com.tv.sheild.models;

import java.util.Set;

public class Mission {

    private String missionName;

    private String missionDetails;

    private MissionStatus missionStatus;

    private Set<Avenger> avengers;

    public Mission(String missionName, String missionDetails, MissionStatus missionStatus) {
        this.missionName = missionName;
        this.missionDetails = missionDetails;
        this.missionStatus = missionStatus;
    }

    public String getMissionName() {
        return missionName;
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
        String avengersCommaSeperated = "| ";
        for (Avenger avenger : avengers) {
            avengersCommaSeperated = avengersCommaSeperated.concat(avenger.getAvengerName() + "| ");
        }
        return "Mission Name : " + missionName + ", mission status : " + missionStatus + ", avengers : " + avengersCommaSeperated;
    }
}
