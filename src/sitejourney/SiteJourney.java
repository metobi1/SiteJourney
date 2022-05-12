package sitejourney;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static sitejourney.Print.*;

public class SiteJourney {

    private static final Scanner scanner =
            new Scanner(System.in);
    private static final List<Location>
            locations = new ArrayList<>();
    private static Location bestStartLoc = null;
    private static boolean exit = false;

    static void siteJourneyApp() {

        printWelcomeMessage();

        while(!exit) {
            printInstructions();
            // load locations object in a list
            journeyConf();

            if (exit) {
                continue;
            }

            chooseAlgorithm();
            boolean isSuccessful = isSuccessful();
            displayResult(isSuccessful);
        }
    }

    static void chooseAlgorithm() {
        if (locations.size() < 10) {
            bestStartLoc = bestStartBruteForce();
        } else {
            bestStartLoc = betterAlgo();
        }
    }

    static void displayResult(boolean isSuccessful) {
        if (isSuccessful) {
            printSuccessMessage(bestStartLoc.toString());
        } else {
            printFailureMessage();
        }
    }

    static boolean isSuccessful() {

        if (bestStartLoc == null) {
            return false;
        } else {
            return true;
        }
    }

    static Location betterAlgo() {

        boolean isStart = true;
        int totalFare = 0;
        int fareDiff = 0;
        Location startLocation = null;

        for (int i = 0; i < locations.size(); i++) {

            if (isStart) {
                startLocation = locations.get(i);
                isStart = false;
            }

            totalFare = addFare(totalFare, i, true);
            totalFare = subRate(totalFare, i, true);

            if (i == locations.size() - 1) {
                totalFare = totalFare + fareDiff;
                if (totalFare >= 0) {
                    return startLocation;
                }
            }
            if (totalFare < 0) {
                fareDiff = fareDiff + totalFare;
                totalFare = 0;
                isStart = true;
            }
        }
        return null;
    }

    static int addFare(int totalFare, int index, boolean betterAlgo) {

        if (betterAlgo) {
            return totalFare + locations.get(index).getFare();
        } // make the locations on the list interact in a circular pattern
        return totalFare + locations.get(index % locations.size()).getFare();
    }

    static int subRate(int totalFare, int index, boolean betterAlgo) {
        if (betterAlgo) {
            return totalFare - locations.get(index).getRate();
        } // make the locations on the list interact in a circular pattern
        return totalFare - locations.get(index % locations.size()).getRate();
    }

    static Location bestStartBruteForce() {

        for (int i = 0; i < locations.size(); i++) {
            // Store the most recent starting point
            Location startLocation = locations.get(i);
            int totalFare = 0;
            int totLocCount = 0;

            for (int j = i; j < i + locations.size(); j++) {

                totalFare = addFare(totalFare, j, false);
                totalFare = subRate(totalFare, j, false);
                totLocCount++;
                // if a circular path is completed
                if (totLocCount == locations.size()) {
                    if (totalFare >= 0 ) { // check if the total fare balance
                        return startLocation; // is zero or above
                    } else {
                        break;
                    }
                } // the starting point was wrong, choose another
                if (totalFare < 0) {
                    break;
                }
            }
        }
        return null;
    }

    static void journeyConf() {

        printStartMessage();
        int totNumLoc = getInt("S");

        if (totNumLoc == 0) {
            exit = true;
            return;
        }
         getAllLocations(totNumLoc);
    }

    static void getAllLocations(int totNumLoc) {

        for (int i = 0; i < totNumLoc; i++ ) {
            locations.add(getLocation());
            if (i < totNumLoc - 1) {
                printForSiteJourney();
            }
        }
    }

    static Location getLocation() {

        printForName();
        String name = scanner.next();
        printForFare(name);
        int tFare = getInt(name);
        printForRate();
        int tRate = getInt("");

        return new Location(name, tFare, tRate);
    }

    static int getInt(String name) {
        String strNum = scanner.next();
        while (!isNumeric(strNum)) {
            printErrorMessage();
            printForFare(name);
            strNum = scanner.next();
        }
        return Integer.parseInt(strNum);
    }

    static boolean isNumeric(String strNum) {

        for (int i = 0; i < strNum.length(); i++) {
            if (!(strNum.charAt(i) >= '0' && strNum.charAt(i) <= '9')) {
                return false;
            }
        }
        return true;
    }


}
