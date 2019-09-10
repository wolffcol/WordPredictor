package TreePackage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;


//Parse a text document to create the dictionary for word occurrences method
public class TrieDictionaryTrainer {
	
	File file = new File("C:\\Users\\CDubLaptop\\Downloads\\TestDocumentTrie.txt");
	ArrayList<String> stringList = new ArrayList<String>();
	
	public String[] OpenFile() throws IOException{
		
		BufferedReader textReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
		String str;
		while((str = textReader.readLine()) != null){
			stringList.add(str);
		}
		
		textReader.close();
		
		str = stringList.toString();
		
		
		//TODO-Properly implement parsing parameters to handle a larger variety of Text documents
		String[] returnArray = str.split(" |\\.|\\[|\\]\\,\\;\\'\\...\\?\\/\"");
		
		return returnArray;
		
	}
}
