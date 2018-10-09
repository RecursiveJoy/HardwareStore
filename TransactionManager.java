package hardwarestoreplus;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**class TransactionManager manages the saleHistory
 * @author Megan West
 */
public class TransactionManager {

    private ArrayList<Transaction> saleHistory = new ArrayList<>();


    /**constructor loads array from file
     * @author Megan West
     */
    public TransactionManager () {
        readFromFile();
    }

    /**readFromFile reads array from file
     * @author Megan West
     * @return success if succeeded
     */
    public boolean readFromFile() {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("transactiondb.dat"));
            saleHistory = (ArrayList<Transaction>) ois.readObject();
            ois.close();
        }catch (EOFException e) { //this exception gets thrown when trying to read an empty file.
        }catch (IOException e1) {
            System.out.println("IO Exception occurred while reading from file.");
            e1.printStackTrace();
        }
        catch (ClassNotFoundException e1) {
            System.out.println("Class not found Exception occurred while reading from file.");
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return true;
    }


    /** writeToFile writes the saleHistory to file
     * @author Megan West
     */
    public void writeToFile(){

        //empties out the file so we can enter the updated arrayList
        try {
            FileWriter fileOut = new FileWriter("transactiondb.dat");
            fileOut.write("");
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //try to write the inventory arrayList to file
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("transactiondb.dat"));

            oos.writeObject(saleHistory);
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


    /**newTransaction creates a new Transaction record
     * @author Megan West
     */
    public void newTransaction(){
        Transaction sale = new Transaction();
        UserManager customers = new UserManager("c");
        UserManager employees = new UserManager("e");
        ItemManager hardware = new ItemManager("h");
        ItemManager appliances = new ItemManager("a");

        Scanner a = new Scanner(System.in);
        Scanner b = new Scanner(System.in);
        Scanner c = new Scanner(System.in);
        Scanner d = new Scanner(System.in);
        Scanner e = new Scanner(System.in);

        employees.displayAll();

        System.out.println("Enter the employee ID");
        String EID = a.next();
        employees.addUser(EID);
        sale.setEmployeeID(EID);

        customers.displayAll();

        System.out.println("Enter the customer ID");
        String CID = b.next();
        customers.addUser(CID);
        sale.setCustomerID(CID);

        sale.setSaleDate();

        String again;
        String newItem;
        int quantity;
        float total = 0.00f;
        int totalQuantity = 0;
        int i = 0;
        //boolean success = false;
        boolean hreduced;
        boolean areduced;

        do{
            hardware.displayAll();
            appliances.displayAll();

            System.out.println("Enter the name of the item you wish to buy");
            newItem = c.next();

            System.out.println("How many do you need?");
            quantity = d.nextInt();
            while (quantity <= 0){
                System.out.println("Enter a positive quantity.");
                quantity = d.nextInt();
            }

            //subtract quantity from stock, check if there are enough items in stock
            hreduced = hardware.removeQuantity(newItem, quantity);
            areduced = appliances.removeQuantity(newItem, quantity);

            sale.addToOrder(newItem);

            if (areduced || hreduced) {
                total += sale.subtotal(quantity, i);
                totalQuantity += quantity;
                hardware.displayAll();
                appliances.displayAll();
                i++;
            }
            else{
                System.out.println("The requested quantity exceeds items in stock.");
            }

            System.out.println("Do you want to add another item? y|n");
            again = e.next();
            while(!again.matches("[yYnN]")){
                System.out.println("Enter valid input.");
                again = e.next();
            }

        }while (again.equalsIgnoreCase("y"));

        sale.setTotal(total);
        sale.setSaleQuantity(totalQuantity);

        sale.printSaleData();

        saleHistory.add(sale);

        writeToFile();
    }

    /**displayAll displays the entire transaction history
     * @author Megan West
     */
    public void displayAll(){

        for (int index = 0; index < saleHistory.size(); index++){
            displayTransaction(index);
            index++;
        }
    }


    /**displayTransaction displays an individual sale data
     * @author Megan West
     * @param currentIndex index of the transaction to display
     */
    public void displayTransaction(int currentIndex) {

        Transaction current = saleHistory.get(currentIndex);

        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-12s | %-12s | %-12s | %-12s | %-12s \n", "TID", "EID", "CID", "QUANTITY", "TOTAL");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-12s | %-12s | %-12s | %-12d | %-12f ", current.getTransactionID(), current.getEmployeeID(),
                current.getCustomerID(), current.getSaleQuantity(),
                current.getTotal());
        current.printDate();
    }
}
