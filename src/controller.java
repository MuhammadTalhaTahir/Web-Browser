
import java.awt.Toolkit;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;
import java.util.Vector;
import javax.swing.ImageIcon;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class controller {
    mainWindow win;
    hyperlinkHnd hHandle;
    btnHnd btnhnd;
    listHnd listhnd;
    menuHnd menuhnd;
    //just to implements back tracking
    Stack<String> backTrack; 
    Stack<String> forwardTrack; 
    String currentUrl;
    String defaultPage;
    String firewall;
    fileReaderWriter file;
    //bookmark window
    bookmarkListGui bookmarklist;
    //history list Gui
    historyListGui historyGui;
    public controller(){
        win = new mainWindow();
        file = new fileReaderWriter();
        hHandle = new hyperlinkHnd(this);
        btnhnd = new btnHnd(this);
        listhnd = new listHnd(this);
        menuhnd = new menuHnd();
        backTrack = new Stack<String>();
        forwardTrack = new Stack<String>();
        this.loadParameters();
        // adding listeners
        this.addListeners();
        //this will act as load default site.
        this.loadDefaultSite();
    }
    public void loadHtmlPage(String URL){
        try{
            if(this.passesThroughFirewall(URL)){
                win.htmlView.setPage(URL);
                win.search.setText(URL);
            }
            else{
                JOptionPane.showMessageDialog(null, "[INFO] Firewall is blocking this Site.\nDisable firewall to procced.","Firewall", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(IOException a){
            JOptionPane.showMessageDialog(null,"[ERROR] Page not found","Bad url",JOptionPane.ERROR_MESSAGE);
        }
    }
    public String makeURL(String URL){
        if(URL.length() >= 8){
            if(!URL.substring(0, 8).equals("https://")){
                String result = "https://" + URL;
                return result;
            }
            return URL;
        }
        return URL;
    }
    public void loadParameters(){
        Vector<String> temp = file.readFile("dataFiles\\parameterfile.mybr");
        this.defaultPage = temp.elementAt(0);
        this.firewall = temp.elementAt(1);
        this.removeOldHistory();
    }
    public void loadFirewallIcon(){
        if(this.firewall.equals("enable")){
            win.firewallbtn.setIcon(new ImageIcon("ico\\firewallenable.png"));
        }
        else if(this.firewall.equals("disable")){
            win.firewallbtn.setIcon(new ImageIcon("ico\\firewalldisable.png"));
        }
    }
    public void loadDefaultSite(){
        this.loadFirewallIcon();
        currentUrl = defaultPage;
        this.loadHtmlPage(currentUrl);
        win.backbtn.setEnabled(false);
        win.forwardbtn.setEnabled(false);
        if(checkIfBookmarked(currentUrl)){
            win.bookmarkbtn.setIcon(new ImageIcon("ico\\bookmarkselected.png"));
        }
    }
    public void EnableDisablebtn(){
        if(backTrack.size() > 0)win.backbtn.setEnabled(true);
        else win.backbtn.setEnabled(false);
        if(forwardTrack.size() > 0)win.forwardbtn.setEnabled(true);
        else win.forwardbtn.setEnabled(false);
    }
    private void addListeners(){
        win.htmlView.addHyperlinkListener(hHandle);
        win.searchBtn.addActionListener(btnhnd);
        win.backbtn.addActionListener(btnhnd);
        win.forwardbtn.addActionListener(btnhnd);
        win.homebtn.addActionListener(btnhnd);
        win.refreshbtn.addActionListener(btnhnd);
        win.bookmarkbtn.addActionListener(btnhnd);
        win.i3.addActionListener(btnhnd);
        win.i4.addActionListener(btnhnd);
        win.i2.addActionListener(btnhnd);
        win.i1.addActionListener(btnhnd);
        win.add.addActionListener(btnhnd);
        win.remove.addActionListener(btnhnd);
        win.enable.addActionListener(btnhnd);
        win.firewallbtn.addActionListener(btnhnd);
        win.help.addMenuListener(menuhnd);
        
    }
    public boolean checkIfBookmarked(String URL){
        Vector<String> temp = this.file.readFile("dataFiles\\bookmarks.mybr");
        for(int i=0;i<temp.size();i++){
            StringTokenizer tk = new StringTokenizer(temp.elementAt(i), " - ");
            if(URL.equals(tk.nextToken())) return true;
        }
        return false;
    }
    public void createBookmarkListGui(Vector<String> data){
        this.bookmarklist = new bookmarkListGui(data);
        bookmarklist.list.addListSelectionListener(listhnd);
    }
    public void createHistoryGui(Vector<String> data){
        this.historyGui = new historyListGui(data);
        historyGui.list.addListSelectionListener(listhnd);
    }
    public void writeHistory(String URL){
        String data = URL;
        data += " - ";
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss");
        data += f.format(new Date());
        file.writeFile(data, "dataFiles\\history.mybr");
    }
    public boolean passesThroughFirewall(String URL){
        if(firewall.equals("disable"))return true;
        else{
           Vector<String> firewallWords = file.readFile("dataFiles//firewall.mybr");
           for(String i: firewallWords){
               if(URL.indexOf(i) > -1) return false;
           }
           return true;
        }
    }
    private void removeOldHistory(){
        
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        String currentDate = f.format(new Date());
        Vector<String> previoushistory = this.file.readFile("dataFiles\\lastdays.mybr");
        boolean present = false;
        for(String i:previoushistory){
            if(i.equals(currentDate)){
                present = true;
                break;
            }
        }
        if(!present){
            if(previoushistory.size() < 3){
                file.writeFile(currentDate,"dataFiles\\lastdays.mybr", true);
            }
            else{
                file.removeAline(previoushistory.elementAt(0),"dataFiles\\lastdays.mybr");
                file.writeFile(currentDate,"dataFiles\\lastdays.mybr", true);
            }
        }
        this.oldDate();
    }  
    private void oldDate(){
        Vector<String> history = file.readFile("dataFiles\\history.mybr");
        Vector<String> lastdays = file.readFile("dataFiles\\lastdays.mybr");
        boolean flag = true;
        for(String i:history){
            StringTokenizer tk = new StringTokenizer(i, " - ");
            String dateAndTime = tk.nextToken();
            dateAndTime = tk.nextToken();
            StringTokenizer tk2 = new StringTokenizer(dateAndTime, ",");
            String date = tk2.nextToken();
            for(String j:lastdays){
                //System.out.println(date);
                if(date.equals(j)){
                    flag = false;
                }
            }
            if(flag){
                file.removeAline(i,"dataFiles\\history.mybr");
            }
            else flag = true;
        }
    }    
}
