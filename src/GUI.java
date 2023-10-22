import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.BorderLayout;  
import java.awt.Color;  
import java.awt.Dimension;  
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JScrollPane;  
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView; 
import javax.swing.filechooser.FileFilter;

public class GUI {
	
	private static JDialog d;
	private static String fromLanguage;
	private static String toLanguage;
	private static File uploadedFile;
	private static ArrayList<String> highlightedWords;
	
    public static void main(String[] args) throws IOException {
    	try { 
    	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
    	
    	fromLanguage = "English";
    	toLanguage = "English";
    	ArrayList<String> highlightedWords = new ArrayList<String>();
    	
    	// creating instance of JFrame
        JFrame frame = new JFrame(); 
        
        //popUpWelcome(frame);
        
        getFromLanguage(frame);
        getToLanguage(frame);
        
        // big text box
        JTextArea text = new JTextArea();
        text.setBounds(50, 110, 575, 475);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(text);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.add(text);
        frame.add(text);
        
        uploadFileSetUp(frame, text);
        
        // Clear button
        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(545, 590, 80, 20); // x axis, y axis, width, height
        clearButton.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {       
            	text.setText(null);
            }  
        });
        frame.add(clearButton);
        
        // user highlighting words
        
        
        
        frame.setSize(1275, 675); // 1275 width and 675 height
        frame.setLayout(null); // using no layout managers
        frame.setVisible(true); // making the frame visible
    }
    
    // pop-up window
    public static void popUpWelcome(JFrame frame) {
    	d = new JDialog(frame, "Welcome to Translator App (name in progress)", true);  
        d.setLayout(new FlowLayout());  
        JButton but = new JButton("OK");  
        but.addActionListener(new ActionListener()  {  
            public void actionPerformed(ActionEvent e) {  
                d.setVisible(false);  
            }  
        });  
        d.add( new JLabel ("Welcome to this program!"));  
        d.add(but);   
        d.setSize(500, 500);
        d.setLocationRelativeTo(frame);
        d.setVisible(true);
    }
    
    public static void getFromLanguage(JFrame frame) {
    	// initial prompt
        JLabel prompt = new JLabel("Select Input Language:");
        prompt.setBounds(50, 30, 200, 15);
        frame.add(prompt);
        
        // The dropdown menu
        JButton b = new JButton("Set");
        b.setBounds(150, 50, 50, 20);  
        String languages[] = {"English", "Spanish","French","Chinese", "Japanese", "Greek", 
        		"Austrian", "Finnish", "Swahili", "Russian", "Portugese", "Romanian"};       
        final JComboBox cb = new JComboBox(languages);    
        cb.setBounds(50, 50, 90, 20);    
        frame.add(cb); frame.add(b); //frame.add(label);
        
        b.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {       
            	fromLanguage = "" + cb.getItemAt(cb.getSelectedIndex());
            }  
        });
    }
    
    public static void getToLanguage(JFrame frame) {
    	// initial prompt
        JLabel prompt = new JLabel("Select Translated Language:");
        prompt.setBounds(450, 30, 200, 15);
        frame.add(prompt);
        
        // The dropdown menu
        JButton b = new JButton("Set");  
        b.setBounds(550, 50, 50, 20);  
        String languages[] = {"English", "Spanish","French","Chinese", "Japanese", "Greek", 
        		"Austrian", "Finnish", "Swahili", "Russian", "Portugese", "Romanian"};       
        final JComboBox cb = new JComboBox(languages);    
        cb.setBounds(450, 50, 90, 20);    
        frame.add(cb); frame.add(b); //.add(label);
        
        b.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {       
            	toLanguage = "" + cb.getItemAt(cb.getSelectedIndex());
            }  
        });
    }
    
    public static void uploadFileSetUp(JFrame frame, JTextArea text) {
        JLabel filePrompt = new JLabel("Type in selected language or upload a .txt file:");
        filePrompt.setBounds(50, 88, 500, 15);
        frame.add(filePrompt);
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileFilter() {
            public String getDescription() {
                return "T Documents (*.txt)";
            }
         
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".txt");
                }
            }
        });
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        JButton fileButton = new JButton("Upload");
        fileButton.setBounds(280, 85, 80, 20); // x axis, y axis, width, height
        frame.add(fileButton);
        fileButton.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {       
            	int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                	uploadedFile = fileChooser.getSelectedFile();
                	try (BufferedReader reader = new BufferedReader(new FileReader(new File("" + 
                			uploadedFile.getAbsolutePath())))) {
                        text.read(reader, "File");
                    } catch (IOException exp) {
                        exp.printStackTrace();
                    }
                }
            }  
        });
    }
    
    public static void something() {
    	
    }
}

public class D
