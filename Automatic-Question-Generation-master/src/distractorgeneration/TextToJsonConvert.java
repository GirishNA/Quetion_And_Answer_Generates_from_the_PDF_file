package distractorgeneration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.simple.JSONObject;

import Configuration.Configuration;
//import mytesting.code.Text2Json;

public class TextToJsonConvert {

	//public static void main(String[] args) {
	public static void textToJson() {
		// final String FILENAME = "C:\\workspace\\input\\intents.json";
		// final String FILENAME = Constants.output_file;

		final String FILENAME =Configuration.JSON_FILE;
		//  String line = null;
		 
		 //File f1 = new File("/home/user/mac/testing/input.txt");
		// File f1 = new File(Configuration.input_file);
		 File f1 = new File(Configuration.QUESTION_DISTRACTOR_LOG_PATH);
		  Scanner scanner = null;
		  
		  JSONArray arrObj= new JSONArray();
		try {
			scanner = new Scanner(f1);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 // List<Text2Json> intentList = new ArrayList<Text2Json>();
		  List<String> pattern = new ArrayList<String>();
		  List<String> response = new ArrayList<String>();
		  String tag="";
		  while(scanner.hasNextLine()){					  
			  String eachLine = scanner.nextLine();
			  
			if((!eachLine.startsWith("Q: ") && !eachLine.startsWith("A: ")) && !eachLine.equals(Configuration.DELIMITERS)) {
				  System.out.println("tag name :\t "+eachLine);
				  tag = eachLine;
			  }else if(eachLine.equals(Configuration.DELIMITERS)) {
				  JSONObject obj = new JSONObject();
				  obj.put("tag",tag);				  
				  obj.put("patterns", pattern);
				  obj.put("responses", response);	
				  obj.put("context_set","");
				 
				  arrObj.put(obj);				 
				  
				  pattern.clear();
				  response.clear();
				  tag ="";
			  }else {	

				  if(eachLine.startsWith("Q: ")) {
					  pattern.add(eachLine.replace("Q: ",""));
				  }else {
					  response.add(eachLine.replace("A: ", ""));
				  }
			  }
		  }
		    scanner.close();

		    System.out.println("Final Json : \t "+arrObj);
		    
		  BufferedWriter bw = null;
		  FileWriter fw = null;
		  			     
	      String header = "{\"intents\": ";
	      String tailer = "}";
	      try {
		        fw = new FileWriter(FILENAME);
				bw = new BufferedWriter(fw);
				bw.write(header);
				bw.write(arrObj.toString());
				bw.write(tailer);
				System.out.println("completed..");
	      	  }
	      catch (IOException e) {

				e.printStackTrace();
				}
	      finally {

				try {

					if (bw != null)
						bw.close();

					if (fw != null)
						fw.close();

				} catch (IOException ex) {

					ex.printStackTrace();

				}

			}
	}

}
