// Lisa Ho Yen Xin 20297507
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

// store a pool of locations
class LocationDatabase {
    int id;
    String name;
    double area;
    int max_capacity;
    double average_time;

    /* for printing outputs */
    public void locationName() {
        System.out.println("L" + id + ": " + name);
    }

    public void locationInfo() {
        System.out.println("Area: " + area + " m\u00B2" + "\nAverage time per person: " + average_time + " minutes" + "\nMax capacity: " + max_capacity + " visitors");
    }

    // location 1
    public static void location1(ProactiveSD obj){
        obj.id = 1;
        obj.name = "Lotus's Semenyih";
        obj.average_time = 55;
        obj.locationName();
    }

    // location 2
    public static void location2(ProactiveSD obj) {
        obj.id = 2;
        obj.name = "Secret Recipe Semenyih";
        obj.average_time = 45;
        obj.locationName();
    }
    
    // location 3
    public static void location3(ProactiveSD obj) {
        obj.id = 3;
        obj.name = "KFC Semenyih";
        obj.average_time = 30;
        obj.locationName();
    }
    
    // location 4
    public static void location4(ProactiveSD obj) {
        obj.id = 4;
        obj.name = "McDonald Semenyih";
        obj.average_time = 35;
        obj.locationName();
    }
    
    // location 5
    public static void location5(ProactiveSD obj) {
        obj.id = 5;
        obj.name = "Tealive Semenyih";
        obj.average_time = 15;
        obj.locationName();
    }
    
    // calculate max capacity, adhering to social distancing
    public static int maxcapacity(double area) {
        int capacity = (int) area / 16; // safe social distance is 2 meters, length = 4 meters (front + behind), width = 4 meters (left + right), total = 4 * 4 = 16 sq meters
        return capacity;
    }

    // generate random number of visitors
    public static int visitors(int capacity) {
        int current_visitors = (int) Math.floor(Math.random() * (capacity - 0 + 1) + 0);
        System.out.println("Current number of visitors: " + current_visitors + " visitors");
        return current_visitors;
    }
}

public class ProactiveSD extends SocialBubble {
    public static void main(String[] args) {
        ProactiveSD obj = new ProactiveSD();

        // display menu of locations set in database
        System.out.println("Locations available:");
        location1(obj);
        location2(obj);
        location3(obj);
        location4(obj);
        location5(obj);

        System.out.print("\nPlease enter the ID of the location/building you would like to enter into: ");

        /* get user input */
        try (Scanner myId = new Scanner(System.in)) {
            int LocationId;

            // if user input is an interger
            if (myId.hasNextInt()) {
                LocationId = myId.nextInt();

                // if location ID not found in database
                if (LocationId >= 6) {
                    Scanner myIn = new Scanner(System.in);

                    System.out.println("\nLocation not available in database.");
                    System.out.println("Enter the location info here.");
                    obj.id = LocationId; // set the ID entered by the user earlier as location ID

                    System.out.print("Location name: ");
                    String Lname = myIn.nextLine();
                    obj.name = Lname;

                    calculate(obj, myIn); // calculate max capacity

                    System.out.print("Average time spent per person: ");
                    double time = myIn.nextDouble();
                    obj.average_time = time;

                    /* display complete location details */
                    System.out.println("");
                    obj.locationName();
                    obj.locationInfo();

                    confirmation(obj); // if max capacity is reached, get confirmation from user to wait or leave
                    guidelines(obj, myIn); // after entering, check if user is adhering to social distancing guidelines

                } else {
                    Scanner myIn = new Scanner(System.in);

                    switch (LocationId) {
                        case 1:
                            calculate(obj, myIn); // calculate max capacity

                            /* display complete location details */
                            location1(obj); 
                            obj.locationInfo();

                            confirmation(obj); // if max capacity is reached, get confirmation from user to wait or leave
                            guidelines(obj, myIn); // after entering, check if user is adhering to social distancing guidelines
                            break;

                        case 2:
                            calculate(obj, myIn); 

                            location2(obj);
                            obj.locationInfo();

                            confirmation(obj);
                            guidelines(obj, myIn);
                            break;

                        case 3:
                            calculate(obj, myIn);

                            location3(obj);
                            obj.locationInfo();

                            confirmation(obj);
                            guidelines(obj, myIn);
                            break;

                        case 4:
                            calculate(obj, myIn); 

                            location4(obj);
                            obj.locationInfo();

                            confirmation(obj);
                            guidelines(obj, myIn);
                            break;

                        case 5: 
                            calculate(obj, myIn);

                            location5(obj);
                            obj.locationInfo();

                            confirmation(obj);
                            guidelines(obj, myIn);
                            break;
                    }
                    myIn.close();
                }
            } else { // if user input is invalid
                System.out.println("Please enter a positive integer as location ID.\n");
                main(null); // return to main function to display main menu
            }
        }
    }

