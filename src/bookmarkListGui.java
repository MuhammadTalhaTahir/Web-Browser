import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.util.Vector;
import javax.swing.*;
public class bookmarkListGui {
    JFrame win;
    JList list;
    DefaultListModel listModel;
    
    public bookmarkListGui(Vector<String> data){
        this.addlistCompenents(data);
        this.init();
    }
    private void addlistCompenents(Vector<String> data){
        listModel = new DefaultListModel();
        for(int i=0; i<data.size(); i++){
            listModel.addElement(data.elementAt(i));
        }
        
    }
    public void init(){
        win = new JFrame();
        win.setTitle("Favourites List");
        win.setLayout(new FlowLayout());
        
        list = new JList(listModel);
        list.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        list.setBackground(new Color(245, 245, 245));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        win.add(new JScrollPane(list));
        
        win.pack();
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        win.setVisible(true);
        win.setResizable(false);
    }
}
