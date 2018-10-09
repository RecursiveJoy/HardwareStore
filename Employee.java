package hardwarestoreplus;

import java.util.Scanner;


/** class Employee is a special type of User
 * @author Megan West
 */
public class Employee extends User {

    private String idNumber;
    private String firstName;
    private String lastName;
    private int ssNumber;
    private float salary;
    private static final long serialVersionUID = -4541863609115392566L;

    public Employee(){
        this.idNumber = this.getRandomID();
    }

    /** toString returns the object as a string
     * @author Megan West
     * @return string form of object
     */
    public String toString(){
        return "User{" +
                "idNumber=" + idNumber + '\\' +
                ", First name= " + firstName + '\\' +
                ", Last name= " + lastName + '\\' +
                ", phoneNumber= " + ssNumber + '\\' +
                ", address= " + salary + '\\' +
                '}';
    }

    /** display displays the object's fields
     * @author Megan West
     */
    public void display(){
        firstName = this.getFirstName();
        firstName = firstName.substring(0, Math.min(firstName.length(), 8)) + "...";

        lastName = this.getLastName();
        lastName = lastName.substring(0, Math.min(lastName.length(), 8)) + "...";

        System.out.printf("%-12s | %-12s | %-12s | %-12d | %-12.2f\n", this.getIdNumber(), firstName,
                this.getLastName(), this.getSSNumber(), this.getSalary());
    }

    /**getSSNumber retrieves the ssNumber
     * @author Megan West
     * @return ssNumber
     */
    public int getSSNumber() {
        return ssNumber;
    }

    /**setSSNumber sets the value of ssNumber
     * @author Megan West
     * @param ssNumber
     */
    public void setSSNumber(int ssNumber) {
        this.ssNumber = ssNumber;
    }

    /**getSalary retrieves the value of salary
     * @author Megan West
     * @return salary
     */
    public float getSalary() {
        return salary;
    }

    /**setSalary sets the value of salary
     * @author Megan West
     * @param salary is the user's salary
     */
    public void setSalary(float salary) {
        this.salary = salary;
    }

    /**update allows the user to update the object's fields
     * @author Megan West
     */
    public void update(){
        Scanner choose = new Scanner(System.in);
        Scanner fieldIn = new Scanner(System.in);
        String choice;

        System.out.println("1: First Name");
        System.out.println("2: Last Name");
        System.out.println("3: Social Security Number");
        System.out.println("4: Monthly Salary");

        System.out.println("Which Employee field would you like to update?");
        choice = choose.next();

        while(!choice.matches("[1-4]")){
            System.out.println("Please enter a valid choice:");
            choice = choose.next();
        }

        switch (Integer.parseInt(choice)) {
            case 1:
                System.out.println("Enter new first name:");
                this.setFirstName(fieldIn.nextLine());
                break;
            case 2:
                System.out.println("Enter new last name:");
                this.setLastName(fieldIn.nextLine());
                break;
            case 3:
                System.out.println("Enter new SS number");
                this.setSSNumber(fieldIn.nextInt());
                break;
            case 4:
                System.out.println("Enter new Salary");
                this.setSalary(fieldIn.nextFloat());
                break;

            default:
                System.out.println("Invalid choice. Try again.");
        }

    }

}