    // get user input for area & calculate max capacity
    public static void calculate(ProactiveSD obj, Scanner myIn) {
        System.out.print("\nPlease enter the available space out of total area in meters.");
        System.out.print("\nLength: ");
        double a1 = myIn.nextDouble();
        System.out.print("Width: ");
        double a2 = myIn.nextDouble();
        System.out.println("");

        obj.area = a1 * a2;

        int capacity = (int) maxcapacity(obj.area);
        if (capacity == 0) {
            System.out.println("Area of location is too small to practice social distancing.\nPlease choose a different location.\n");
            main(null); // return to main function to display main menu
        }
        obj.max_capacity = capacity;
    }

    // if max capacity is reached, get confirmation from user to wait or leave
    public static void confirmation(ProactiveSD obj) {
        int current_visitors = visitors(obj.max_capacity); // get number of current visitors

        if (current_visitors >= obj.max_capacity) { // if capacity is full, deny entry & get confirmation from user to wait or leave
            Scanner confirmIn = new Scanner(System.in);

            System.out.println("\nEntry denied. Max capacity reached.");
            System.out.println("Average time spent per person is " + obj.average_time + " minutes.");
            System.out.println("Would you like to wait? (yes/no)");

            String confirm = confirmIn.nextLine(); // get user input (confirmation)

            // if invalid input, prompt for new input
            while (!"yes".equalsIgnoreCase(confirm) && !"no".equalsIgnoreCase(confirm)) {
                System.out.println("Please enter 'yes' or 'no'.");
                confirm = confirmIn.nextLine();
            }

            if (confirm.equalsIgnoreCase("yes")) { // allow entry once waiting time is over
                System.out.println("\nWaiting time is over. You may enter.");
                return;
            } else if (confirm.equalsIgnoreCase("no")) { // let user choose a different location
                System.out.println("\nPlease choose a different location.\n");
                main(null); // return to main function to display main menu
            }
            confirmIn.close();
        } else { // if capacity not full, allow entry
            System.out.println("\nWelcome!");
        }
    }

    // after entering, check if user is adhering to social distancing guidelines
    public static void guidelines(ProactiveSD obj, Scanner myIn) {
        System.out.println("\nTo ensure proactive social distancing is practiced, please input your distance in meters from other people in all four directions.");
        System.out.print("North: ");
        double north = myIn.nextDouble();
        System.out.print("South: ");
        double south = myIn.nextDouble();
        System.out.print("East: ");
        double east = myIn.nextDouble();
        System.out.print("West: ");
        double west = myIn.nextDouble();

        if (north >= 2 && south >= 2 && east >= 2 && west >= 2) { // if user is practicing social distancing, display appreciation message
            System.out.println("Thank you for adhering to the social distancing guildelines. Enjoy your time.");
        } else {
            distancing(obj, north, south, east, west); // determine how much the user should move away to practice social distancing and alert them if they are in a close contact of a close contact 
        }
    }
}

// ensure dynamic social distancing within a confined space
class SocialBubble extends LocationDatabase {
    // determine the distance & direction the user should move to practice social distancing
    public static void distancing(ProactiveSD obj, double north, double south, double east, double west) {
        double distance = 0;

        if (north < 2) {
            distance = 2 - north;
            if ((south - distance) < 2) { // if place is too crowded for social distancing
                System.out.println("Unable to practice social distancing. Please move to an uncrowded area.");
                close_contact(obj); // check if user is casual contact
                return;
            } else {
                System.out.println("Please move " + distance + " m to the back.");
            }
        }
        if (south < 2) {
            distance = 2 - south;
            if ((north - distance) < 2) {
                System.out.println("Unable to practice social distancing. Please move to an uncrowded area.");
                close_contact(obj);
                return;
            } else {
                System.out.println("Please move " + distance + " m to the front.");
            }
        }
        if (east < 2) {
            distance = 2 - east;
            if ((west - distance) < 2) {
                System.out.println("Unable to practice social distancing. Please move to an uncrowded area.");
                close_contact(obj);
                return;
            } else {
                System.out.println("Please move " + distance + " m to the left.");
            }
        }
        if (west < 2) {
            distance = 2 - west;
            if ((east - distance) < 2) {
                System.out.println("Unable to practice social distancing. Please move to an uncrowded area.");
                close_contact(obj);
                return;
            } else {
                System.out.println("Please move " + distance + " m to the right.");
            }
        }
    }

    // check if user is a casual contact
    public static void close_contact(ProactiveSD obj) {
        int boolean_variable = (int) Math.floor(Math.random() * (1 - 0 + 1) + 0); // generate Boolean variable to determine close contact 

        // current date & time
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        if(boolean_variable==1) { // if user is in close contact with a close contact
            System.out.println("\nYou are in a close contact of a close contact.");
            System.out.println("20297507 Lisa Ho Yen Xin");
            System.out.println("L" + obj.id + ": " + obj.name);
            System.out.println("" + formatter.format(date));
            System.out.println("Status: Casual Contact");
        }else {
            System.out.println("Please be mindful of the social distancing guidelines. Always maintain a minimum distance of 2 meters with others. \nYour health is our priority. Thank you.");
        }
    }
}