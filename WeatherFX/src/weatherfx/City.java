package weatherfx;

/**
 * It represents the place the user searched.
 * 
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class City {
    private Coordinates coordinates;
    private String name;

    /**
     * It creats a City object using a Coordinates object and a Stirng object.
     * @param coordinates A Coordinates object which represents the coordinates of the place.
     * @param name A String object which represents the name of the place.
     */
    public City(Coordinates coordinates, String name) {
        this.coordinates = coordinates;
        this.name = name;
    }

    /**
     * It returns a Coordinates object which represents the coordinates of the city.
     * @return A Coordinates object of the city.
     * @see weatherfx.Coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * It sets the coordinates of this City object.
     * @param coordinates A Coordinates object which represents the coordinates of the city.
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Returns a String object which represents the name of the city.
     * @return A String object which represents the name of the city.
     */
    public String getName() {
        return name;
    }

    /**
     * It sets the name of the city.
     * @param name A String object which represents the name of the city.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * It returns a description of this City object.
     * @return A String object which contains the name of the locality and the coordinates
     * @see Coordinates#toString() 
     * @see String#toString()
     */
    @Override
    public String toString()
    {
        return "locality: " + name + "\n" + coordinates;
    }
    
    
    
}
