import java.io.*;
import javax.swing.*;

public class GUI {
	
    // Main driver method
    public static void main(String[] args) {
    	try { 
    	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
    	
        JFrame frame = new JFrame(); // creating instance of JFrame
 
        JButton button = new JButton("Click Here!!!!"); // creating instance of JButton
        button.setBounds(500, 500, 220, 50); // x axis, y axis, width, height
 
        frame.add(button); // adding button in JFrame
 
        frame.setSize(1275, 675); // 400 width and 500 height
        frame.setLayout(null); // using no layout managers
        frame.setVisible(true); // making the frame visible
        
        JLabel l1 = new JLabel("Select known Languages");
        
        l1.setBounds(100, 50, 120, 80);
        frame.add(l1);
 
        JCheckBox c2 = new JCheckBox("Hindi");
        c2.setBounds(100, 150, 50, 50);
        frame.add(c2);
        JCheckBox c3 = new JCheckBox("English");
        c3.setBounds(100, 200, 80, 50);
        frame.add(c3);
        JCheckBox c4 = new JCheckBox("marathi");
        c4.setBounds(100, 250, 80, 50);
        frame.add(c4);
 
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
