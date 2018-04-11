package distractorgeneration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import Configuration.Configuration;

public class PDFToTextWithHeadersDelemeter {

	public static void convertPDFToText(File pdfFilePath) {
		PDFParser parser = null;
	    PDDocument pdDoc = null;
	    COSDocument cosDoc = null;
	    PDFTextStripper pdfStripper;

	    String parsedText;
//	    String fileName = "/home/user/mac/input_file/mgl-trading-policy.pdf";
	   // File file = new File(pdfFilePath);
	    try {
	    	//File txtFile = new File(Configuration.INPUT_FILE_PATH+"input.txt");
	    	File txtFile = new File(Configuration.INPUT_FILE_PATH+Configuration.INPUT_TEXT_FILE_NAME);
	    	//FileWriter fw = new FileWriter(txtFile,true);
	    	FileWriter fw = new FileWriter(txtFile);
	     //   parser = new PDFParser(new FileInputStream("/home/user/mac/input_file/mgl-trading-policy.pdf"));
	    	parser = new PDFParser(new FileInputStream(pdfFilePath));
	        parser.parse();
	        cosDoc = parser.getDocument();
	        pdfStripper = new PDFTextStripper();
	        pdDoc = new PDDocument(cosDoc);
	        parsedText = pdfStripper.getText(pdDoc);
	        System.out.println("Pdf parsing complete");
	        
	        //fw.write(parsedText);
	        
	        String[] strData= parsedText.split("\\n");
	        
	        int count = 1;
	        boolean headFlag = false;
	        
	        for(String eachLine: strData) {
	        		        	
	        	if(!eachLine.trim().isEmpty() ) {
	        		
	        		if(eachLine.trim().matches("^[(A-Z)|\\s]*$")) {
        			if(!headFlag) {	        				
	        				//fw.write("\n");
	        				fw.write(Configuration.DELIMITERS+"\n");
	        			}
	        			fw.write(eachLine.trim()+" ");
	        			headFlag = true;
	        			
	        		}else  		
	        		 {
	        			
	        			if(headFlag) {
	        				fw.write("\n");
	        			}
	        			fw.write(eachLine+"\n");
	        			headFlag = false;

	        		}
	        	}
	        	
	        	if(strData.length == count) {
	        		fw.write(Configuration.DELIMITERS+"\n");
	        	}
	        	count++;

	        }
	    
	        fw.close();
	        System.out.println("---------------File  Convert Done--------------");
	    } catch (Exception e) {
	        e.printStackTrace();
	        try {
	        	
	            if (cosDoc != null)
	                cosDoc.close();
	            if (pdDoc != null)
	                pdDoc.close();
	        } catch (Exception e1) {
	            e.printStackTrace();
	        }

	    }

	}

}
