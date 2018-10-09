package hardwarestoreplus;

/**Appliance Class creates one appliance object
 * @author Megan West
 */
public class Appliance extends Item{

    private String idNumber;
    private String name;
    private String type;
    private String brand;
    private int quantity;
    private float price;
    private static final long serialVersionUID = 7554030768942086467L;

    /**Constructor sets the random ID number
     * @author Megan West
     */
    public Appliance(){
        idNumber = this.getRandomID();
    }

    /**get Type retrieves the type
     * @author Megan West
     * @return type value
     */
    public String getType() {
        return type;
    }

    /**setType sets the type field
     * @author Megan West
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**getBrand retrieves the appliance brand
     * @author Megan West
     * @return brand value
     */
    public String getBrand() {
        return brand;
    }

    /**setBrand sets the brand field
     * @author Megan West
     * @param brand is the item's brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**toString prints the item as a string.
     * @author Megan West
     * @return string value of the object fields
     */
    @Override
    public String toString() {
        return "Appliance{" +
                "idNumber='" + idNumber + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public void display(){

        String name = this.getName();
        name = name.substring(0, Math.min(name.length(), 8));

        String type = this.getType();
        type = type.substring(0, Math.min(type.length(), 8));

        String brand = this.getBrand();
        brand = brand.substring(0, Math.min(brand.length(), 8));

        System.out.printf("%-12s | %-12s | %-12s | %-12s | %-12d | %-12.2f\n", this.getIdNumber(), name,
                type, brand, this.getQuantity(), this.getPrice());
    }

}
