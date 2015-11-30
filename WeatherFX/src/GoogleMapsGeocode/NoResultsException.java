/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     * It creats a NoResutsException object which contains a String object.
     * @param address A String which represents the address which Google Maps doesn't found.
     * @see GoogleMapsGeocoding#getCity(java.lang.String) 
     */
    public NoResultsException(String address) {
        this.address = address;
    }

    /**
     * It returns the address which Google Maps doesn't found
     * @return A String which represents the address which Google Maps doesn't found.
     */
    public String getAddress() {
        return address;
    }
       
}
