
import java.util.Scanner;
import java.lang.Thread;

public class RailwayManagementSystem {
    public static void main(String[] args) {
        RailwaySystem railwaySystem = new RailwaySystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearScreen();
            System.out.print("\033[0;33m"); // Set text color to yellow
            System.out.println(centerText("=== Railway Management System ==="));
            System.out.print("\033[0m"); // Reset text color
            System.out.print("\033[0;36m"); // Set text color to cyan
            System.out.println(centerText("1. Admin Login"));
            System.out.println(centerText("2. Customer Login"));
            System.out.println(centerText("3. Customer Registration"));
            System.out.println(centerText("4. Exit"));
            System.out.print("\033[0;32m");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character.
            switch (choice) {
                case 1:
                    adminLogin(railwaySystem, scanner);
                    break;
                case 2:
                    customerLogin(railwaySystem, scanner);
                    break;
                case 3:
                    customerRegistration(railwaySystem, scanner);
                    break;
                case 4:
                railwaySystem.saveDataToFile("railway_data.dat");
                System.out.println(centerText("Exiting Railway Management System."));
                    clearScreen();
                    System.out.println("\n\n\n"+centerText("Thanks for Visiting!!\n\tVisit Again..."));
                    try{
                        Thread.sleep(3000);
                    }
                    catch(Exception e){

                    }
                    System.exit(0);
                default:
                    System.out.print("\033[0;31m"); // Set text color to red
                    System.out.println(centerText("Invalid choice. Please try again."));
                    
                    System.out.print("\033[0m"); // Reset text color
                    scanner.nextLine(); // Consume the newline character.
            }
        }
    }
    private static void adminLogin(RailwaySystem railwaySystem, Scanner scanner) {
        System.out.print("\033[0;35m");
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("\033[0;36m");
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();

        if (railwaySystem.isUserValid(username, password)) {
            railwaySystem.setCurrentUser(new User(username, password));
            adminMenu(railwaySystem, scanner);
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void adminMenu(RailwaySystem railwaySystem, Scanner scanner) {
        User currentUser = railwaySystem.getCurrentUser();
        if (currentUser == null) {
            System.out.println("You must log in as an admin first.");
            return;
        }
    
        if (!currentUser.getUsername().equals("admin")) {
            System.out.println("You don't have admin privileges.");
            return;
        }
    
        while (true) {
            
            clearScreen(); // Clear the screen for a clean display.
            System.out.print("\033[0;33m");
            System.out.println(centerText("=== Admin Menu ==="));
            System.out.print("\033[0;36m");
            System.out.println(centerText("1. Display Trains"));
            System.out.println(centerText("2. Add Train"));
            System.out.println(centerText("3. Display Booked Tickets"));
            System.out.println(centerText("4. Save Train Data to File"));
            System.out.println(centerText("5. Log Out"));
            System.out.print("\033[0;32m");
            System.out.print(centerText("Enter your choice: "));
    
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character.
    
            switch (choice) {
                case 1:
                    railwaySystem.displayTrains();
                       try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                
            }
                    break;
                case 2:
                    System.out.print("Enter Train Number: ");
                    int trainNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character.
                    System.out.print("Enter Train Name: ");
                    String trainName = scanner.nextLine();
                    System.out.print("Enter Total Seats: ");
                    int totalSeats = scanner.nextInt();
                    Train newTrain = new Train(trainNumber, trainName, totalSeats);
                    railwaySystem.addTrain(newTrain);
                    System.out.println("Train added successfully.");
                    try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                
            }
                    break;
                case 3:
                    railwaySystem.displayBookedTickets();
                    try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                
            }
                    break;
                case 4:
                    railwaySystem.saveDataToFile("railway_data.dat");
                    try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                
            }
                    break;
                case 5:
                    railwaySystem.setCurrentUser(null);
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println(centerText("Invalid choice. Please try again."));
                    try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                
            }
            }
        }
    }
    

    private static void customerLogin(RailwaySystem railwaySystem, Scanner scanner) {
        System.out.print("Enter Customer Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Customer Password: ");
        String password = scanner.nextLine();

        if (railwaySystem.isUserValid(username, password)) {
            railwaySystem.setCurrentUser(new User(username, password));
            customerMenu(railwaySystem, scanner);
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void customerMenu(RailwaySystem railwaySystem, Scanner scanner) {
        User currentUser = railwaySystem.getCurrentUser();
        if (currentUser == null) {
            System.out.println("You must log in as a customer first.");
            return;
        }
    
        while (true) {
            clearScreen(); // Clear the screen for a clean display.
            System.out.println(centerText("=== Customer Menu ==="));
            System.out.print("\033[0;36m");
            System.out.println(centerText("1. Display Trains"));
            System.out.println(centerText("2. Book Seats"));
            System.out.println(centerText("3. Log Out"));
            System.out.print("\033[0;32m");
            System.out.print("Enter your choice: ");
    
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character.
    
            switch (choice) {
                case 1:
                    railwaySystem.displayTrains();
                     try{
                        Thread.sleep(2000);
                    }
                    catch(Exception e){

                    }
                    break;
                case 2:
                railwaySystem.displayTime();
                    System.out.print("Enter Train Number: ");
                    int trainNumber = scanner.nextInt();
                    Train selectedTrain = railwaySystem.findTrain(trainNumber);
                    if (selectedTrain != null) {
                        
                        System.out.print("Enter Seats to Book: ");
                        int seatsToBook = scanner.nextInt();
                        if (selectedTrain.bookSeats(seatsToBook)) {
                            railwaySystem.bookTicket(selectedTrain, currentUser, seatsToBook);
                            System.out.println("Seats booked successfully.");
                        } else {
                            System.out.println("Not enough available seats.");
                        }
                    } else {
                        System.out.println("Train not found.");
                    }
                    try{
                        Thread.sleep(2000);
                    }
                    catch(Exception e){

                    }
                    break;
                case 3:
                    railwaySystem.setCurrentUser(null);
                    System.out.println("Logged out.");
                     try{
                        Thread.sleep(2000);
                    }
                    catch(Exception e){

                    }
                    return;
                default:
                    System.out.println(centerText("Invalid choice. Please try again."));
                     try{
                        Thread.sleep(2000);
                    }
                    catch(Exception e){

                    }
            }
        }
    }
    

    private static void customerRegistration(RailwaySystem railwaySystem, Scanner scanner) {
        clearScreen(); // Clear the screen for a clean display.
        System.out.println(centerText("=== Customer Registration ==="));
        System.out.print("Enter Customer Username: ");
        String username = scanner.nextLine();
        if (railwaySystem.isUserExists(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }
        System.out.print("Enter Customer Password: ");
        String password = scanner.nextLine();
    
        User newUser = new User(username, password);
        railwaySystem.addUser(newUser);
        railwaySystem.saveDataToFile("user_data.dat");
        System.out.println(centerText("Registration successful. You can now log in as a customer."));
    }
    
    private static String centerText(String text) {
        int screenWidth = 80; // Change this to match your console width
        int textWidth = text.length();
        int padding = (screenWidth - textWidth) / 2;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            result.append(" ");
        }
        result.append(text);
        return result.toString();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
