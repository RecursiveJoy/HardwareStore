package hardwarestoreplus;

import java.io.Serializable;
import java.util.Scanner;

/**class User is the base class for customer and employee to be used in a database
 * @author Megan West
 */
public class User implements Serializable, Comparable<User>{
    String idNumber;
    String firstName;
    String lastName;
    private static final long serialVersionUID = -2250779643763138424L;


    /**constructor initializes id field with random ID
     * @author Megan West
     */
    public User(){
        this.idNumber = this.getRandomID();
    }


    /**getRandomID generates a random 5 digit user ID
     * @author Megan West
     * @return random ID
     */
    public String getRandomID(){

        String availableChars = "ABCDEF1234567890";
        String randomID = "";
        int randomIndex = 0;

        for (int x = 0; x < 5; x = x + 1){
            randomIndex = (int) (Math.random() * 16);
            randomID = randomID + availableChars.charAt(randomIndex);
        }

        return randomID;

    }


    /** compareTo allows the users to be sorted
     * @author Megan West
     * @param nextUser next user to compare current user to
     * @return idNumber of the larger value
     */
    public int compareTo(User nextUser){
        return idNumber.compareTo(nextUser.getIdNumber());
    }


    /**toString returns a string version of the user
     * @author Megan West
     * @return string version of user
     */
    public String toString(){
        return "User{" +
                "idNumber='" + idNumber + '\\' +
                ", First name='" + firstName + '\\' +
                ", Last name=" + lastName +
                '}';
    }


    /**display displays a user
     * @author Megan West
     */
    public void display(){

        String id = this.getIdNumber();
        id = id.substring(0, Math.min(id.length(), 11));

        String firstName = this.getFirstName();
        firstName = firstName.substring(0, Math.min(firstName.length(), 11));

        String lastName = this.getLastName();
        lastName = lastName.substring(0, Math.min(lastName.length(), 11));

        System.out.printf("%-12s | %-20s | %-20s\n", id, firstName, lastName);
    }


    /**getIdNumber returns value of ID number
     * @author Megan West
     * @return idNumber
     */
    public String getIdNumber() {
        return idNumber;
    }


    /**getFirstName returns value of firstName
     * @author Megan West
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }


    /**getLastName returns value of lastName
     * @author Megan West
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }


    /** setFirstName sets value of firstName
     * @author Megan West
     * @param firstName first name of user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**setLastName sets value of lastName
     * @author Megan West
     * @param lastName last name of user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /**update lets user update object fields
     * @author Megan West
     */
    public void update(){
        Scanner choose = new Scanner(System.in);
        int choice;

        System.out.println("1: First Name");
        System.out.println("2: Last Name");

        System.out.println("Which user field would you like to update?");
        choice = choose.nextInt();


        switch (choice) {
            case 1:
                System.out.println("Enter new first name:");
                this.setFirstName(choose.nextLine());
                break;
            case 2:
                System.out.println("Enter new last name:");
                this.setLastName(choose.nextLine());
                break;
            default:
                System.out.println("Invalid Choice.");
        }
    }
}

