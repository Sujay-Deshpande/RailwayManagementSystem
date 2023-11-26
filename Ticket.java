import java.io.*;
public class Ticket implements Serializable {
    private Train train;
    private User user;
    private int bookedSeats;

    public Ticket(Train train, User user, int bookedSeats) {
        this.train = train;
        this.user = user;
        this.bookedSeats = bookedSeats;
    }

    public Train getTrain() {
        return train;
    }

    public User getUser() {
        return user;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }
}