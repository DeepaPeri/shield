package com.tv.shield.notification;

import com.tv.shield.models.Avenger;
import com.tv.shield.models.Mission;

public class Email implements NotificationMedium {

    @Override
    public void sendMessage(Mission mission, Avenger avenger, String message) {
        // use code to send message via email
        System.out.println(message + "\nEmail notification has been sent for mission : " + mission.getMissionName() + " to : " + avenger.getAvengerName());
    }
}
