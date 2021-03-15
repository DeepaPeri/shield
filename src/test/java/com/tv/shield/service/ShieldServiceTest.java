package com.tv.shield.service;

import com.tv.shield.models.Mission;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class ShieldServiceTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void checkMissionsForEmptyTest(){
        Set<Mission> missions = new HashSet<>();
        ShieldService.checkMissions(missions);
        String expectedResult ="No Mission has been assigned to an Avenger";
        Assertions.assertEquals(expectedResult.trim(), outputStreamCaptor.toString()
                .trim());
    }
}
