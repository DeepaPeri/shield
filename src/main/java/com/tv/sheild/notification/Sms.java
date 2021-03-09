package com.tv.sheild.notification;

import com.tv.sheild.models.Avenger;
import com.tv.sheild.models.Mission;

public class Sms implements NotificationMedium {
    @Override
    public void sendMessage(Mission mission, Avenger avenger, String message) {
        // use code to send message via sms
        System.out.println(message);
        System.out.println("SMS notification has been sent for mission : " + mission.getMissionName() + "to : " + avenger.getAvengerName());
    }
}
