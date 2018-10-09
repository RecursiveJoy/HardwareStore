package hardwarestoreplus;

import java.util.Scanner;


/** class Menu runs a particular method based on the user's choice.
 * @author Megan West
 */
public class Menu {

    hardwarestoreplus.ItemManager hardwareInventory = new hardwarestoreplus.ItemManager("h");
    hardwarestoreplus.ItemManager applianceInventory = new hardwarestoreplus.ItemManager( "a");
    UserManager customerRoster = new UserManager("c");
    UserManager employeeRoster = new UserManager("e");
    TransactionManager saleHistory = new TransactionManager();

    /** displayMenu displays the menu options and gets a choice from the user.
     * @author Megan West
     * @return int choice is the user's choice from menu options.
     */
    public String displayMenu(){
        System.out.println("\n\nChoose an option below:\n");
        System.out.println("1: Show all existing items in database sorted by ID.");
        System.out.println("2: Add a new item or quantity to the database.");
        System.out.println("3: Delete an item from a database.");
        System.out.println("4: Search for an item (given its name or part of its name).");
        System.out.println("5: Show a list of users in the database.");
        System.out.println("6: Add a new user to the database.");
        System.out.println("7: Update user info(given their ID)");
        System.out.println("8: Complete a sale transaction");
        System.out.println("9: Show completed sale transactions");
        System.out.println("10: Save and Exit");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();

        while (!choice.matches("[1-9]|1[0]")){
            System.out.println("Your choice was invalid. Please enter a valid choice.");
            choice = scanner.next();
        }

        return choice;
    }


    /** runMenu runs appropriate code based on the user's choice
     * @author Megan West
     * @param choice user's menu choice
     * @return char run again
     */
    public char runMenu(String choice){
        char runAgain = 'y';
        String db;

        switch(Integer.parseInt(choice)) {
            case 1:
                hardwareInventory.displayAll();
                applianceInventory.displayAll();

                break;

            case 2:
                Scanner input = new Scanner(System.in);
                Scanner addTo = new Scanner(System.in);
                Scanner q = new Scanner(System.in);


                System.out.println("Do you want to update (H)ardware or (A)ppliances?");
                String update = input.next();
                while(!update.matches("[HAha]")){
                    System.out.println("Enter a valid choice:");
                    update = input.next();
                }


                System.out.println("Enter the name of the item to update:");
                String thing = addTo.next();
                System.out.println("Enter the new quantity:");
                int quantity = q.nextInt();
                while(quantity < 0){
                    System.out.println("Enter a positive number:");
                    quantity = q.nextInt();
                }

                if (update.equalsIgnoreCase("h")){
                    hardwareInventory.addQuantity(thing, quantity);
                    hardwareInventory.displayAll();

                }
                else if (update.equalsIgnoreCase("a")){
                    applianceInventory.addQuantity(thing, quantity);
                    applianceInventory.displayAll();
                }
                else {
                    System.out.println("Incorrect input. Try again.");
                }

                break;

            case 3:
                Scanner ud = new Scanner(System.in);
                String database;
                Scanner three = new Scanner(System.in);
                boolean success = false;
                thing = "";

                System.out.println("Do you want to update (H)ardware or (A)ppliances?");
                database = ud.next();
                while(!database.matches("[HAha]")){
                    System.out.println("Enter a valid choice:");
                    database = ud.next();
                }

                if (database.equalsIgnoreCase("h")){
                    System.out.println("Enter the name of the item to remove:");
                    thing = three.nextLine();
                    success = hardwareInventory.removeItem(thing);

                }
                else if (database.equalsIgnoreCase("a")){
                    System.out.println("Enter the name of the item to remove:");
                    thing = three.nextLine();
                    success = applianceInventory.removeItem(thing);
                }
                else {
                    System.out.println("Incorrect input. Try again.");
                }

                if (success)
                    System.out.println(thing + " was deleted succesfully.");


                break;

            case 4:
                Scanner find = new Scanner(System.in);
                System.out.println("Enter the name of the item you wish to search for:");
                thing = find.next();

                int index = hardwareInventory.searchItem(thing);

                if (index >= 0){
                    hardwareInventory.displayItem(index);
                }
                else {
                    index = applianceInventory.searchItem(thing);

                    if (index >= 0) {
                        applianceInventory.displayItem(index);
                    } else {
                        System.out.println("Item does not exist in database.");
                    }
                }
                break;

            case 5:

                System.out.println("\n\nCUSTOMERS:");
                customerRoster.displayAll();

                System.out.println("\nEMPLOYEES:");
                employeeRoster.displayAll();

                break;

            case 6:
                System.out.println("Do you want to add an (E)mployee or a (C)ustomer?");
                Scanner six = new Scanner(System.in);
                db = six.nextLine();
                while(!db.matches("[EeCc]")) {
                    System.out.println("Enter a valid choice:");
                    db = six.next();
                }

                if (db.equalsIgnoreCase("e")){
                    System.out.println("Enter the user ID you want to add");
                    String EID = six.nextLine();
                    employeeRoster.addUser(EID);
                    employeeRoster.displayAll();
                }
                else if (db.equalsIgnoreCase("c")){
                    System.out.println("Enter the user ID you want to add");
                    String CID = six.nextLine();
                    customerRoster.addUser(CID);
                    customerRoster.displayAll();
                }
                else {
                    System.out.println("Incorrect input. Try again.");
                }

                break;

            case 7:
                String idNumber;

                System.out.println("Do you want to update an (E)mployee or a (C)ustomer?");
                Scanner seven = new Scanner(System.in);
                Scanner sevenplus = new Scanner(System.in);
                db = seven.next();

                while(!db.matches("[EeCc]")) {
                    System.out.println("Enter a valid choice:");
                    db = seven.next();
                }
                if (db.equalsIgnoreCase("e")){
                    System.out.println("Enter the full user ID you want to update:");
                    idNumber = sevenplus.nextLine();
                    employeeRoster.update(idNumber);
                }
                else if (db.equalsIgnoreCase("c")){
                    System.out.println("Enter the full user ID you want to update:");
                    idNumber = sevenplus.nextLine();
                    customerRoster.update(idNumber);
                }
                else {
                    System.out.println("Incorrect input. Try again.");
                }
                break;

            case 8:
                saleHistory.newTransaction();
                break;

            case 9:
                saleHistory.displayAll();
                break;

            case 10:
                hardwareInventory.writeToFile();
                applianceInventory.writeToFile();
                customerRoster.writeToFile();
                employeeRoster.writeToFile();
                saleHistory.writeToFile();

                runAgain = 'n';
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

        return runAgain;
    }
}