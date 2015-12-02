package GoogleMapsGeocode;

/**
 * This is an exception which is thrown when Google Maps doesn't find results.
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 * 
 * @see GoogleMapsGeocoding#getCity(java.lang.String) 
 */
public class NoResultsException extends Exception{
    private String address;
    
    /**
     * Creats a NoResutsException object which contains a String object.
     * @param address A String which represents the address which Google Maps doesn't found.
     * @see GoogleMapsGeocoding#getCity(java.lang.String) 
     */
    public NoResultsException(String address) {
        this.address = address;
    }

    /**
     * Returns the address which Google Maps doesn't found
     * @return A String which represents the address which Google Maps doesn't found.
     */
    public String getAddress() {
        return address;
    }
       
}
