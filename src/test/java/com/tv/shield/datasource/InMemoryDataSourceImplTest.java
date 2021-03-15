package com.tv.shield.datasource;

import com.tv.shield.models.Avenger;
import com.tv.shield.models.AvengerStatus;
import com.tv.shield.models.Mission;
import com.tv.shield.models.MissionStatus;
import java.util.HashSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class InMemoryDataSourceImplTest {
    private InMemoryDataSource inMemoryDataSource = new InMemoryDataSourceImpl();

    @Test
    public void getAvengerForNullTest() {
        inMemoryDataSource.createAvengers();
        Assertions.assertNull(inMemoryDataSource.getAvenger("avengerName"));
    }

    @Test
    public void getAvengerTest() {
        Avenger avenger = new Avenger("Iron Man temp", "Technology", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), AvengerStatus.AVAILABLE);
        inMemoryDataSource.createAvengers();
        inMemoryDataSource.addAvenger(avenger);
        Assertions.assertEquals(avenger, inMemoryDataSource.getAvenger("Iron Man temp"));
    }

    @Test
    public void getCaptainForNullTest(){
        inMemoryDataSource.createCaptains();
        Assertions.assertNull(inMemoryDataSource.getCaptain("captainName"));
    }

    @Test
    public void getMissionTest(){
        inMemoryDataSource.setMissions(new HashSet<>());
        Mission mission = new Mission("missionName", "missionDetails", MissionStatus.ASSIGNED);
        inMemoryDataSource.addMission(mission);
        Assertions.assertEquals(mission, inMemoryDataSource.getMission("missionName"));
    }
}
