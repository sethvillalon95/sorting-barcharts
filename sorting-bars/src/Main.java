
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main extends JFrame {

    private Vis mainPanel;

    public Main() {

        JMenuBar mb = setupMenu();
        setJMenuBar(mb);

        mainPanel = new Vis();
        setContentPane(mainPanel);

        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Seth's Data Visualization");
        setVisible(true);
    }

    //select count(*) from derbyDB
    private int runSimpleCountQuery(String q) {
        try {
            Connection c = DriverManager.getConnection("jdbc:derby:sethFinal");
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(q);
            rs.next();
            int count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            System.out.println("could not connect to Derby!");
            System.out.println("could not connect to Derby!");
            System.out.println(e);
            System.err.println("  Error Code: " + e.getErrorCode());
            System.err.println("  Message:    " + e.getMessage());
            System.err.println("  Message:    " + e.getNextException());

            
            return 0;
        }
    }

    private Map<String, Double> performTwoColumnQuery(String q) {
        Map<String, Double> results = new HashMap<>();
        try {
            Connection c = DriverManager.getConnection("jdbc:derby:sethFinal");
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(q);
            while (rs.next()) {
                double num = rs.getDouble(1);
                String label = rs.getString(2);
                results.put(label, num);
            }
        } catch (SQLException e) {
        	
            System.out.println("could not connect to Derby in the method!");
            System.out.println(e);
            System.err.println("  Error Code: " + e.getErrorCode());
            System.err.println("  Message:    " + e.getMessage());
            System.err.println("  Message:    " + e.getNextException());

        }
        return results;
    }

    private JMenuBar setupMenu() {
        //instantiate menubar, menus, and menu options
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem item1 = new JMenuItem("Item 1");
        JMenuItem item2 = new JMenuItem("# Students per Major");
        JMenuItem item3 = new JMenuItem("# Students per area");
        JMenuItem item4 = new JMenuItem("AVG GPA per Major");
        JMenuItem item5 = new JMenuItem("AVG credits per year");
        JMenuItem item6 = new JMenuItem("# Students per GPA (not working yet)");
        JMenuItem item7 = new JMenuItem("Your choice");
        
        JMenu chartMenu = new JMenu("Chart Type");
        JMenuItem bar = new JMenuItem("Bar Chart");
        JMenuItem lineChart = new JMenuItem("Line Chart");






        //setup action listeners
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Just clicked menu item 1");
                int gilmo = runSimpleCountQuery("SELECT COUNT(*) FROM cis2019");
                System.out.println("I found " + gilmo + " rows in the table.");
                mainPanel.setText("I found " + gilmo + " rows in the table.");
            }
        });
        
        //# Students per Major
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Just clicked menu item 2");
            	mainPanel.clearMap();
                var sqlData = performTwoColumnQuery("select count(*), major from cis2019 group by major");
//                for (var k : sqlData.keySet()) {
//                    double num = sqlData.get(k);
//                    System.out.println(k + " : " + num);
//
//                }
                
                mainPanel.setData(sqlData);

            }
        });
        
        //# Students per area
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainPanel.clearMap();
                System.out.println("Just clicked menu item 3");
                var sqlData = performTwoColumnQuery("select count(*), home from cis2019 group by home");
                mainPanel.setData(sqlData);
            }
        });
        
        
        //AVG GPA per Major
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainPanel.clearMap();
                System.out.println("*******************Just clicked menu item 4***************");
                var sqlData = performTwoColumnQuery("select avg(gpa), major from cis2019 group by major");
                mainPanel.setData(sqlData);
                for (var k : sqlData.keySet()) {
                  double num = sqlData.get(k);
                  System.out.println(k + " : " + num);

              }

            }
        });
        
        //AVG credits per year
        item5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainPanel.clearMap();

                System.out.println("Just clicked menu item 5");
                var sqlData = performTwoColumnQuery("select avg(credits_attempted), gradyear from cis2019 group by gradyear");
                mainPanel.setData(sqlData);

            }
        });
        
        
        //# Students per GPA
        item6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainPanel.clearMap();

                System.out.println("Just clicked menu item 6");
                var sqlData = performTwoColumnQuery("select count(*), gpa_bins from cis2019 group by gpa_bins");
                mainPanel.setData(sqlData);

            }
        });
        
        
        //# Students per GPA
        item7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainPanel.clearMap();

                System.out.println("Just clicked menu item 7");
                var sqlData = performTwoColumnQuery("select avg(credits_attempted), home from cis2019 group by home");
                mainPanel.setData(sqlData);

            }
        });
        
        // toggle to bar charts 
        bar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainPanel.isBar=true;
            	repaint();
            }
        });
        
        lineChart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainPanel.isBar=false;
            	repaint();
            }
        });
        //now hook them all together
        fileMenu.add(item1);
        fileMenu.add(item2);
        fileMenu.add(item3);
        fileMenu.add(item4);
        fileMenu.add(item5);
        fileMenu.add(item6);
        fileMenu.add(item7);
        
        chartMenu.add(bar);
        chartMenu.add(lineChart);


        menuBar.add(fileMenu);
        menuBar.add(chartMenu);

        return menuBar;
    }
//    
//    private double maxNumber(ArrayList<Double> myList) {
//    	double largest=0;
//    	Collections.sort(myList);
//    	largest =(double) myList.get(myList.size()-1);
//    	return largest;
//    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}