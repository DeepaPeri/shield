package com.tv.sheild.notification;

import com.tv.sheild.models.Avenger;
import com.tv.sheild.models.Mission;

public interface NotificationMedium {

    void sendMessage(Mission mission, Avenger avenger, String message);

}
