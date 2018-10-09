package hardwarestoreplus;

/**class PointOfSaleProgram is the main class
 * @author Megan West
 */
public class PointOfSaleProgram
{
    /**main is the main method of the program
     * @author Megan West
     * @param args
     */
    public static void main(String[] args) {

        char runAgain = 'y';
        hardwarestoreplus.Menu pos = new hardwarestoreplus.Menu();
        String choice;

        do {
            choice = pos.displayMenu();
            runAgain = pos.runMenu(choice);
        }while (runAgain != 'n');

    }
}