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
        button.setBounds(150, 200, 220, 50); // x axis, y axis, width, height
 
        frame.add(button); // adding button in JFrame
 
        frame.setSize(1275, 675); // 400 width and 500 height
        frame.setLayout(null); // using no layout managers
        frame.setVisible(true); // making the frame visible
    }
}
