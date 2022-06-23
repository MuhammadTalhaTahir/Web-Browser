import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;

import java.awt.GridLayout;

import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class mainWindow {
    JFrame window;
    JMenuBar menubar;
    JMenuItem i1, i2, i3, i4,add,remove,enable;
    JMenu menu,firewall, help;
    JPanel menuPanel;
    JPanel htmlPanel;
    Dimension winsize;
    Dimension menuBtnSize;
    JTextField search;
    JButton searchBtn, backbtn,forwardbtn,homebtn,refreshbtn,bookmarkbtn,firewallbtn;
    GridBagConstraints cons;
    JEditorPane htmlView;
    public mainWindow(){
        winsize = Toolkit.getDefaultToolkit().getScreenSize();
        menuBtnSize = new Dimension(40, 28);
        cons = new GridBagConstraints();
        cons.weightx = 1;
        cons.weighty = 1;
        cons.fill = GridBagConstraints.BOTH;
        this.init();
    }
    public void init(){
        window = new JFrame();
        window.setTitle("Browser");
        
        
        //adding above two layers menubar and buttons.
        this.addMenuBar();
        menuPanel = new JPanel();
        menuPanel.setBackground(new Color(245,245,245));
        menuPanel.setBorder(BorderFactory.createMatteBorder(2,0,0,0, Color.BLACK));
        menuPanel.setLayout(new FlowLayout());
        this.addMenuButtons();
        window.add(menuPanel, BorderLayout.CENTER);
        //adding hmtl viewer
        htmlPanel = new JPanel();
        htmlPanel.setLayout(new GridLayout());
        this.addHtmlViewer();
        window.add(htmlPanel, BorderLayout.SOUTH);
        

        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void addMenuBar(){
        menubar = new JMenuBar();
        
        menubar.setBackground(Color.white);
        menubar.setBorder(null);
        menu = new JMenu("File");
        menu.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        firewall = new JMenu("FireWall");
        firewall.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        help = new JMenu("About Us");
        help.setActionCommand("aboutus");
        help.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        
        i1 = new JMenuItem("History");
        i1.setActionCommand("history");
        i1.setBackground(Color.WHITE);
        i1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        
        i2 = new JMenuItem("Search");
        i2.setBackground(Color.WHITE);
        i2.setActionCommand("searchword");
        i2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        
        i3 = new JMenuItem("Favourites List");
        i3.setActionCommand("favourite");
        i3.setBackground(Color.WHITE);
        i3.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        
        i4 = new JMenuItem("Set Home Page");
        i4.setBackground(Color.WHITE);
        i4.setActionCommand("sethome");
        i4.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        
        add = new JMenuItem("Add Word");
        add.setActionCommand("addword");
        remove = new JMenuItem("Remove Word");
        remove.setActionCommand("removeword");
        enable = new JMenuItem("Enable/Disable");
        enable.setActionCommand("enable/disable");
        add.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        add.setBackground(Color.WHITE);
        remove.setBackground(Color.WHITE);
        enable.setBackground(Color.WHITE);
        remove.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        enable.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        firewall.add(add);
        firewall.add(remove);
        firewall.add(enable);
        menu.add(i1);
        menu.add(i2);
        menu.add(i3);
        menu.add(i4);
        //menu.add(firewall);
        menubar.add(menu);
        menubar.add(firewall);
        menubar.add(help);
        window.add(menubar, BorderLayout.NORTH);
    }
    private void addSearch(){
        search = new JTextField();
        int x = winsize.width-100;
        search.setPreferredSize(new Dimension(x-250, 28));
        search.setFont (new Font(Font.MONOSPACED, Font.BOLD,12));
        //search.setPreferredSize(new Dimension(300, 40));
        search.setText("https://");
        searchBtn = new JButton(new ImageIcon("ico\\search.png"));
        searchBtn.setPreferredSize(menuBtnSize);
        searchBtn.setActionCommand("Search");
        searchBtn.setBackground(new Color(245, 245, 245));
        search.setBorder(new LineBorder(Color.BLACK, 1));
        searchBtn.setBorder(null);
        menuPanel.add(search);
        menuPanel.add(searchBtn);

    }
    private void addMenuButtons(){
        backbtn = new JButton(new ImageIcon("ico\\back.png"));
        Color btnColor = new Color(245, 245, 245);
        backbtn.setActionCommand("back");
        backbtn.setBorder(null);
        backbtn.setBackground(btnColor);
        backbtn.setPreferredSize(menuBtnSize);
        forwardbtn = new JButton(new ImageIcon("ico\\forward.png"));
        forwardbtn.setActionCommand("forward");
        forwardbtn.setBackground(btnColor);
        forwardbtn.setBorder(null);
        forwardbtn.setPreferredSize(menuBtnSize);
        homebtn = new JButton(new ImageIcon("ico\\home.png"));
        homebtn.setActionCommand("home");
        homebtn.setBackground(btnColor);
        homebtn.setBorder(null);
        homebtn.setPreferredSize(menuBtnSize);
        refreshbtn = new JButton(new ImageIcon("ico\\refresh.png"));
        refreshbtn.setActionCommand("refresh");
        refreshbtn.setBackground(btnColor);
        refreshbtn.setBorder(null);
        refreshbtn.setPreferredSize(menuBtnSize);
        bookmarkbtn = new JButton(new ImageIcon("ico\\bookmark.png"));
        bookmarkbtn.setActionCommand("bookmark");
        bookmarkbtn.setBackground(btnColor);
        bookmarkbtn.setBorder(null);
        bookmarkbtn.setPreferredSize(menuBtnSize);
        firewallbtn = new JButton(new ImageIcon("ico\\firewalldisable.png"));
        firewallbtn.setActionCommand("firewallbtn");
        firewallbtn.setBackground(btnColor);
        firewallbtn.setBorder(null);
        firewallbtn.setPreferredSize(menuBtnSize);
        menuPanel.add(backbtn);
        menuPanel.add(forwardbtn);
        menuPanel.add(homebtn);
        menuPanel.add(refreshbtn);
        this.addSearch();
        menuPanel.add(bookmarkbtn);
        menuPanel.add(firewallbtn);

        
    }
    private void addHtmlViewer(){
        htmlView = new JEditorPane();
        htmlView.setEditable(false);
        htmlPanel.setBorder(null);
        htmlPanel.add(htmlView);
        htmlPanel.setPreferredSize(new Dimension(winsize.width-140, winsize.height-150));
        htmlPanel.add(new JScrollPane(htmlView));
    }
    
}
