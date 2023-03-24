import javax.swing.JFrame;
//importing package
/**
 * Garima Dhakal 
 * Section: C
 */
public class Converter {
	 
    public static void main(String[] args) {
    	//Creating an object
        JFrame frame = new JFrame("Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        MainPanel panel = new MainPanel();
        
        frame.setJMenuBar(panel.setupMenu());
    
        frame.getContentPane().add(panel); 
        frame.setLocation(350,300);//location in the screen
        frame.pack();
        frame.setVisible(true); //visibility is false by default
    }
    
}

