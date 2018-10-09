package hardwarestoreplus;

import java.io.*;
import java.util.*;

public class UserManager {
    private ArrayList<User> roster;
    private String arrayType;

    /**Constructor initializes roster and arrayType
     * @author Megan West
     * @param arrayType determines which database will be used by this UserManager
     */

    public UserManager(String arrayType){

        roster = new ArrayList<>();
        this.arrayType = arrayType;
        boolean success = false;

        if(arrayType.equalsIgnoreCase("c")){
            success = readFromFileCust();
        }
        else if (arrayType.equalsIgnoreCase("e")){
            success = readFromFileEmp();
        }

        if(success)
            System.out.println("Database read successfully.");

    }

    /**readFromFileCust sets inventory = to the array in the customer database.
     * @author Megan West
     * @return boolean to indicate success or failure.
     */
    public boolean readFromFileCust() {

        //tries to open a file and read using OIS
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("customerdb.dat"));

            roster = (ArrayList<User>) ois.readObject();

            ois.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Exception occurred while reading from file.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (EOFException e) { //this exception is thrown when using a new empty database file. There is nothing to do.
        } catch (IOException e){
            System.out.println("Exception occurred while reading from file.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Exception occurred while reading from file.");
            e.printStackTrace();
        }

        return true;
    }

    /**readFromFileEmp sets roster = to the array in the employee database.
     * @author Megan West
     * @return boolean to indicate success or failure.
     */
    public boolean readFromFileEmp() {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("employeedb.dat"));

            roster = (ArrayList<User>) ois.readObject();

            ois.close();

        }catch (EOFException e) { //this exception is thrown when a new empty database file is used.
        }catch (IOException e1) {
            System.out.println("IO exception occurred while reading from file.");
            e1.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not Found Exception occurred while reading from file.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Exception occurred while reading from file.");
            e.printStackTrace();
        }

        return true;
    }

    /**writeToFile clears the database file and writes roster ArrayList to it.
     * @author Megan West
     */
    public void writeToFile(){

        //empties out the file so we can enter the updated arrayList

        String filename;

        //determines which database we are going to write to
        if (arrayType == "e"){
            filename = "employeedb.dat";
        }
        else {
            filename = "customerdb.dat";
        }

        try {
            FileWriter fileOut = new FileWriter(filename);
            fileOut.write("");
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //try to write the inventory arrayList to file
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));

            oos.writeObject(roster);
            oos.close();

        } catch (IOException e) {
            System.out.println("Exception occurred while writing to file.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Caught unknown exception while writing to file.");
            e.printStackTrace();
        }
    }


    /** addUser checks if the ID is already in the database or it lets you add a user
     * @author Megan West
     * @param idNumber is the user ID number
     */
    public void addUser(String idNumber) {

        int index = searchUser(idNumber);      //problem: quantity can be negative

        if (index != -1){
            System.out.println("User exists in the system.");
            displayUser(index);
        }
        else if (arrayType.equalsIgnoreCase("c")){
            System.out.println("The customer you are adding does not yet exist.");
            Customer newCX = new Customer();
            newCX = setValuesCX(newCX);
            roster.add(newCX);
        }
        else if (arrayType.equalsIgnoreCase("e")){
            System.out.println("The employee you are adding does not yet exist.");
            Employee newHire = new Employee();
            newHire = setValuesEmp(newHire);
            roster.add(newHire);
        }
    }

    /**
     * setValuesCX sets the fields of a new Customer to given values
     * @author Megan West
     * @param newCX is the newly created Customer
     * @return void
     */
    private Customer setValuesCX(Customer newCX){

        Scanner first = new Scanner(System.in);
        Scanner last = new Scanner(System.in);
        Scanner phone = new Scanner(System.in);
        Scanner address = new Scanner(System.in);

        System.out.println("Please enter the Customer's first name:");
        newCX.setFirstName(first.next());

        System.out.println("Please enter the Customer's last name:");
        newCX.setLastName(last.next());

        System.out.println("Please enter the customer's phone number with dashes:");
        newCX.setPhoneNumber(phone.next());

        System.out.println("Enter the customer's address:");
        newCX.setAddress(address.nextLine());

        return newCX;
    }

    /**
     * setValuesEmp sets the fields of a new Employee to given values
     * @author Megan West
     * @param newUser is the newly created Employee
     * @return void
     */
    private Employee setValuesEmp(Employee newUser){

        Scanner first = new Scanner(System.in);
        Scanner last = new Scanner(System.in);
        Scanner ssnum = new Scanner(System.in);
        Scanner sal = new Scanner(System.in);

        System.out.println("Please enter the full first name:");
        newUser.setFirstName(first.next());

        System.out.println("Please enter the full last name:");
        newUser.setLastName(last.next());

        System.out.println("Please enter the Social Security Number:");
        newUser.setSSNumber(ssnum.nextInt());

        System.out.println("Please enter the monthly Salary:");
        newUser.setSalary(sal.nextFloat());

        return newUser;
    }

    /**displayUser displays a single user's fields
     * @author Megan West
     * @param currentIndex is the index of the item to display
     */
    public void displayUser(int currentIndex) {

        User current = roster.get(currentIndex);
        current.display();
    }


    /**displayAll displays the contents of both types of Item databases
     * @author Megan West
     */
    public void displayAll(){

        sort();

        if(arrayType.equals("e")){
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.printf("%-12s | %-12s | %-12s | %-12s | %-12s\n", "ID NUMBER", "FIRST NAME", "LAST NAME", "SS #", "SALARY");
            System.out.println("-------------------------------------------------------------------------------------------------");
        }
        else if(arrayType.equals("c")){
            System.out.println("--------------------------------------------------------------------------------------------------");
            System.out.printf("%-12s | %-12s | %-12s | %-12s | %-12s\n", "ID NUMBER", "FIRST NAME", "LAST NAME", "PHONE #", "ADDRESS");
            System.out.println("---------------------------------------------------------------------------------------------------");
        }

        int index = 0;

        for (User current : roster){
            displayUser(index);
            index++;
        }

    }


    /**searchUser searches for a user given their user ID
     * @author Megan West
    //*@return i: the index of the found item. */
    private int searchUser(String input) {
        int found = 0;
        User current;
        int index = 0;
        int i;
        Scanner in = new Scanner(System.in);

        //if the inventory has users and the user has not been found yet,
        //search for a user with matching string and return its' index
        if (!roster.isEmpty()) {

            for (i = 0; i < roster.size(); i++) {
                current = roster.get(i);

                if (current.getIdNumber().contains(input)) {
                    this.displayUser(i);
                    index = i;
                    found++;
                }
            }

            if (found == 1)
                return index;

            else if (found >= 2) {
                System.out.println("\nNarrow down your search by entering the full User ID:");
                input = in.nextLine();
                index = searchUser(input);
            } else {
                index = -1;
            }
        }
        else
            index = -1;

        return index;
    }

    /**sort uses the Collections class's sort method to sort roster by ID
     * @author Megan West
     */
    private void sort(){
        Collections.sort(roster);
    }

    /**removeUser deletes a user from the roster
     * @author Megan West
     * @param input is the userID to remove
     * @return boolean indicates success or failure.
     */
    public boolean removeUser(String input) {
        int index = searchUser(input);

        if (!roster.isEmpty()) {
            if (index < 0) {
                System.out.println("That is an invalid user ID.");
            } else {
                User removed = roster.get(index);
                roster.remove(index);
            }
        } else {
            System.out.println("There are no users to remove.");
        }

        return true;

    }


    /**update allows the user to update a User's fields, then displays updated User.
     * @author Megan West
     * @param input is the User ID to update.
     * @return boolean indicates success or failure
     */
    public boolean update(String input){
        /* Check if the user is in the database. */
        int index = searchUser(input);

        if (index < 0) {
            System.out.println("That user is not in the database.");
        } else {
            roster.get(index).update();
            displayUser(index);
        }
        return true;
    }

}
