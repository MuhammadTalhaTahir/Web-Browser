
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class listHnd implements ListSelectionListener{
    
    controller ctrl;
    public listHnd(controller c){
        ctrl = c;
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(ctrl.bookmarklist != null && e.getSource().equals(ctrl.bookmarklist.list)){
            if(e.getValueIsAdjusting()){     //https://docs.oracle.com/javase/8/docs/api/javax/swing/ListSelectionModel.html#setValueIsAdjusting-boolean-
                String URL = ctrl.bookmarklist.list.getSelectedValue().toString();
                StringTokenizer tk = new StringTokenizer(URL, " - ");
                URL = tk.nextToken();
                ctrl.loadHtmlPage(URL);
                ctrl.backTrack.add(ctrl.currentUrl);
                ctrl.currentUrl = URL;
                ctrl.writeHistory(ctrl.currentUrl);
                if(ctrl.checkIfBookmarked(ctrl.currentUrl)){
                    ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmarkselected.png"));
                }
                else{
                    ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmark.png"));
                }
                ctrl.EnableDisablebtn();
            }
        }
        else if(ctrl.historyGui != null && e.getSource().equals(ctrl.historyGui.list)){
            if(e.getValueIsAdjusting()){
                String temp = ctrl.historyGui.list.getSelectedValue().toString();
                StringTokenizer tk = new StringTokenizer(temp, " - ");
                String URL = tk.nextToken();
                ctrl.loadHtmlPage(URL);
                ctrl.backTrack.add(ctrl.currentUrl);
                ctrl.currentUrl = URL;
                if(ctrl.checkIfBookmarked(ctrl.currentUrl)){
                ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmarkselected.png"));
                }
                else{
                    ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmark.png"));
                }
                ctrl.EnableDisablebtn();
            }
        }
    }    
}
