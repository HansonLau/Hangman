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
	
	ClickListener cl = new ClickListener(); // for game
	
///////// USER INPUT //////////////////////////////////////
	private int allowedMisses; 
	private String answer = "HANSON";
	private ArrayList<String> guesses = new ArrayList<String>();
///////////////////////////////////////////////////////////
	
	// Labels
	private JLabel word = new JLabel();
	private JLabel tries = new JLabel();
	
	public hangman() {
		
		answer = (JOptionPane.showInputDialog("Enter a word to guess")).toUpperCase();
		allowedMisses = Integer.parseInt(JOptionPane.showInputDialog("How many mistakes are allowed?"));
		
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
		   
	    tries.setBounds(450, 100, 300, 100);
	    tries.setFont(new Font("Arial", Font.PLAIN, 50));
	    word.setBounds(30, 300, 1350, 100);
	    word.setFont(new Font("Arial", Font.PLAIN, 100));
	    panel.add(word);
	    panel.add(tries);
	    
		setUp();
		this.add(panel);
		this.setVisible(true);
		
	}
	
	public void setUp() {
		String underScores = "";
		for(int i = 0; i < answer.length(); i++) {
			if (answer.substring(i,i+1).equals(" "))
				underScores += " ";
			else
				underScores += "_";
			underScores += " ";
		}
		
		word.setText(underScores);
		
	}
	
	private class ClickListener implements ActionListener { 

	    public void actionPerformed(ActionEvent e) {
	    	String letter = (String)((AbstractButton)e.getSource()).getText();
	    	guesses.add(letter);
	    	((Component) e.getSource()).setEnabled(false);
	    	
	    	if(answer.indexOf(letter) == -1) {
		    	((JComponent) e.getSource()).setBackground(Color.RED);
		    	misses++;
		    	tries.setText("Tries left: " + Integer.toString(allowedMisses-misses));
		    	if (allowedMisses-misses <=0)
		    		ending();
		    		
	    	}
	    	else {
	    		((JComponent) e.getSource()).setBackground(Color.GREEN);
	    	}
	    }
    
	}
	
	public void ending() {
		this.dispose();
		new end();
	}
	
	public void restarting() {
		this.dispose();
		new hangman();
	}
	
	public class end extends JFrame {
		private final int FRAME_HEIGHT2 = 550, FRAME_WIDTH2 = 900;
		private JLabel reveal = new JLabel("The answer was: " + answer);
		private JLabel talk = new JLabel("Better luck next time!");
		private JButton restart = new JButton("Again");
		private JButton exit = new JButton("Exit");
		ClickListener2 cl2 = new ClickListener2(); // for post-game
		
		public end() {
			
			JPanel panel2 = new JPanel();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((dim.width/2-this.getSize().width/2)-FRAME_WIDTH2/2, (dim.height/2-this.getSize().height/2)-FRAME_HEIGHT2/2);
			this.setSize(FRAME_WIDTH2, FRAME_HEIGHT2);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle("Results");
			this.add(panel2);
			
			panel2.setLayout(null);
			panel2.add(reveal);
			panel2.add(talk);
			panel2.add(restart);
			panel2.add(exit);
			reveal.setBounds(0, 10, 900, 200);
			talk.setBounds(190, 200, 500, 200);
			restart.setBounds(500, 400, 300, 100);
			exit.setBounds(50, 400, 300, 100);
			restart.addActionListener(cl2);
			exit.addActionListener(cl2);
			
			Font myFont = new Font("Serif", Font.BOLD, 50);
			
			reveal.setFont(myFont);
			talk.setFont(myFont);
			restart.setFont(myFont);
			exit.setFont(myFont);
			this.setVisible(true);
		}
		
		private class ClickListener2 implements ActionListener { 

		    public void actionPerformed(ActionEvent e) {

		    	if(e.getSource() == exit)
		    		System.exit(0);
		    	if(e.getSource() == restart) {
		    		restarting();
		    	}
		    }
	    
		}
		
	}
	
	
	public static void main(String[] args) {
		new hangman();
		
	}
	
	
}
