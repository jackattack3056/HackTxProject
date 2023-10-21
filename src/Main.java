import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main extends JFrame { 

	public static void main(String[] args) {
		new Main();
	}
	
	public Main () {
		super("HackTX 2023");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	setSize(screenSize.width, screenSize.height);

		Window window = new Window();
		((Component) window).setFocusable(true);
		setBackground(Color.GRAY);
		getContentPane().add(window);

		// GridLayout layout = new GridLayout(1, 2);
		// setLayout(layout);

		// ST

		JFileChooser fc = new JFileChooser();

		int num = screenSize.width / 2;
        JTextArea tarea = new JTextArea(num, screenSize.height);
        JButton readButton = new JButton("OPEN FILE");
		readButton.setBounds(1, 1, 50, 50);
        readButton.addActionListener(ev -> {
        int returnVal = fc.showOpenDialog(window);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
            BufferedReader input = new BufferedReader(new InputStreamReader(
                new FileInputStream(file)));
            tarea.read(input, "READING FILE :-)");
			tarea.setLineWrap(true);
			tarea.setWrapStyleWord(true);
            } catch (Exception e) {
            e.printStackTrace();
            }
        } else {
            System.out.println("Operation is CANCELLED :(");
        }
        });

		// END
		JScrollPane scrollPane = new JScrollPane(tarea);
		getContentPane().add(scrollPane);
    	getContentPane().add(readButton, BorderLayout.PAGE_END);
    	pack();
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
