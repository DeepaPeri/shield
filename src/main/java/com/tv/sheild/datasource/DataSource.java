package com.tv.sheild.datasource;

import com.tv.sheild.acl.Acl;
import com.tv.sheild.acl.Roles;
import com.tv.sheild.models.Avenger;
import com.tv.sheild.models.AvengerStatus;
import com.tv.sheild.models.Captain;
import com.tv.sheild.models.Mission;
import com.tv.sheild.notification.Email;
import com.tv.sheild.notification.NotificationMedium;
import com.tv.sheild.notification.NotificationTypes;
import com.tv.sheild.notification.Pager;
import com.tv.sheild.notification.Sms;
import java.util.HashSet;
import java.util.Set;

public class DataSource {

    private Set<Avenger> avengers;

    private Set<Captain> captains;

    private Set<Mission> missions;

    public DataSource() {
        createCaptains();
        createAvengers();
        missions = new HashSet<>();
    }

    private Set<NotificationMedium> createNotificationMeidum(Set<NotificationTypes> mediums) {

        NotificationMedium notificationMediumEmail = null;
        NotificationMedium notificationMediumSms = null;
        NotificationMedium notificationMediumPager = null;

        if (mediums.contains(NotificationTypes.EMAIL)) {
            notificationMediumEmail = new Email();
        }
        if (mediums.contains(NotificationTypes.SMS)) {
            notificationMediumSms = new Sms();
        }
        if (mediums.contains(NotificationTypes.PAGER)) {
            notificationMediumPager = new Pager();
        }

        Set<NotificationMedium> notificationMedia = new HashSet<>();
        if (notificationMediumEmail != null) {
            notificationMedia.add(notificationMediumEmail);
        }
        if (notificationMediumSms != null) {
            notificationMedia.add(notificationMediumSms);
        }
        if (notificationMediumPager != null) {
            notificationMedia.add(notificationMediumPager);
        }

        return notificationMedia;
    }

    private Set<Acl> createAcl(Roles role) {
        Set<Acl> acls = new HashSet<>();

        if (Roles.AVENGER.equals(role)) {
            acls.add(Acl.READ);
            acls.add(Acl.READOTHERS);
            return acls;
        } else {
            acls.add(Acl.READ);
            acls.add(Acl.READOTHERS);
            acls.add(Acl.UPDATE);
            acls.add(Acl.ASSIGN);
            return acls;
        }
    }

    public Set<Avenger> createAvengers() {

        avengers = new HashSet<>();

        // Iron man
        Set<NotificationTypes> notificationMedia = new HashSet<>();
        notificationMedia.add(NotificationTypes.EMAIL);

        Avenger avenger = new Avenger("Iron Man", "Technology", new HashSet<>(), new HashSet<>(), createNotificationMeidum(notificationMedia), createAcl(Roles.AVENGER), AvengerStatus.AVAILABLE);

        avengers.add(avenger);

        // Captain America
        notificationMedia = new HashSet<>();
        notificationMedia.add(NotificationTypes.EMAIL);
        notificationMedia.add(NotificationTypes.SMS);

        avenger = new Avenger("Captain America", "Strength", new HashSet<>(), new HashSet<>(), createNotificationMeidum(notificationMedia), createAcl(Roles.AVENGER), AvengerStatus.AVAILABLE);

        avengers.add(avenger);

        // Hulk
        notificationMedia = new HashSet<>();
        notificationMedia.add(NotificationTypes.EMAIL);
        notificationMedia.add(NotificationTypes.SMS);
        notificationMedia.add(NotificationTypes.PAGER);

        avenger = new Avenger("Hulk", "Bulky", new HashSet<>(), new HashSet<>(), createNotificationMeidum(notificationMedia), createAcl(Roles.AVENGER), AvengerStatus.AVAILABLE);

        avengers.add(avenger);

        // Thor
        notificationMedia = new HashSet<>();
        notificationMedia.add(NotificationTypes.EMAIL);
        notificationMedia.add(NotificationTypes.SMS);
        notificationMedia.add(NotificationTypes.PAGER);

        avenger = new Avenger("Thor", "Hammer", new HashSet<>(), new HashSet<>(), createNotificationMeidum(notificationMedia), createAcl(Roles.AVENGER), AvengerStatus.AVAILABLE);

        avengers.add(avenger);

        // Black Widow
        notificationMedia = new HashSet<>();
        notificationMedia.add(NotificationTypes.EMAIL);
        notificationMedia.add(NotificationTypes.SMS);
        notificationMedia.add(NotificationTypes.PAGER);

        avenger = new Avenger("Black Widow", "Fight Skills", new HashSet<>(), new HashSet<>(), createNotificationMeidum(notificationMedia), createAcl(Roles.AVENGER), AvengerStatus.AVAILABLE);

        avengers.add(avenger);

        // Hawkeye
        notificationMedia = new HashSet<>();
        notificationMedia.add(NotificationTypes.EMAIL);
        notificationMedia.add(NotificationTypes.SMS);
        notificationMedia.add(NotificationTypes.PAGER);

        avenger = new Avenger("Hawkeye", "Archery", new HashSet<>(), new HashSet<>(), createNotificationMeidum(notificationMedia), createAcl(Roles.AVENGER), AvengerStatus.AVAILABLE);

        avengers.add(avenger);

        return avengers;
    }

    public Set<Captain> createCaptains() {
        captains = new HashSet<>();
        Captain captain = new Captain("Fury", createAcl(Roles.CAPTAIN));
        captains.add(captain);
        return captains;
    }


    public void addCaptain(Captain captain) {
        this.captains.add(captain);
    }

    public void removeCaptain(Captain captain) {
        this.captains.remove(captain);
    }

    public void addAvenger(Avenger avenger) {
        this.avengers.add(avenger);
    }

    public void removeAvenger(Avenger avenger) {
        this.avengers.remove(avenger);
    }


    public Set<Avenger> getAvengers() {
        return avengers;
    }

    public void setAvengers(Set<Avenger> avengers) {
        this.avengers = avengers;
    }

    public Set<Captain> getCaptains() {
        return captains;
    }

    public void setCaptains(Set<Captain> captains) {
        this.captains = captains;
    }

    public void addMission(Mission mission) {
        missions.add(mission);
    }

    public void removeMission(Mission mission) {
        missions.remove(mission);
    }

    public Set<Mission> getMissions() {
        return missions;
    }

    public void setMissions(Set<Mission> missions) {
        this.missions = missions;
    }

    public Mission getMission(String missionName) {
        for (Mission mission : missions) {
            if (mission.getMissionName().equals(missionName)) {
                return mission;
            }
        }
        return null;
    }

    public Avenger getAvenger(String avengerName) {
        for (Avenger avenger : avengers) {
            if (avenger.getAvengerName().equals(avengerName)) {
                return avenger;
            }
        }
        return null;
    }
}
