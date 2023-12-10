package onetoone.carpooling;
public class CarpoolRequest {

    private int userId;
    private CarpoolListing carpoolListing;

    public CarpoolRequest() {
    }

    public CarpoolRequest(int userId, CarpoolListing carpoolListing) {
        this.userId = userId;
        this.carpoolListing = carpoolListing;
    }

    // Getters and Setters for userId and carpoolListing

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public CarpoolListing getCarpoolListing() {
        return carpoolListing;
    }

    public void setCarpoolListing(CarpoolListing carpoolListing) {
        this.carpoolListing = carpoolListing;
    }
}
