
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
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
    String filename = "test.txt";
    ArrayList<Double> nums;

    public Main() {

        JMenuBar mb = setupMenu();
        setJMenuBar(mb);

        mainPanel = new Vis();
        setContentPane(mainPanel);

        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Seth's Data Visualization");
        setVisible(true);
        getData(filename);
        mainPanel.setData(nums);
    }
    
    private void getData(String fname) {
    	try{

            File f = new File(fname);
            Scanner s = new Scanner(f);
            nums = new ArrayList<>();
            if(!nums.isEmpty()) {
            	nums.clear();
            }
            while(s.hasNextLine()){
                double number = s.nextDouble();
                nums.add(number);
            }   
            s.close();
            int numsSize = nums.size();
//            for (var t: nums) {
//            	say(t);
//            }
    	}catch(FileNotFoundException fe){
            say("error is :" +fe);
        }

    		
    }
    public static void say(Object o){
        System.out.println(o);
    }

    //select count(*) from derbyDB


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
        JMenu fileMenu = new JMenu("Sorting Algorithms");
        JMenuItem item1 = new JMenuItem("Heap Sort");
        JMenuItem item2 = new JMenuItem("Bubble Sort");
        JMenuItem item3 = new JMenuItem("Quick Sort");
        JMenuItem item4 = new JMenuItem("Sort");
        JMenuItem item5 = new JMenuItem("Sort");
        JMenuItem item6 = new JMenuItem("Sort");
        JMenuItem item7 = new JMenuItem("Sort");
        
        JMenu chartMenu = new JMenu("Chart Type");
        JMenuItem bar = new JMenuItem("Bar Chart");
        JMenuItem lineChart = new JMenuItem("Line Chart");






        //setup action listeners
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Just clicked menu item 1");
                System.out.println("Just clicked menu item 2");
            	mainPanel.clearMap();
            	
            	

            }
        });
        
        //Bubble sort
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Just clicked menu item 2");
//            	mainPanel.clearMap();
//            	getData(filename);
//            	mainPanel.setData(nums);
//            	new Timer(1000).start();
            	mainPanel.bubbleSort();

                
//                mainPanel.setData(sqlData);

            }
        });
        
        //# Students per area
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainPanel.clearMap();
                System.out.println("Just clicked menu item 3");
//                mainPanel.setData(sqlData);
            }
        });
        
        
        //AVG GPA per Major
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainPanel.clearMap();
                System.out.println("*******************Just clicked menu item 4***************");
//                mainPanel.setData(sqlData);


            }
        });
        
        //AVG credits per year
        item5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainPanel.clearMap();

                System.out.println("Just clicked menu item 5");
//                mainPanel.setData(sqlData);

            }
        });
        
        
        //# Students per GPA
        item6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainPanel.clearMap();

                System.out.println("Just clicked menu item 6");
//                mainPanel.setData(sqlData);

            }
        });
        
        
        //# Students per GPA
        item7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainPanel.clearMap();

                System.out.println("Just clicked menu item 7");
//                mainPanel.setData(sqlData);

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