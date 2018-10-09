package hardwarestoreplus;

/**class HardwareItem is a special type of Item
 * @author Megan West
 */
public class HardwareItem extends Item{

    private String idNumber;
    private String name;
    private String category;
    private int quantity;
    private float price;
    private static final long serialVersionUID = 1533090881490161266L;

    /**constructor initializes idNumber with random ID value
     * @author Megan West
     */
    public HardwareItem(){
        idNumber = getRandomID();
    }

    /**getCategory retrieves the value of Category
     * @author Megan West
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**setCategory sets the value of category
     * @author Megan West
     * @param cat
     */
    public void setCategory(String cat){
        this.category = cat;
    }


    /**toString returns the object in string form
     * @author Megan West
     * @return string version of the object
     */
    @Override
    public String toString() {
        return "HardwareItem{" +
                "idNumber='" + idNumber + '\\' +
                ", name='" + name + '\\' +
                ", category='" + category + '\\' +
                ", quantity=" + quantity + '\\' +
                ", price=" + price + '\\' +
                '}';
    }

    /**display displays the fields of the hardware Item
     * @author Megan West
     */
    public void display(){

        String name = this.getName();
        name = name.substring(0, Math.min(name.length(), 8));


        System.out.printf("%-12s | %-12s | %-15s | %12s | %-12.2f\n", this.getIdNumber(), name,
                this.getCategory(), this.getQuantity(), this.getPrice());
    }
}