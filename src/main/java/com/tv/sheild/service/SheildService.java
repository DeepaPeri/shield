package com.tv.sheild.service;

import com.tv.sheild.datasource.DataSource;
import com.tv.sheild.models.Avenger;
import com.tv.sheild.models.AvengerStatus;
import com.tv.sheild.models.Mission;
import com.tv.sheild.models.MissionStatus;
import java.util.HashSet;
import java.util.Set;

public class SheildService {

    public static void checkMissions(Set<Mission> missions) {
        if (missions.size() < 1) {
            System.out.println("No Mission has been assigned to an Avenger");
        } else {
            missions.forEach(mission -> {
                System.out.println();
                System.out.print(mission.toString() + ", ");
                mission.getAvengers().forEach(
                    avenger -> {
                        System.out.print(avenger.getAvengerName() + ", ");
                    }
                );
            });
        }
    }

    public static void checkMissionsDetails(DataSource dataSource, String missionsName) {
        System.out.println(dataSource.getMission(missionsName).toString());
    }

    public static void assignMissionToAvengers(String[] avengersList, String missionName, String missionDetails, DataSource dataSource) {
        Set<Avenger> avengerSet = new HashSet<>();
        Set<Mission> missionsSet;

        Mission mission = new Mission(missionName, missionDetails, MissionStatus.Assigned);
        for (int i = 0; i < avengersList.length; i++) {
            Avenger avenger = dataSource.getAvenger(avengersList[i]);
            dataSource.removeAvenger(avenger);

            avenger.setStatus(AvengerStatus.OnMission);
            missionsSet = avenger.getMissionAssigned();
            if (missionsSet.size() < 2) {
                missionsSet.add(mission);
            }

            avenger.setMissionAssigned(missionsSet);
            dataSource.addAvenger(avenger);
            avengerSet.add(avenger);

            avenger.getNotificationMediumMedia().forEach(notification -> notification.sendMessage(mission, avenger, "Notification via " + notification.getClass().getSimpleName() + " has been sent to : " + avenger.getAvengerName()));
        }
        mission.setAvengers(avengerSet);
        dataSource.addMission(mission);
    }

    public static void updateMissionStatus(String missionName, DataSource dataSource, String status) {
        Mission mission = dataSource.getMission(missionName);
        dataSource.removeMission(mission);
        switch (status) {
            case "Completed":
                mission.setMissionStatus(MissionStatus.Completed);
                mission.getAvengers().forEach(avenger -> {
                    avenger.addMissionsCompleted(mission);
                    avenger.removeMissionsAssigned(mission);
                    if(avenger.getMissionAssigned().size() < 1){
                        avenger.setStatus(AvengerStatus.Available);
                    }
                });
                break;
            case "Assigned":
                mission.setMissionStatus(MissionStatus.Assigned);
                break;
        }
        dataSource.addMission(mission);
        mission.getAvengers().forEach(
                avenger -> avenger.getNotificationMediumMedia().
                        forEach(notification -> notification.sendMessage(mission, avenger, "Notification via " + notification.getClass().getSimpleName() + " has been sent to : " + avenger.getAvengerName())));

    }

    public static void listAvengers(DataSource dataSource) {
        dataSource.getAvengers().forEach(avenger -> {
            System.out.print(avenger.toString());
            System.out.print(", missions completed : ");
            avenger.getMissionsCompleted().forEach(mission -> System.out.print(mission.getMissionName() + ", "));
            System.out.print(", missions assigned : ");
            avenger.getMissionAssigned().forEach(mission -> System.out.print(mission.getMissionName() + ", "));
            System.out.println();
        });
    }
}