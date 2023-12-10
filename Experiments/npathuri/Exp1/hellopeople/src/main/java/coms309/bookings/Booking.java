package coms309.bookings;

/**
 * Provides the Definition/Structure for a booking entry
 *
 * @author Niharika Pathuri
 */

public class Booking {

    // Unique identifier for each booking
    private String bookingId;

    // Unique identifier of the user making the booking
    private String userId;

    // Unique identifier of the office space being booked
    private String spaceId;

    // Start date of the booking
    private String startDate;

    // End date of the booking
    private String endDate;

    // Total cost of the booking
    private double totalPrice; // Assuming you'd use a numeric type for the price

    public Booking() {

    }

    // Constructor to initialize a booking with provided values
    public Booking(String bookingId, String userId, String spaceId, String startDate, String endDate, double totalPrice) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.spaceId = spaceId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    // Getter for bookingId
    public String getBookingId() {
        return this.bookingId;
    }

    // Setter for bookingId
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    // Getter for userId
    public String getUserId() {
        return this.userId;
    }

    // Setter for userId
    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getter for spaceId
    public String getSpaceId() {
        return this.spaceId;
    }

    // Setter for spaceId
    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    // Getter for startDate
    public String getStartDate() {
        return this.startDate;
    }

    // Setter for startDate
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    // Getter for endDate
    public String getEndDate() {
        return this.endDate;
    }

    // Setter for endDate
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    // Getter for totalPrice
    public double getTotalPrice() {
        return this.totalPrice;
    }

    // Setter for totalPrice
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // String representation of the Booking object
    @Override
    public String toString() {
        return "Booking ID: " + bookingId + "\n"
                + "User ID: " + userId + "\n"
                + "Space ID: " + spaceId + "\n"
                + "Start Date: " + startDate + "\n"
                + "End Date: " + endDate + "\n"
                + "Total Price: " + totalPrice + "\n";
    }
}
