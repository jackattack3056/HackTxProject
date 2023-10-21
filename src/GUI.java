import java.io.*;
import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;

public class GUI {
	
    // Main driver method
    public static void main(String[] args) {
    	try { 
    	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
        JFrame frame = new JFrame(); // creating instance of JFrame
 
        JLabel l1 = new JLabel("Select Wanted Language:");
        
        l1.setBounds(50, 30, 200, 15);
        frame.add(l1);
  
        final JLabel label = new JLabel();          
        label.setSize(400, 100);  
        label.setBounds(50, 85, 300, 20);
        JButton b = new JButton("Choose");  
        b.setBounds(200, 50, 75, 20);  
        String languages[] = {"Spanish","French","Chinese", "Japanese", "Greek", "Austrian",
        		"Finnish", "Swahili", "Russian", "Portugese", "Romanian"};        
        final JComboBox cb = new JComboBox(languages);    
        cb.setBounds(50, 50, 90, 20);    
        frame.add(cb); frame.add(label); frame.add(b); 
        
        JTextArea text = new JTextArea("Paste Text Here");
        text.setBounds(50, 110, 575, 485);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        frame.add(text);
        
        b.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {       
            	String data = "Language Selected: "   + cb.getItemAt(cb.getSelectedIndex());  
            	label.setText(data); 
            }  
        });
        
        JButton button = new JButton("Done"); // creating instance of JButton
        button.setBounds(545, 600, 80, 20); // x axis, y axis, width, height
        frame.add(button); // adding button in JFrame
 
        frame.setSize(1275, 675); // 1275 width and 675 height
        frame.setLayout(null); // using no layout managers
        frame.setVisible(true); // making the frame visible
    }
    
}
