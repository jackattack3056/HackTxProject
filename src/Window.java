import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Window extends JPanel {
	private BufferedImage back;
	
	public Window() {
		back = null;
		
	}
	
	public void paint(Graphics g) {
        Graphics2D twoDgraph = (Graphics2D) g;
        if (back==null) {
            back = (BufferedImage) (createImage(getWidth(), getHeight()));
        }

        Graphics g2d = back.createGraphics();
        
        // Color cyan = new Color(45,245,245);
        // g2d.setColor(Color.BLACK);
        // g2d.setFont(new Font ("Calibri", Font.BOLD, 30));;
        // g2d.drawString("testing",  300, 300);
        
        twoDgraph.drawImage(back, 0, 0, null);
	
	}
}

