/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherwidgetfx;

import javafx.scene.control.ListCell;

/**
 *
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class CityCell extends ListCell<City>{

    @Override
    protected void updateItem(City item, boolean empty) {
        
        super.updateItem(item, empty);
        
        if(item != null)
        {
            setText(item.getName());
        }
    }
    
}
