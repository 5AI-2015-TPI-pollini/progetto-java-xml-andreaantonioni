/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherfx;

/**
 * 
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class Weather {
    private String description;
    private String urlIcon;
    private String temperature;

    public Weather(String description, String urlIcon, String temperature) {
        this.description = description;
        this.urlIcon = urlIcon;
        if(temperature.indexOf(".")<0)
            this.temperature = temperature;
        else this.temperature = temperature.substring(0, temperature.indexOf("."));
        this.temperature+="°";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    
    
    @Override
    public String toString()
    {
        return description + " - " + temperature;
    }
}
