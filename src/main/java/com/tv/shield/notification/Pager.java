package com.tv.shield.notification;

import com.tv.shield.models.Avenger;
import com.tv.shield.models.Mission;

public class Pager implements NotificationMedium {
    @Override
    public void sendMessage(Mission mission, Avenger avenger, String message) {
        // use code to send message via pager
        System.out.println(message + "\nPager notification has been sent for mission : " + mission.getMissionName() + " to : " + avenger.getAvengerName());

    }
}
