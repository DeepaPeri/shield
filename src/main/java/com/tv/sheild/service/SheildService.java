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
        if (missions.isEmpty()) {
            System.out.println("No Mission has been assigned to an Avenger");
        } else {
            missions.forEach(mission -> {
                System.out.println(mission.toString());
            });
        }
    }

    public static void checkMissionsDetails(DataSource dataSource, String missionsName) {
        Mission mission = dataSource.getMission(missionsName);
        System.out.println(mission.toString() + ", mission details : " + mission.getMissionDetails());
    }

    public static void checkAvengerDetails(DataSource dataSource, String avengerName) {
        Avenger avenger = dataSource.getAvenger(avengerName);
        System.out.println(avenger.toString() + ", missions assigned : " + avenger.getMissionAssigned().size() + ", missions completed : " + avenger.getMissionsCompleted().size());
    }

    public static void sendNotification(Avenger avenger, Mission mission) {
        avenger.getNotificationMediumMedia().forEach(notification -> notification.sendMessage(mission, avenger, "Notification via " + notification.getClass().getSimpleName() + " has been sent to : " + avenger.getAvengerName()));
    }

    public static void assignMissionToAvengers(String[] avengersList, String missionName, String missionDetails, DataSource dataSource) {
        Set<Avenger> avengerSet = new HashSet<>();
        Set<Mission> missionsSet;
        Mission mission = new Mission(missionName, missionDetails, MissionStatus.ASSIGNED);

        for (int i = 0; i < avengersList.length; i++) {
            Avenger avenger = dataSource.getAvenger(avengersList[i]);

            if (avenger == null) {
                System.out.println("Avenger is not in S.H.I.E.L.D, please assign valied avenger");
                return;
            }

            missionsSet = avenger.getMissionAssigned();
            if (missionsSet.size() == 2) {
                System.out.println("Avenger " + avenger.getAvengerName() + " already has two missions assigned, current mission cannot be assigned");
                return;
            }

            missionsSet.add(mission);
            dataSource.removeAvenger(avenger);
            avenger.setStatus(AvengerStatus.ON_MISSION);

            avenger.setMissionAssigned(missionsSet);
            dataSource.addAvenger(avenger);
            avengerSet.add(avenger);

            SheildService.sendNotification(avenger, mission);
        }
        mission.setAvengers(avengerSet);
        dataSource.addMission(mission);
    }

    public static void updateMissionStatus(String missionName, DataSource dataSource, String status) {
        Mission mission = dataSource.getMission(missionName);
        if (mission == null) {
            System.out.println("Invalid mission name, the request mission doesn't exist");
            return;
        }
        dataSource.removeMission(mission);
        switch (status) {
            case "Completed":
                mission.setMissionStatus(MissionStatus.COMPLETED);
                mission.getAvengers().forEach(avenger -> {
                    avenger.addMissionsCompleted(mission);
                    avenger.removeMissionsAssigned(mission);
                    if (!avenger.getMissionAssigned().isEmpty() && (avenger.getMissionAssigned().size() < 2)) {
                        avenger.setStatus(AvengerStatus.AVAILABLE);
                    }
                });
                break;
            case "Assigned":
                mission.setMissionStatus(MissionStatus.ASSIGNED);
                break;
            default:
                System.out.println("Invalid mission status, cannot be updated");
                break;
        }
        dataSource.addMission(mission);

        mission.getAvengers().forEach(
                avenger ->
                        SheildService.sendNotification(avenger, mission)
        );
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