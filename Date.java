package hardwarestoreplus;

import java.io.Serializable;

/**class Date knows the day, month, year and formats itself
 * @author Megan West
 */
public class Date implements Serializable {

    private int day;
    private int month;
    private int year;
    private static final long serialVersionUID = -316788964911571346L;


    /**Default constructor
     * @author Megan West
     */
    public Date (){}


    /**setDate builds a formatted date m/d/y
     * @author Megan West
     * @param m is the month
     * @param d is the day
     * @param y is the year
     */
    public void setDate(int m, int d, int y){

        month = ((m > 0 && m < 13) ? m : 0);
        year = ((y > 2000 && y < 3000) ? y : 0);
        //month and year input validation, set to 0 if invalid.

        if (month == 2) {
            day = ((d > 0 && d < 30) ? d : 0);
        }
        //if it is Feb, only 29 days allowed

        else if (month == 9 || month == 4 || month == 6 || month == 11){
            day = ((d > 0 && d < 31) ? d : 0);
        }
        //April, June, September, November only have 30 days.

        else if (month > 0 || month < 13){
            day = ((d > 0 && d < 32) ? d : 0);
        }
        //All other months have 31 days.

        else
            System.out.println("That is an invalid day.");
    }


    /**toString returns the object as a string
     * @author Megan West
     * @return string value of item
     */
    public String toString(){
        return String.format("%d/%d/%d", month, day, year);
    }


    /** getDay returns the day
     * @author Megan West
     * @return day
     */
    public int getDay() {
        return day;
    }


    /** getMonth returns the month
     * @author Megan West
     * @return month
     */
    public int getMonth() {
        return month;
    }


    /**getYear returns the year
     * @author Megan West
     * @return year
     */
    public int getYear() {
        return year;
    }

}