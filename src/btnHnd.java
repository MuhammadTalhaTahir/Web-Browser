import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class btnHnd implements ActionListener{
    controller ctrl;
    public btnHnd(controller c){
        ctrl = c;
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Search")){
            String URL = ctrl.win.search.getText();
            URL = ctrl.makeURL(URL);                      // to come extent autocomplete https://
            ctrl.loadHtmlPage(URL);
            ctrl.backTrack.push(ctrl.currentUrl);
            ctrl.currentUrl = URL;
            ctrl.writeHistory(ctrl.currentUrl);
            ctrl.EnableDisablebtn();   // to enablediable back and forward btn
            if(ctrl.checkIfBookmarked(ctrl.currentUrl)){                        //checking if bookmark exist to change icon
                ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmarkselected.png"));
            }
            else{
                ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmark.png"));
            }
            
        }
        else if(e.getActionCommand().equals("back")){
            if(!ctrl.backTrack.isEmpty()){
                ctrl.forwardTrack.push(ctrl.currentUrl);
                ctrl.currentUrl = ctrl.backTrack.pop();
                ctrl.loadHtmlPage(ctrl.currentUrl);
                ctrl.writeHistory(ctrl.currentUrl);
                if(ctrl.checkIfBookmarked(ctrl.currentUrl)){
                ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmarkselected.png"));
                }
                else{
                    ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmark.png"));
                }
            }
            ctrl.EnableDisablebtn();
        }
        else if(e.getActionCommand().equals("forward")){
            if(!ctrl.forwardTrack.isEmpty()){
                ctrl.backTrack.push(ctrl.currentUrl);
                ctrl.currentUrl = ctrl.forwardTrack.pop();
                ctrl.loadHtmlPage(ctrl.currentUrl);
                ctrl.writeHistory(ctrl.currentUrl);
                if(ctrl.checkIfBookmarked(ctrl.currentUrl)){
                ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmarkselected.png"));
                }
                else{
                    ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmark.png"));
                }
            }
            ctrl.EnableDisablebtn();
        }
        else if(e.getActionCommand().equals("home")){
            if(ctrl.defaultPage != null || !ctrl.defaultPage.equals("")){
                ctrl.backTrack.push(ctrl.currentUrl);
                ctrl.currentUrl = ctrl.defaultPage;
                ctrl.loadHtmlPage(ctrl.currentUrl);
                ctrl.writeHistory(ctrl.currentUrl);
                if(ctrl.checkIfBookmarked(ctrl.currentUrl)){
                    ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmarkselected.png"));
                }
                else{
                    ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmark.png"));
                }
            }
            
        }
        else if(e.getActionCommand().equals("refresh")){
            ctrl.loadHtmlPage(ctrl.currentUrl);
        }
        else if(e.getActionCommand().equals("bookmark")){
            if(!ctrl.checkIfBookmarked(ctrl.currentUrl)){
                String name = JOptionPane.showInputDialog("Name");
                if(name != null && !name.equals(" ")){
                    ctrl.file.writeFile(ctrl.currentUrl + " - " + name, "dataFiles\\bookmarks.mybr");
                    ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmarkselected.png"));
                }
            }
            else{
                Vector<String> bookmarks = ctrl.file.readFile("dataFiles\\bookmarks.mybr");
                for(String i:bookmarks){
                    StringTokenizer tk = new StringTokenizer(i, " - ");
                    if(ctrl.currentUrl.equals(tk.nextToken())){
                        ctrl.file.removeAline(i, "dataFiles\\bookmarks.mybr");
                        ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmark.png"));
                        break;
                    }
                }
            }
            /*if(!ctrl.checkIfBookmarked(ctrl.currentUrl)){
                ctrl.file.writeFile(ctrl.currentUrl, "dataFiles\\bookmarks.mybr");
                ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmarkselected.png"));
            }
            else{
                ctrl.file.removeAline(ctrl.currentUrl, "dataFiles\\bookmarks.mybr");
                ctrl.win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmark.png"));
            }*/
        }
        else if(e.getActionCommand().equals("favourite")){
            ctrl.createBookmarkListGui(ctrl.file.readFile("dataFiles\\bookmarks.mybr"));
        }
        else if(e.getActionCommand().equals("sethome")){
            String URL = JOptionPane.showInputDialog("Enter URL for Default page");
            if(URL != null && !URL.equals("")){
                ctrl.file.writeFile(URL, "dataFiles\\parameterfile.mybr", false);
                ctrl.file.writeFile(ctrl.firewall, "dataFiles\\parameterfile.mybr");
                ctrl.defaultPage = URL;
            }
        }
        else if(e.getActionCommand().equals("history")){
            //ctrl.removeOldHistory();
            ctrl.createHistoryGui(ctrl.file.readFile("dataFiles\\history.mybr"));
        }
        else if(e.getActionCommand().equals("addword")){
            String word = JOptionPane.showInputDialog("Enter a word to add to firewall");
            if(word != null && !word.equals("")){
                ctrl.file.writeFile(word, "dataFiles\\firewall.mybr");
            }
        }
        else if(e.getActionCommand().equals("removeword")){
            String word = JOptionPane.showInputDialog("Enter a word to remove from firewall");
            if(word != null && !word.equals("")){
                ctrl.file.removeAline(word,"dataFiles\\firewall.mybr");
            }
        }
        else if(e.getActionCommand().equals("enable/disable")){
            this.firewallFunction();
        }
        else if(e.getActionCommand().equals("firewallbtn")){
            this.firewallFunction();
        }
        else if(e.getActionCommand().equals("searchword")){
            String wordToSearch = JOptionPane.showInputDialog("Enter the word to search");
            if(wordToSearch != null && !wordToSearch.equals(" ")){
                String pageData = ctrl.win.htmlView.getText();    
                boolean flag = true;
                int wordCount = 0;
                int tempCount = 0;
                for(int i=0; i<pageData.length(); i++){
                    if(pageData.charAt(i) == '<')flag = false;
                    if(flag){
                        if(pageData.charAt(i) == wordToSearch.charAt(0)){
                            i++;
                            tempCount = 1;
                            for(int j=1; j<wordToSearch.length();j++){
                                if(pageData.charAt(i) == wordToSearch.charAt(j)){
                                    i++;
                                    tempCount ++;
                                }
                            }
                            if(tempCount == wordToSearch.length()){
                                wordCount ++;
                            }
                        }
                    }
                    if(pageData.charAt(i) == '>')flag = true;
                }
                JOptionPane.showMessageDialog(null, wordToSearch+" appeard " +Integer.toString(wordCount)+" times.", "Word Count", JOptionPane.INFORMATION_MESSAGE);
                
            }
        }
        
    }
    private void firewallFunction(){
        if(ctrl.firewall.equals("enable")){
                ctrl.firewall = "disable";
        }
        else if (ctrl.firewall.equals("disable")){
            ctrl.firewall = "enable";
        }
        ctrl.file.writeFile(ctrl.defaultPage, "dataFiles\\parameterfile.mybr", false);
        ctrl.file.writeFile(ctrl.firewall, "dataFiles\\parameterfile.mybr");
        ctrl.loadFirewallIcon();
    }
}
