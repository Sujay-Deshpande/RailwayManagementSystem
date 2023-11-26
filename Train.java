import java.io.*;
public class Train implements Serializable {
    private int trainNumber;
    private String trainName;
    private int totalSeats;
    private int availableSeats;
    private int bookedSeats;

    public Train(int trainNumber, String trainName, int totalSeats) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.bookedSeats = 0;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public boolean bookSeats(int seatsToBook) {
        if (seatsToBook <= availableSeats) {
            availableSeats -= seatsToBook;
            bookedSeats += seatsToBook;
            return true;
        } else {
            return false;
        }
    }
}