package hardwarestoreplus;

import java.io.Serializable;

/**class Item is an object type intended to be stored in a database
 * @author Megan West
 */
public class Item implements Serializable, Comparable<Item>{

    private String idNumber;
    private String name;
    private int quantity;
    private float price;
    private static final long serialVersionUID = 1L;


    /**constructor initializes idNumber with random value
     * @author Megan West
     */
    public Item(){
        this.idNumber = this.getRandomID();
    }

    /**parameterized constructor builds an Item from given values.
     * @author Megan West
     * @param idNumber is the object's id number
     * @param name is the name of the object
     * @param quantity is the number in stock
     * @param price is the price of the object
     */
    public Item(String idNumber, String name, int quantity, float price) {
        this.idNumber = idNumber;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    /**getRandomID generates a random 5 digit alphanumeric ID
     * @author Megan West
     * @return randomID is the random id number that will be assigned to idNumber
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

    /**toString creates a string version of the object
     * @author Megan West
     * @return string version of object
     */
    @Override
    public String toString() {
        return "Item{" +
                "idNumber='" + idNumber + '\\' +
                ", name='" + name + '\\' +
                ", quantity=" + quantity + '\\' +
                ", price=" + price +
                '}';
    }

    /**compareTo allows the arrayList items to be sorted
     * @author Megan West
     * @param nextItem is the next item to compare current item to
     * @return IdNumber
     */
    public int compareTo(Item nextItem){
        return idNumber.compareTo(nextItem.getIdNumber());
    }

    /**getIdNumber retrieves value of idNumber
     * @author Megan West
     * @return idNumber
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**getName retrieves value of name
     * @author Megan West
     * @return name
     */
    public String getName() {
        return name;
    }


    /**getQuantity retrieves value of quantity
     * @author Megan West
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**setQuantity sets quantity value
     * @author Megan West
     * @param quantity is the number in stock
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    /**getPrice retrieves the value of price field
     * @author Megan West
     * @return price
     */
    public float getPrice() {
        return price;
    }

    /**setName sets the name field value
     * @author Megan West
     * @param name is name of item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**setPrice sets the value of price field
     * @author Megan West
     * @param price is the item's price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**display displays the value of each Item field
     * @author Megan West
     */
    public void display(){
        String name = this.getName();
        name = name.substring(0, Math.min(name.length(), 11));

        System.out.printf("%-12s | %-20s | %-20d | %-12.2f\n", this.getIdNumber(), name,
                this.getQuantity(), this.getPrice());
    }
}
