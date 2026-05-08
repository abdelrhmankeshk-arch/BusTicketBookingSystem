package model;

public class Passenger extends User {
    private String phone;

    public Passenger(String username, String password, String name, String phone) {
        super(username, password, name);
        this.phone = phone;
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return getName() + " (" + getUsername() + ")";
    }
}