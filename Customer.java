package hardwarestoreplus;

import java.util.Scanner;

/** class customer is a specialized type of User
 * @author Megan West
 */
public class Customer extends User {

    private String idNumber;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private static final long serialVersionUID = 7666893594034619601L;

    /**constructor sets the random ID number
     * @author Megan West
     */
    public Customer(){
        this.idNumber = this.getRandomID();
    }

    /** toString prints the customer as a string
     * @author Megan West
     * @return String value of the customer
     */


    /** display displays all customer fields
     * @author Megan West
     */
    public void display(){

        String first = this.getFirstName();
        first = first.substring(0, Math.min(first.length(), 8)) + "...";

        String last = this.getLastName();
        last = last.substring(0, Math.min(last.length(), 8)) + "...";

        String address = this.getAddress();
        address = address.substring(0, Math.min(address.length(), 8)) + "...";

        System.out.printf("%-12s | %-12s | %-12s | %-12s | %-12s\n", this.getIdNumber(), first,
                last, this.getPhoneNumber(), address);
    }

    /** getPhoneNumber retrieves user's phone number
     * @author Megan West
     * @return phone Number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**setPhoneNumber sets the phoneNumber field
     * @author Megan West
     * @param phoneNumber is the user's phone number as a string
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**getAddress retrieves the address
     * @author Megan West
     * @return address value
     */
    public String getAddress() {
        return address;
    }

    /**setAddress sets the address field
     * @author Megan West
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public String toString(){
        return "User{" +
                "idNumber=" + idNumber + '\\' +
                ", First name= " + firstName + '\\' +
                ", Last name= " + lastName + '\\' +
                ", phoneNumber= " + phoneNumber + '\\' +
                ", address= " + address + '\\' +
                '}';
    }

    /**update allows the user to update a field value
     * @author Megan West
     */
    public void update(){
        Scanner choose = new Scanner(System.in);
        Scanner field = new Scanner(System.in);
        String choice;

        System.out.println("1: First Name");
        System.out.println("2: Last Name");
        System.out.println("3: Phone Number");
        System.out.println("4: Address");

        System.out.println("Which Customer field would you like to update?");
        choice = choose.next();
        while(!choice.matches("[1-4]")){
            System.out.println("Enter a valid choice.");
            choice = choose.next();
        }

        switch (Integer.parseInt(choice)) {
            case 1:
                System.out.println("Enter new first name:");
                this.setFirstName(field.nextLine());
                break;
            case 2:
                System.out.println("Enter new last name:");
                this.setLastName(field.nextLine());
                break;
            case 3:
                System.out.println("Enter new phone number");
                this.setPhoneNumber(field.nextLine());
                break;
            case 4:
                System.out.println("Enter new address");
                this.setAddress(field.nextLine());
                break;
            default:
                System.out.println("Invalid choice.");
        }

    }

}