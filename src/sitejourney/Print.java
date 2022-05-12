package sitejourney;

public class Print {

    static void printWelcomeMessage() {
        System.out.println("Welcome to the Site Journey App\n");
        System.out.println("Use the app to determine the circular " +
                "path of your journey based on the available parameters");
    }

    static void printInstructions() {
        System.out.println("Enter \"0\" to exit the app otherwise: ");
        System.out.println("Enter the total number of locations " +
                "under review in integer format");
        System.out.println("After that follow the instructions on the " +
                "screen to fill in the details of each location\n");

    }
    static void printForName() {
        System.out.print("\nEnter location name(one word name): ");
    }

    static void printStartMessage() {
        System.out.print("Enter Total number of Locations: ");
    }

    static void printForFare(String name) {
        if ("".equals(name)) {
            printForRate();
        } else if ("S".equals(name)) {
            printStartMessage();
        } else {
            System.out.printf("Enter fare given at %s: ", name);
        }
    }

    static void printForRate(){
        System.out.print("Enter rate to the next location: ");
    }

    static void printErrorMessage() {
        System.out.println("Enter only Integers please\n");
    }

    static void printForSiteJourney() {
        System.out.println("\nNext Site-Journey Location");
    }

    static void printSuccessMessage(String location) {
        System.out.printf("%nIt is possible. The starting point should be %s%n%n", location);
    }

    static void printFailureMessage() {
        System.out.println("\nIt is not possible\n");
    }
}
