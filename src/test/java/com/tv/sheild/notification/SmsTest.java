package com.tv.sheild.notification;

import com.tv.sheild.models.Avenger;
import com.tv.sheild.models.AvengerStatus;
import com.tv.sheild.models.Mission;
import com.tv.sheild.models.MissionStatus;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class SmsTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private NotificationMedium sms = new Sms();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void sendMessageTest() {
        Mission mission = new Mission("mission name", "mission details", MissionStatus.ASSIGNED);
        Avenger avenger = new Avenger("Black Widow", "Fight Skills", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), AvengerStatus.AVAILABLE);
        String message = "test message";
        sms.sendMessage(mission, avenger, message);

        String expectedResult ="test message\n" +
                "SMS notification has been sent for mission : mission name to : Black Widow";
        Assertions.assertEquals(expectedResult.trim(), outputStreamCaptor.toString()
                .trim());
    }

}
