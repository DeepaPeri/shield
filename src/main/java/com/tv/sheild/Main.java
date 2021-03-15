package com.tv.sheild;

import com.tv.sheild.datasource.DbInitializer;
import com.tv.sheild.datasource.InMemoryDataSource;
import com.tv.sheild.datasource.InMemoryDataSourceImpl;
import com.tv.sheild.service.SheildService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        InMemoryDataSource inMemoryDataSource = DbInitializer.initializeInMemoryDatabase();
        /*
         * Introduction
         * */
        System.out.println("============------------S.H.I.E.L.D------------============");
        System.out.println("Welcome to Captain Fury.");
        System.out.println("1. Check the missions\n" +
                "2. Assign mission to Avengers\n" +
                "3. Check mission’s details\n" +
                "4. Check Avenger’s details\n" +
                "5. Update Mission’s status\n" +
                "6. List Avengers");

        while (true) {
            /*
             * Read choice
             * */
            java.io.BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the option: ");
            int choice = Integer.parseInt(in.readLine());

            switch (choice) {
                case 1:
                    /*
                     * Check the missions
                     * */
                    SheildService.checkMissions(inMemoryDataSource.getMissions());
                    break;
                case 2:
                    /*
                     *  Assign mission to Avengers
                     * */
                    System.out.println("Enter Avengers : ");
                    String[] avengersList = in.readLine().split(",");
                    System.out.println("Enter Mission Name : ");
                    String missionName = in.readLine();
                    System.out.println("Enter Mission Details : ");
                    String missionDetails = in.readLine();
                    SheildService.assignMissionToAvengers(avengersList, missionName, missionDetails, (InMemoryDataSourceImpl) inMemoryDataSource);
                    break;

                case 3:
                    System.out.println("Enter Mission Name : ");
                    missionName = in.readLine();
                    SheildService.checkMissionsDetails((InMemoryDataSourceImpl) inMemoryDataSource, missionName);
                    break;
                case 4:
                    System.out.println("Enter Avenger Name : ");
                    String avengerName = in.readLine();
                    SheildService.checkAvengerDetails((InMemoryDataSourceImpl) inMemoryDataSource, avengerName);
                    break;
                case 5:
                    System.out.println("Enter Mission Name : ");
                    missionName = in.readLine();
                    System.out.println("Enter new status : ");
                    String status = in.readLine();

                    SheildService.updateMissionStatus(missionName, (InMemoryDataSourceImpl) inMemoryDataSource, status);
                    break;
                case 6:
                    SheildService.listAvengers((InMemoryDataSourceImpl) inMemoryDataSource);
                    break;
                default:
                    System.out.println("Please choose valid option 1 to 6");
                    break;
            }
        }
    }
}
