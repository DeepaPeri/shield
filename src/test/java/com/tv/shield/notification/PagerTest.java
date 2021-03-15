package com.tv.shield.notification;

import com.tv.shield.models.Avenger;
import com.tv.shield.models.AvengerStatus;
import com.tv.shield.models.Mission;
import com.tv.shield.models.MissionStatus;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class PagerTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private NotificationMedium pager = new Pager();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void sendMessageTest() {
        Mission mission = new Mission("mission name", "mission details", MissionStatus.ASSIGNED);
        Avenger avenger = new Avenger("Black Widow", "Fight Skills", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), AvengerStatus.AVAILABLE);
        String message = "test message";
        pager.sendMessage(mission, avenger, message);

        String expectedResult ="test message\n" +
                "Pager notification has been sent for mission : mission name to : Black Widow";
        Assertions.assertEquals(expectedResult.trim(), outputStreamCaptor.toString()
                .trim());
    }


}
