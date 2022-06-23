
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muhammad Talha Tahir
 */
public class menuHnd implements MenuListener{

    @Override
    public void menuSelected(MenuEvent e) {
        String message = "This Web Browser is developed by Bitf19a003\nas an OOAD assignment.";
        JOptionPane.showMessageDialog(null,message, "About Us" , JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        return;
                
    }

    @Override
    public void menuCanceled(MenuEvent e) {
        return;
    }
}
