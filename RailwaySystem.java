import java.io.*;
import java.util.ArrayList;

public class RailwaySystem {
    private ArrayList<Train> trains;
    private ArrayList<User> users;
    private ArrayList<Ticket> bookedTickets;
    private User currentUser;

    public RailwaySystem() {
        this.trains = new ArrayList<>();
        this.users = new ArrayList<>();
        this.bookedTickets = new ArrayList<>();
        loadDataFromFile("railway_data.dat");
        loadUserData("user_data.dat");
        addUser(new User("admin", "adminpassword"));
        addUser(new User("customer", "customerpassword"));
    }

    public void addTrain(Train train) {
        trains.add(train);
    }

    public void displayTrains() {
        System.out.println("\n=== Available Trains ===");
        if(trains.size()==0){
            System.out.println("No Train Available!!!");
        }
        else{
            for (Train train : trains) {
                System.out.println("Train Number: " + train.getTrainNumber());
                System.out.println("Train Name: " + train.getTrainName());
                System.out.println("Available Seats: " + train.getAvailableSeats());
                System.out.println("Booked Seats: " + train.getBookedSeats());
                System.out.println("-----------------------------");
            }
        }

    }
    public void displayTime() {
        System.out.println("\n=== Available Trains ===");
        if(trains.size()==0){
            System.out.println("No Train Available!!!\n   Please Try Later...");
        }
        else{
            System.out.println("Train No.    Train Name    Avai. Seats    Booked Seats");
            for (Train train : trains) {
                System.out.println(train.getTrainNumber()+"        "+train.getTrainName()+"        "+train.getAvailableSeats()+"        "+train.getBookedSeats());
            }
            System.out.println();
        }

    }

    public Train findTrain(int trainNumber) {
        for (Train train : trains) {
            if (train.getTrainNumber() == trainNumber) {
                return train;
            }
        }
        return null;
    }

    public void saveDataToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(trains);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDataFromFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                trains = (ArrayList<Train>) ois.readObject();
                System.out.println("Data loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Data file not found. A new file will be created.");
        }
    }

    public void addUser(User user) {
        users.add(user);
    }

    private void loadUserData(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            users = (ArrayList<User>) ois.readObject();
            System.out.println("User data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("User data file not found. No users loaded.");
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public boolean isUserValid(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void displayBookedTickets() {
        System.out.println("\n=== Booked Tickets ===");
        if(bookedTickets.size()==0){
            System.out.println("No Booking");
        }
        else{
            System.out.println("No. of Booked Tickets: "+bookedTickets.size()+"-----------------------------");
            for (Ticket ticket : bookedTickets) {
                System.out.println("Train Number: " + ticket.getTrain().getTrainNumber());
                System.out.println("Train Name: " + ticket.getTrain().getTrainName());
                System.out.println("Booked Seats: " + ticket.getBookedSeats());
                System.out.println("Customer: " + ticket.getUser().getUsername());
                System.out.println("-----------------------------");
            }
        }

    }

    public void bookTicket(Train train, User user, int seatsToBook) {
        if (train.bookSeats(seatsToBook)) {
            bookedTickets.add(new Ticket(train, user, seatsToBook));
        }
    }
}