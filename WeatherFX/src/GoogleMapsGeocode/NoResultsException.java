/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoogleMapsGeocode;

/**
 *
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class NoResultsException extends Exception{
    private String address;
    
    public NoResultsException(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    
    
}
