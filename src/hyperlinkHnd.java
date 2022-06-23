
import javax.swing.ImageIcon;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class hyperlinkHnd implements HyperlinkListener{
    controller ctrl;
    public hyperlinkHnd(controller c){
        ctrl = c;
    }
    @Override
    public void hyperlinkUpdate(HyperlinkEvent e) {
        if(e.getEventType()==HyperlinkEvent.EventType.ACTIVATED){
            String URL = e.getURL().toString();
            ctrl.loadHtmlPage(URL);
            ctrl.backTrack.push(ctrl.currentUrl);
            ctrl.currentUrl = URL;
            ctrl.EnableDisablebtn();
            ctrl.writeHistory(ctrl.currentUrl);
            if(ctrl.checkIfBookmarked(ctrl.currentUrl)){
                ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmarkselected.png"));
            }
            else{
                ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmark.png"));
            }
        }
    }
    
}
