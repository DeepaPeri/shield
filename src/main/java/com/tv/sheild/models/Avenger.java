package com.tv.sheild.models;

import com.tv.sheild.acl.Acl;
import com.tv.sheild.notification.NotificationMedium;
import java.util.Set;

public class Avenger {

    private String avengerName;

    private String abilities;

    private AvengerStatus status;

    private Set<Mission> missionAssigned;

    private Set<Mission> missionsCompleted;

    private Set<NotificationMedium> notificationMediumMedia;

    private Set<Acl> acl; // can make it per user or per group

    public Avenger(String avengerName, String abilities, Set<Mission> missionAssigned, Set<Mission> missionsCompleted, Set<NotificationMedium> notificationMediumMedia, Set<Acl> acl, AvengerStatus avengerStatus) {
        this.avengerName = avengerName;
        this.abilities = abilities;
        this.missionAssigned = missionAssigned;
        this.missionsCompleted = missionsCompleted;
        this.notificationMediumMedia = notificationMediumMedia;
        this.acl = acl;
        this.status = avengerStatus;
    }

    public String getAvengerName() {
        return avengerName;
    }

    public void setAvengerName(String avengerName) {
        this.avengerName = avengerName;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public Set<Mission> getMissionAssigned() {
        return missionAssigned;
    }

    public void setMissionAssigned(Set<Mission> missionAssigned) {
        this.missionAssigned = missionAssigned;
    }

    public Set<Mission> getMissionsCompleted() {
        return missionsCompleted;
    }

    public void addMissionsCompleted(Mission mission){
        this.missionsCompleted.add(mission);
    }
    public void removeMissionsAssigned(Mission mission){
        this.missionsCompleted.remove(mission);
    }
    public void setMissionsCompleted(Set<Mission> missionsCompleted) {
        this.missionsCompleted = missionsCompleted;
    }

    public Set<NotificationMedium> getNotificationMediumMedia() {
        return notificationMediumMedia;
    }

    public void setNotificationMediumMedia(Set<NotificationMedium> notificationMediumMedia) {
        this.notificationMediumMedia = notificationMediumMedia;
    }

    public Set<Acl> getAcl() {
        return acl;
    }

    public void setAcl(Set<Acl> acl) {
        this.acl = acl;
    }

    public AvengerStatus getStatus() {
        return status;
    }

    public void setStatus(AvengerStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Avenger name : " + avengerName + ", ability : " + abilities + ", Status : " + status ;
    }
}
