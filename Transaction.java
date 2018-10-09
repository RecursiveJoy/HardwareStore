package hardwarestoreplus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/** Transaction class holds data for one sale transaction
 * @author Megan West
 */
public class Transaction implements Serializable {
    private String transactionID;
    private Date saleDate;
    private int saleQuantity;
    private String customerID;
    private String employeeID;
    private float total;
    private static final long serialVersionUID = 3L;

    ArrayList<Item> order = new ArrayList<>();

    /**constructor initializes transactionID with a random ID
     * @author Megan West
     */
    public Transaction(){
        this.transactionID = getRandomID();
    }

    /**addToOrder adds a new Item to the customer's order
     * @author Megan West
     * @param input Item to add
     * @return success or failure
     */
    public boolean addToOrder(String input) {
        ItemManager hinventory = new ItemManager("h");
        ItemManager ainventory = new ItemManager("a");
        Item current;

        int index = hinventory.searchItem(input);

        if (index >= 0){
            current = hinventory.getItem(index);
            order.add(current);
        }
        else
            index = ainventory.searchItem(input);

        if (index >= 0){
            current = ainventory.getItem(index);
            order.add(current);
        }
        else {
            System.out.println("Item does not exist in database.");
            return false;
        }

        return true;
    }

    /**subtotal calculates the subtotal
     * @author Megan West
     * @param quantity number being purchased
     * @param i index of item being purchased
     * @return subtotal
     */
    public float subtotal(int quantity, int i){

        float itemPrice = order.get(i).getPrice();

        return quantity * itemPrice;
    }

    /**setSaleDate sets the date of the sale
     * @author Megan West
     */
    public void setSaleDate() {

        Scanner dayIn = new Scanner(System.in);
        Scanner monthIn = new Scanner(System.in);
        Scanner yearIn = new Scanner(System.in);
        int month;
        int day;
        int year;
        Date transDate = new Date();
        boolean validDate = false;

        do {
            System.out.println("Enter the day of the month:");
            day = dayIn.nextInt();

            System.out.println("Enter the month:");
            month = monthIn.nextInt();

            System.out.println("Enter the year:");
            year = yearIn.nextInt();

            transDate.setDate(month, day, year);

            if (transDate.getDay() != 0 && transDate.getMonth() != 0 && transDate.getYear() != 0) {
                validDate = true;
            } else {
                System.out.println("The date you entered is invalid.");
                System.out.println("Please reenter the date.");
            }

        } while (!validDate);

        saleDate = transDate;
    }

    /**getTransactionID returns value of transactionID
     * @author Megan West
     * @return transactionID
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**printSaleData prints the sale data
     * @author Megan West
     */
    public void printSaleData(){
        System.out.println("Customer ID = " + getCustomerID());
        System.out.println("Employee ID = " + getEmployeeID());
        System.out.println("Date: " + getDate());
        System.out.println("------------------------------");

        for (Item saleItem : order) {
            System.out.println("Item ID: " + saleItem.getIdNumber());
            System.out.println("name: " + saleItem.getName());
        }

        System.out.println("Total items purchased: " + getSaleQuantity());
        System.out.println("Total Sale: " + getTotal());
    }


    /**getRandomID generates a random ID for the transaction.
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

    /** getDate returns the saleDate
     * @author Megan West
     * @return saleDate
     */
    public Date getDate(){
        return saleDate;
    }

    /**printDate prints the date
     * @author Megan West
     */
    public void printDate(){
        System.out.println(saleDate.toString());
    }

    /**setSaleQuantity sets quantity value
     * @author Megan West
     * @param quantity number of items purchased
     */
    public void setSaleQuantity(int quantity){
        saleQuantity = quantity;
    }

    /**getSaleQuantity retrieves sale quantity
     * @author Megan West
     * @return
     */
    public int getSaleQuantity(){
        return saleQuantity;
    }

    /**getCustomerID returns customerID
     * @author Megan West
     * @return customerID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**setCustomerID sets the value of customerID
     * @author Megan West
     * @param customerID the customer's random ID
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**getEmployeeID gets the employee id of the employee who did the sale
     * @author Megan West
     * @return EID
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**setEmployeeID sets the value of employeeID
     * @author Megan West
     * @param employeeID employee's ID
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**getTotal retrieves the total sale value
     * @author Megan West
     * @return total
     */
    public float getTotal() {
        return total;
    }

    /**setTotal sets the value of total
     * @author Megan West
     * @param total total sale value
     */
    public void setTotal(float total) {
        this.total = total;
    }
}
