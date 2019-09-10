package TreePackage;

import java.io.IOException;
import java.util.Arrays;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class TrieMain {

	public static void main(String[] args) throws IOException {
		
		TrieTree tt = new TrieTree();
		TrieDictionaryTrainer tdt = new TrieDictionaryTrainer();
		tt.loadTestData(tdt.OpenFile());
				
		char[] lettersArray = new char[45];
		
	    JFrame frame = new JFrame("Key Listener");
		frame.setSize(1000, 1000);
				 
		Container contentPane = frame.getContentPane();
				  
	    KeyListener listener = new KeyListener() {
		
	    int counter = 0;
	    String searchString = "";
	    
	    
		@Override
		//Stores keyboard presses in an array to be used as the key for the word predictor-TODO empty text field
		public void keyPressed(KeyEvent event) {
			
			if(lettersArray[counter] < 'a' || lettersArray[counter] > 'z'){
				lettersArray[counter] = event.getKeyChar();
				counter++;
				
				if(counter >= 3) {
					for(int j = 0; j < counter; j++) {
						searchString = searchString + lettersArray[j];
					}
					tt.textPredictor(searchString);
					searchString = "";
					counter = 0;
					Arrays.fill(lettersArray, 'A');	
				}
			}	 
		}
				 
		@Override
		//Unused method	 
		public void keyReleased(KeyEvent event) {
				 
		    //printEventInfo("Key Released", event);
				 
		}
				 
		@Override
		//Unused method		 
		public void keyTyped(KeyEvent event) {
				 
		    //printEventInfo("Key Typed", event);
				 
		}
	    
	};
		 JTextField textField = new JTextField();
	 
	  textField.addKeyListener(listener);
	 
	  contentPane.add(textField, BorderLayout.NORTH);
	 
	  frame.pack();
	 
	  frame.setVisible(true);
	  
	}
}
