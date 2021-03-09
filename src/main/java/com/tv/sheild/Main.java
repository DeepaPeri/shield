package com.tv.sheild;

import com.tv.sheild.datasource.DataSource;
import com.tv.sheild.models.Avenger;
import com.tv.sheild.models.Captain;
import com.tv.sheild.service.SheildService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        DataSource dataSource = new DataSource();

        // get captains and avengers
        Set<Captain> captains = dataSource.getCaptains();
        Set<Avenger> avengers = dataSource.getAvengers();

        System.out.println("============------------S.H.I.E.L.D------------============");
        System.out.println("Welcome to Captain Fury.");
        System.out.println("1. Check the missions\n" +
                "2. Assign mission to Avengers\n" +
                "3. Check mission’s details\n" +
                "4. Check Avenger’s details\n" +
                "5. Update Mission’s status\n" +
                "6. List Avengers");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the option: ");
            int choice = sc.nextInt();
            java.io.BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            switch (choice) {
                case 1:
                    SheildService.checkMissions(dataSource.getMissions());
                    break;
                case 2:
                    System.out.println("Enter Avengers : ");
                    String[] avengersList = in.readLine().split(",");
                    System.out.println("Enter Mission Name : ");
                    String missionName = in.readLine();
                    System.out.println("Enter Mission Details : ");
                    String missionDetails = in.readLine();
                    SheildService.assignMissionToAvengers(avengersList, missionName, missionDetails, dataSource);
                    break;

                case 3:
                    System.out.println("Enter Mission Name : ");
                    missionName = in.readLine();
                    System.out.println(dataSource.getMission(missionName).toString());
                    break;
                case 4:
                    System.out.println("Enter Avenger Name : ");
                    String avengerName = in.readLine();
                    System.out.println(dataSource.getAvenger(avengerName).toString());
                    break;
                case 5:
                    System.out.println("Enter Mission Name : ");
                    missionName = in.readLine();
                    System.out.println("Enter new status : ");
                    String status = in.readLine();

                    SheildService.updateMissionStatus(missionName, dataSource, status);
                    break;
                case 6:
                    SheildService.listAvengers(dataSource);
                    break;
                default:
                    System.out.println("Please choose valid option 1 to 6");
            }
        }
    }
}
