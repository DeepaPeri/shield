package com.tv.shield.notification;

import com.tv.shield.models.Avenger;
import com.tv.shield.models.Mission;

public interface NotificationMedium {

    void sendMessage(Mission mission, Avenger avenger, String message);

}
