package com.tv.shield.datasource;

import com.tv.shield.models.Avenger;
import com.tv.shield.models.Captain;
import com.tv.shield.models.Mission;
import java.util.Set;

public interface InMemoryDataSource {
    Set<Avenger> createAvengers();

    Set<Captain> createCaptains();

    void addCaptain(Captain captain);

    void removeCaptain(Captain captain);

    void addAvenger(Avenger avenger);

    void removeAvenger(Avenger avenger);

    Set<Avenger> getAvengers();

    void setAvengers(Set<Avenger> avengers);

    Set<Captain> getCaptains();

    void setCaptains(Set<Captain> captains);

    void addMission(Mission mission);

    void removeMission(Mission mission);

    Set<Mission> getMissions();

    void setMissions(Set<Mission> missions);

    Mission getMission(String missionName);

    Avenger getAvenger(String avengerName);

    Captain getCaptain(String captainName);
}
