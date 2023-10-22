import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.text.JTextComponent;
import javax.swing.filechooser.FileFilter;

public class GUI implements MouseListener {
	
	private JDialog d;
	private String fromLanguage;
	private String toLanguage;
	private File uploadedFile;
	private ArrayList<String> highlightedWords;
	private JTextArea textBox;
	
	public static void main(String[] args) throws IOException {
		new GUI();
	}
	
    public GUI() throws IOException {
    	try { 
    	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
    	
    	fromLanguage = "English";
    	toLanguage = "English";
    	highlightedWords = new ArrayList<String>();
    	
    	// creating instance of JFrame
        JFrame frame = new JFrame(); 
        
        //popUpWelcome(frame);
        
        getFromLanguage(frame);
        getToLanguage(frame);
        
        // big text box
        textBox = new JTextArea();
        textBox.setBounds(50, 110, 575, 475);
        textBox.setLineWrap(true);
        textBox.setWrapStyleWord(true);
        Font font = new Font("Dialog", Font.PLAIN, 12);
        textBox.setFont(font);
        JScrollPane scroll = new JScrollPane(textBox);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.add(textBox);
        frame.add(textBox);
        textBox.addMouseListener(this);
        
        uploadFileSetUp(frame, textBox);
        
        // Clear button
        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(545, 590, 80, 20); // x axis, y axis, width, height
        clearButton.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {       
            	textBox.setText(null);
            }  
        });
        frame.add(clearButton);
        
        // side panel for definitions and highlighted words
        JPanel panel = new JPanel();
        panel.setBounds(650, 30, 575, 570);    
        panel.setBackground(Color.gray);
        //frame.add(panel);
        
        JLabel wrds = new JLabel("Words Highlighted:");
        wrds.setBounds(400, 30, 100, 20);
        wrds.setVisible(true);
        panel.add(wrds);
        frame.add(panel);
        
        frame.setSize(1275, 675); // 1275 width and 675 height
        frame.setLayout(null); // using no layout managers
        frame.setVisible(true); // making the frame visible
    }
    
    // pop-up window
    public void popUpWelcome(JFrame frame) {
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
    
    public void getFromLanguage(JFrame frame) {
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
    
    public void getToLanguage(JFrame frame) {
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
    
    public void uploadFileSetUp(JFrame frame, JTextArea text) {
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
    
    public void mouseClicked(MouseEvent e) {}
    
    public void mouseEntered(MouseEvent e) {}
    
    public void mouseExited(MouseEvent e) {}  
    
    public void mousePressed(MouseEvent e) {} 
    
    public void mouseReleased(MouseEvent e) {
    	if (textBox.getSelectedText() != null) { // See if they selected something 
    		String s = textBox.getSelectedText();
    		highlightWords(s);
        }
    }
    
    public void highlightWords(String s) {
        //removeHighlights(textBox);

        try
        {
            Highlighter hilite = textBox.getHighlighter();
            Document doc = textBox.getDocument();
            String text = doc.getText(0, doc.getLength());
            int pos = 0;

            // Search for pattern
            // see I have updated now its not case sensitive 
            while ((pos = text.toUpperCase().indexOf(s.toUpperCase(), pos)) >= 0)
            {
                // Create highlighter using private painter and apply around pattern
                hilite.addHighlight(pos, pos+s.length(), myHighlightPainter);
                pos += s.length();
            }
        } catch (BadLocationException e) {
        }
    }
    
    // Removes only our private highlights
    public void removeHighlights(JTextComponent textComp)
    {
        Highlighter hilite = textComp.getHighlighter();
        Highlighter.Highlight[] hilites = hilite.getHighlights();
        for (int i=0; i<hilites.length; i++)
        {
            if (hilites[i].getPainter() instanceof MyHighlightPainter)
            {
                hilite.removeHighlight(hilites[i]);
            }
        }
    }
    
    // An instance of the private subclass of the default highlight painter
    Highlighter.HighlightPainter myHighlightPainter = new MyHighlightPainter(Color.yellow);

    // A private subclass of the default highlight painter
    class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter
    {
        public MyHighlightPainter(Color color)
        {
            super(color);
        }
    }
}