package hardwarestoreplus;

import java.io.*;
import java.util.*;


/**class ItemManager manages the item databases.
 * @author Megan West
 */
public class ItemManager {

    private ArrayList<Item> inventory;
    private String arrayType;

    /**Constructor initializes arrayType and reads an array from appropriate database.
     * @author Megan West
     * @param arrayType
     */
    public ItemManager(String arrayType){

        inventory = new ArrayList<>();
        this.arrayType = arrayType;
        if(arrayType.equalsIgnoreCase("h")){
            readFromFileHW();
        }
        else if (arrayType.equalsIgnoreCase("a")){
            readFromFileApp();
        }
    }


    /** readFromFileHW reads from the hardware database
     * @author Megan West
     * @return boolean indicates success or failure.
     */
    public boolean readFromFileHW() {

        //tries to open a file and read using OIS
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("hardwaredb.dat"));

            inventory = (ArrayList<Item>) ois.readObject();

            ois.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Exception occurred while reading from file.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (EOFException e) {
            System.out.println("This is a new database.");
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

    /** readFromFileApp reads from the appliance database
     * @author Megan West
     * @return boolean indicates success or failure.
     */
    public boolean readFromFileApp() {

        //tries to open a file and read using OIS
        try{

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("appliancedb.dat"));

            inventory = (ArrayList<Item>) ois.readObject();

            ois.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Exception occurred while reading from file.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (EOFException e) { //this exception is thrown when reading from an empty new database
        } catch (InvalidClassException e) {
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


    /** writeToFile writes the inventory array to the database file.
     * @author Megan West
     */
    public void writeToFile(){

        String filename;

        //determines which database we are going to write to
        if (arrayType == "h"){
            filename = "hardwaredb.dat";
        }
        else {
            filename = "appliancedb.dat";
        }

        //empties out the file so we can enter the updated arrayList
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

            oos.writeObject(inventory);
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


    /**addQuantity tries to update quantity of item, then tries to add it
     * @author Megan West
     * @param input is the name of the item to update
     * @param quantity is the new quantity to add to existing quantity
     */
    public void addQuantity(String input, int quantity) {

        int index = searchItem(input);      //problem: quantity can be negative

        if (index != -1){
            System.out.println("Item was found:");
            displayItem(index);
            inventory.get(index).setQuantity(inventory.get(index).getQuantity() + quantity);
            System.out.println("updating...");
            displayItem(index);
        }
        else if (arrayType.equalsIgnoreCase("h")){
            System.out.println("The item you are adding does not yet exist.");
            HardwareItem newHWItem = new HardwareItem();
            setValuesHW(newHWItem);
            inventory.add(newHWItem);
        }
        else if (arrayType.equalsIgnoreCase("a")){
            System.out.println("The item you are adding does not yet exist.");
            Appliance newAppItem = new Appliance();
            setValuesApp(newAppItem);
            inventory.add(newAppItem);
        }
    }

    public boolean removeQuantity(String input, int quantity){
        int index = searchItem(input);
        boolean success;

        if (index >= 0){
            if (inventory.get(index).getQuantity() >= quantity){
                inventory.get(index).setQuantity(inventory.get(index).getQuantity() - quantity);
                success = true;}
            else{
                System.out.println("There are not enough items in stock.");
                success = false;}
        }
        else{
            success = false;
        }
        return success;
    }

    /**
     * setValuesHW sets the fields of a new HardwareItem to given values
     * @author Megan West
     * @param newItem is the newly created Item
     * @return void
     */
    private void setValuesHW(HardwareItem newItem){
        String choice = "0";

        Scanner scanner = new Scanner(System.in);
        Scanner repeat = new Scanner(System.in);

        System.out.println("Please enter the full item name:");
        newItem.setName(scanner.nextLine());

        System.out.println("Please enter the new quantity:");
        newItem.setQuantity(scanner.nextInt());

        System.out.println("Please enter the item price:");
        newItem.setPrice(scanner.nextFloat());

        while (!choice.matches("[1-5]")) {
            System.out.println("Please choose a category number:");
            System.out.println("1. Door & Window");
            System.out.println("2. Cabinet&Furniture");
            System.out.println("3. Fasteners");
            System.out.println("4. Structural");
            System.out.println("5. Other");
            choice = scanner.next();// switch statement for choosing a category

            while (!choice.matches("[1-5]")) {
                System.out.println("Please enter a number between 1 and 5.");
                choice = repeat.next();
            }

            switch (Integer.parseInt(choice)) {
                case 1:
                    newItem.setCategory("Door & Window");
                    break;
                case 2:
                    newItem.setCategory("Cabinet & Furniture");
                    break;
                case 3:
                    newItem.setCategory("Fasteners");
                    break;
                case 4:
                    newItem.setCategory("Structural");
                    break;
                case 5:
                    newItem.setCategory("Other");
                    break;
                default:
                    System.out.println("Invalid Category");
                    break;
            }
        }
    }

    /**
     * setValuesHW sets the fields of a new Appliance to given values
     * @author Megan West
     * @param newItem is the newly created Item
     * @return void
     */
    private void setValuesApp(Appliance newItem){
        String choice = "0";

        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);

        System.out.println("Please enter the full item name:");
        newItem.setName(scanner.nextLine());

        System.out.println("Please enter the new quantity:");
        newItem.setQuantity(scanner.nextInt());

        System.out.println("Please enter the item price:");
        newItem.setPrice(scanner.nextFloat());

        System.out.println("Please enter the item brand:");
        newItem.setBrand(scanner2.nextLine());

        //set Type
        while (!choice.matches("[1-4]")) {

            System.out.println("Please choose an appliance type number:");
            System.out.println("1. Refrigerators");
            System.out.println("2. Washers&Dryers");
            System.out.println("3. Ranges&Ovens");
            System.out.println("4. SmallAppliances");

            choice = scanner.next();// switch statement for choosing a category

            while (!choice.matches("[1-4]")) {
                Scanner repeat = new Scanner(System.in);
                System.out.println("Please enter a number between 1 and 4.");
                choice = repeat.next();
            }

            switch (Integer.parseInt(choice)) {
                case 1:
                    newItem.setType("Refrigerators");
                    break;
                case 2:
                    newItem.setType("Washers&Dryers");
                    break;
                case 3:
                    newItem.setType("Ranges&Ovens");
                    break;
                case 4:
                    newItem.setType("SmallAppliances");
                    break;
                default:
                    System.out.println("Invalid Category");
                    break;
            }
        }
    }


    /**displayItem displays the fields of an item in inventory
     * @author Megan West
     * @param currentIndex is the index to display
     */
    public void displayItem(int currentIndex) {

        Item current = inventory.get(currentIndex);
        current.display();
    }


    /**displayAll displays all items in the corresponding database.
     * @author Megan West
     */
    public void displayAll(){
        sort();

        if(arrayType.equals("h")){
            System.out.println("----------------------------------------------------------------------");
            System.out.printf("%-12s | %-12s | %-20s | %-12s | %-12s\n", "ID NUMBER", "NAME", "CATEGORY", "QUANTITY", "PRICE");
            System.out.println("----------------------------------------------------------------------");
        }
        else if(arrayType.equals("a")){
            System.out.println("-------------------------------------------------------------------------------");
            System.out.printf("%-12s | %-12s | %-15s | %-12s | %-12s | %-12s\n", "ID NUMBER", "NAME", "TYPE", "BRAND", "QUANTITY", "PRICE");
            System.out.println("-------------------------------------------------------------------------------");
        }


        int index = 0;

        for (Item current : inventory){
            displayItem(index);
            index++;
        }
    }

    /**searchItem searches for the item in the database, returns index
     * @author Megan West
     * @param input: the string to search for
     * @return i: the index of the found item.
     */
    public int searchItem(String input) {
        int found = 0;
        Item current;
        int index = 0;
        int i;
        Scanner in = new Scanner(System.in);

        //if the inventory has items and the item has not been found yet,
        //search for an item with matching string and return its' index
        if (!inventory.isEmpty()) {

            for (i = 0; i < inventory.size(); i++) {
                current = inventory.get(i);

                if (current.getName().contains(input)) {
                    this.displayItem(i);
                    index = i;
                    found++;
                }
            }

            if (found == 1)
                return index;

            else if (found >= 2) {
                System.out.println("\nNarrow down your search by entering the full item name:");
                input = in.nextLine();
                index = searchItem(input); //causes displayItem to run more than once
            } else {
                index = -1;
            }
        }
        else
            index = -1;

        return index;
    }

    /**sort sorts the inventory by ID number
     * @author Megan West
     */
    private void sort(){
        Collections.sort(inventory);
    }


    /** removeItem locates an item in the database and removes it if present.
     * @author Megan West
     * @param input is the name of the item to remove
     * @return boolean indicating success or failure
     */
    public boolean removeItem(String input){
        if(!inventory.isEmpty()) {

            int index = searchItem(input);

            if (index >= 0) {
                //Item removed = inventory.get(index);
                inventory.remove(index);
            }
            else
                System.out.println("That item was not found.");
        }
        else
            System.out.println("There are no items to remove.");

        return true;
    }

    /**getItem makes it easier to get an item at a given index in the inventory.
     * @author Megan West
     * @param index = index of item to return
     * @return inventory.get(index) is an item at the provided index
     */
    public Item getItem(int index){
        return inventory.get(index);
    }
}