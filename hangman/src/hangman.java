import java.util.*;
import java.util.Timer;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*; 


public class hangman extends JFrame{

	private final int FRAME_HEIGHT = 800;
	private final int FRAME_WIDTH = 1400;
	
	private JTextPane used = new JTextPane();
	private JTextPane word = new JTextPane();
	
	private JButton[] letters = new JButton[] {
	 new JButton("A"),           
	 new JButton("B"),      
	 new JButton("C"), 
	 new JButton("D"),       
	 new JButton("E"),      
	 new JButton("F"),         
	 new JButton("G"),    
	 new JButton("H"),   
	 new JButton("I"),
	 new JButton("J"),
	 new JButton("K"),     
	 new JButton("L"),  
	 new JButton("M"),   
	 new JButton("N"),                  
	 new JButton("O"),   
	 new JButton("P"),         
	 new JButton("Q"), 
	 new JButton("R"),   
	 new JButton("S"),      
	 new JButton("T"),         
	 new JButton("U"),          
	 new JButton("V"),      
	 new JButton("W"),   
	 new JButton("X"),   
	 new JButton("Y"),   
	 new JButton("Z"),   
	 
	};
	
	private int misses = 0;
	
	
	ClickListener cl = new ClickListener();
///////// USER INPUT //////////////////////////////////////
	private int allowedMisses; 
	private String answer = "HANSON";
	private ArrayList<String> guesses = new ArrayList<String>();
///////////////////////////////////////////////////////////
	
	public hangman() {
		
		JPanel panel = new JPanel();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width/2-this.getSize().width/2)-FRAME_WIDTH/2, 
				(dim.height/2-this.getSize().height/2)-FRAME_HEIGHT/2);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Hangman");
		panel.setLayout(null);
		
		 for(int i = 0; i < 26; i++) {
		    	letters[i].addActionListener(cl);
		 }
		 
		 for(int i = 0; i < 26; i++) {
		    	letters[i].setFont(new Font("Arial", Font.PLAIN, 40));
		 }
		
		    
	    int w = 80, h = 80;
	    for(int i = 0, x = 20, y = 520; i < 26; i++) {
	    	if(i != 0 && i%13 == 0) {
	    		y += 130;
	    		x = 20;
	    	}
	    	letters[i].setBounds(x, y, w, h);
	    	x += 105;
	    }
	    
	    for(int i = 0; i < 26; i++) {
	    	panel.add(letters[i]);
	    }
		    
		
		
		
		this.add(panel);
		this.setVisible(true);
		
	}
	
	private class ClickListener implements ActionListener { 

	    public void actionPerformed(ActionEvent e) {
	    	String letter = (String)((AbstractButton)e.getSource()).getText();
	    	guesses.add(letter);
	    	((Component) e.getSource()).setEnabled(false);
	    	
	    	if(answer.indexOf(letter) == -1) {
		    	((JComponent) e.getSource()).setBackground(Color.RED);
		    	((JComponent) e.getSource()).setOpaque(true);
	    	}
	    	else {
	    		((JComponent) e.getSource()).setBackground(Color.GREEN);
	    	}
	    }
    
  }
	
	public static void main(String[] args) {
		new hangman();
		
	}
	
	
}
