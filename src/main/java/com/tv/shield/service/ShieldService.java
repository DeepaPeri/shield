package com.tv.shield.service;

import com.tv.shield.datasource.InMemoryDataSourceImpl;
import com.tv.shield.models.Avenger;
import com.tv.shield.models.AvengerStatus;
import com.tv.shield.models.Mission;
import com.tv.shield.models.MissionStatus;
import java.util.HashSet;
import java.util.Set;

public class ShieldService {

    private static int maxMissions = 2;

    public static void checkMissions(Set<Mission> missions) {
        if (missions.isEmpty()) {
            System.out.println("No Mission has been assigned to an Avenger");
        } else {
            missions.forEach(mission -> {
                System.out.println(mission.toString());
            });
        }
    }

    public static void checkMissionsDetails(InMemoryDataSourceImpl inMemoryDataSourceImpl, String missionsName) {
        Mission mission = inMemoryDataSourceImpl.getMission(missionsName);
        System.out.println(mission.toString() + ", mission details : " + mission.getMissionDetails());
    }

    public static void checkAvengerDetails(InMemoryDataSourceImpl inMemoryDataSourceImpl, String avengerName) {
        Avenger avenger = inMemoryDataSourceImpl.getAvenger(avengerName);
        System.out.println(avenger.toString() + ", missions assigned : " + avenger.getMissionAssigned().size() + ", missions completed : " + avenger.getMissionsCompleted().size());
    }

    public static void sendNotification(Avenger avenger, Mission mission) {
        avenger.getNotificationMediumMedia().forEach(notification -> notification.sendMessage(mission, avenger, "Notification via " + notification.getClass().getSimpleName() + " has been sent to : " + avenger.getAvengerName()));
    }

    public static void assignMissionToAvengers(String[] avengersList, String missionName, String missionDetails, InMemoryDataSourceImpl inMemoryDataSourceImpl) {
        Set<Avenger> avengerSet = new HashSet<>();
        Set<Mission> missionsSet;
        Mission mission = new Mission(missionName, missionDetails, MissionStatus.ASSIGNED);

        for (int i = 0; i < avengersList.length; i++) {
            Avenger avenger = inMemoryDataSourceImpl.getAvenger(avengersList[i]);

            if (avenger == null) {
                System.out.println("Avenger is not in S.H.I.E.L.D, please assign valied avenger");
                return;
            }

            missionsSet = avenger.getMissionAssigned();
            if (missionsSet.size() == maxMissions) {
                System.out.println("Avenger " + avenger.getAvengerName() + " already has two missions assigned, current mission cannot be assigned");
                return;
            }

            missionsSet.add(mission);
            inMemoryDataSourceImpl.removeAvenger(avenger);
            avenger.setStatus(AvengerStatus.ON_MISSION);

            avenger.setMissionAssigned(missionsSet);
            inMemoryDataSourceImpl.addAvenger(avenger);
            avengerSet.add(avenger);

            ShieldService.sendNotification(avenger, mission);
        }
        mission.setAvengers(avengerSet);
        inMemoryDataSourceImpl.addMission(mission);
    }

    public static void updateMissionStatus(String missionName, InMemoryDataSourceImpl inMemoryDataSourceImpl, String status) {
        Mission mission = inMemoryDataSourceImpl.getMission(missionName);
        if (mission == null) {
            System.out.println("Invalid mission name, the request mission doesn't exist");
            return;
        }
        inMemoryDataSourceImpl.removeMission(mission);
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
        inMemoryDataSourceImpl.addMission(mission);

        mission.getAvengers().forEach(
                avenger ->
                        ShieldService.sendNotification(avenger, mission)
        );
    }

    public static void listAvengers(InMemoryDataSourceImpl inMemoryDataSourceImpl) {
        inMemoryDataSourceImpl.getAvengers().forEach(avenger -> {
            System.out.print(avenger.toString());
            System.out.print(", missions completed : ");
            avenger.getMissionsCompleted().forEach(mission -> System.out.print(mission.getMissionName() + ", "));
            System.out.print(", missions assigned : ");
            avenger.getMissionAssigned().forEach(mission -> System.out.print(mission.getMissionName() + ", "));
            System.out.println();
        });
    }

}