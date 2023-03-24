import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.*;
//Importing all the packages.

@SuppressWarnings("serial")
public class MainPanel extends JPanel 
{
	// list of conversion
	private final static String[] list = { "Inches/Centimeter", "Pounds/Kilogram", "Degree/Radians", "Acres/Hectares", "Miles/Kilometers", "Yards/Meters", "Celsius/Fahrenheit"};
	private JTextField textField;
	private JLabel field1, field2;
	private JCheckBox Reverse;
	private JComboBox<String> ComboBox;
	private int count=0;
	JMenuBar setupMenu() 
	{

		//Creating MenuBar where menu items are kept from where files can be opened
		JMenuBar MenuBar = new JMenuBar();
		JMenu FileMenu = new JMenu("File");
		JMenu HelpMenu = new JMenu("Help");
		
		
		//Adding menufiles to menu bar
		MenuBar.add(FileMenu);
		MenuBar.add(HelpMenu);
		

		
		//Creating MenuItems
		JMenuItem NewFile = new JMenuItem("New");
		JMenuItem OpenFile = new JMenuItem("Open");
		JMenuItem SaveFile = new JMenuItem("Save");
	    JMenuItem ExitFile = new JMenuItem("Exit");
	    JMenuItem HelpAbout = new JMenuItem("About");
	    

	    //Creating Tool tips for menu Item
	    NewFile.setToolTipText("Creates new file");
	    OpenFile.setToolTipText("Opens existing file");
        SaveFile.setToolTipText("Saves numeric data");
        ExitFile.setToolTipText("EXITs the program");
        HelpAbout.setToolTipText("Provides Information");
        
        

        //Adding MenuItems to Menu
        FileMenu.add(NewFile);
		FileMenu.add(OpenFile);
		FileMenu.add(SaveFile);
		FileMenu.add(ExitFile);
		
		HelpMenu.add(HelpAbout);
		
		//Adding Icon and Shortcut Keys
		NewFile.setIcon(new ImageIcon("src/newfile.png"));
		NewFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));

		OpenFile.setIcon(new ImageIcon("src/open.png"));
		OpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));

		SaveFile.setIcon(new ImageIcon("src/save.png"));
		SaveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		
		ExitFile.setIcon(new ImageIcon("src/exit.png"));
		ExitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));

		HelpAbout.setIcon(new ImageIcon("src/about.png"));
		HelpAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));

						
		//when the about menu is clicked the following Message will be appeared
		HelpAbout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				{
					JOptionPane.showMessageDialog(null, "This program is created by Garima Dhakal (77261088). "
						+ " \n This program is to convert numeric values as inserted by the user. "
						+"\n All Right Reserved.","About Us",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		//To exit out when clicked exit button
		ExitFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a)
			{
				System.exit(0);
			}
		});
		return MenuBar;
	}

	MainPanel()
	{
		ActionListener Listener = new ConvertListener(); 
		JLabel name= new JLabel("Conversion");
		ComboBox = new JComboBox<String>(list);
		ComboBox.addActionListener(Listener); //convert values when option changed

		JLabel input = new JLabel("Enter value:"); // adding string to the panel

		JButton convert = new JButton("Convert");
		convert.addActionListener(Listener); // convert values when pressed	
		
		field1 = new JLabel("0");
		textField = new JTextField(5);
		textField.setText("0");
		textField.addActionListener(Listener);
		
		//Reverse Conversion of given data
		Reverse = new JCheckBox("Reverse Conversion");
		Reverse.addActionListener(Listener);
		Reverse.setToolTipText("Reverse all Inputs");
		
		add(name);
		
		add(ComboBox);
		add(input);
		add(textField);
		add(convert);
		add(field1);
		add(Reverse);
		
		//To set the size and the background color
		setPreferredSize(new Dimension(800, 300));
		setBackground(Color.LIGHT_GRAY);
		
		//To clear the input by the user
		JButton buttonClear=new JButton("Clear");
		buttonClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent b) {
			field1.setText("0");
			textField.setText("");
			 if(b.getSource()==buttonClear) {
         		textField.setText(null);
         		field1.setText(null);
		           count=0;
		           field2.setText("Conversion count: "+count);
		            }
			}
		});
		add(buttonClear);
		buttonClear.setToolTipText("Clears everything.");
		convert.setToolTipText("Converts the entered input");
		field2=new JLabel("Conversion Count: "+ count);
		add(field2);  // starts new count
		
		
	}

	private class ConvertListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			String text = textField.getText().trim();
			//To ask the user to input the value if it hasn't been entered but asked to convert it
			if (text.isEmpty() == true) {					
				String Alert = "Please input the value";
				JOptionPane.showMessageDialog(null,Alert);
			}
			
			//To ask the user to input the valid number when invalid number is entered
			if (text.isEmpty() == false) {
				
			try {
				
				// the factor applied during the conversion
				
				
				if (text.isEmpty() == false) {
				
				double value = Double.parseDouble(text);

				// the factor applied during the conversion
				double factor =0;

				// the offset applied during the conversion.
				double offset = 0;

				// Setup the correct factor/offset values depending on required conversion
				switch (ComboBox.getSelectedIndex()) {
				
				case 0: // inches and cm
					if(Reverse.isSelected()) {
						factor=0.33978;
					}
					else
						factor=2.54;
					break;
				case 1: //pound and kg
					if(Reverse.isSelected()) {
						factor=2.20462;
					}
					else {
						factor=0.45359237;
					}
					break;
					
				case 2://degree and radian
					if(Reverse.isSelected()) {
						factor=57.29;
					}
					else {
					factor=0.017;
					}
					break;
				case 3:// acres and hectare
					if(Reverse.isSelected()) {
						factor=2.47105;
					}
					else {
					factor=0.40468564;
					}
					break;
					
				case 4:
					// km and mile
					if(Reverse.isSelected()) {
						factor=0.062;
					}
					else {
					factor=1.60;
					}
					break;
				case 5: //yards and meter
					if(Reverse.isSelected()) {
						factor=1.09;
					}
					else {
						factor=0.91;
						}
					break;
				
				case 6: //celsius and fahrenheit
					if(Reverse.isSelected()) {
						offset= -17.78;
						factor=0.5555556;
						
					}
					else {
						offset= 1.8;
						factor=32;
						}
					break;
					
				}
				NumberFormat numberformat = NumberFormat.getInstance();
				double result =(factor * value + offset);
				numberformat.setMaximumFractionDigits(2);
				String finalResult=numberformat.format(result);
				field1.setText(finalResult);
				 
				//To count the number of conversion
				count++;
				field2.setText("Conversion count: "+count);
				 
			}
			}
			catch (NumberFormatException exp) {
				String Alert = "Please enter the valid number";
				JOptionPane.showMessageDialog(null,  Alert); //catching the error for valid number
			}
				}
			}
		
			
		
	}
}



