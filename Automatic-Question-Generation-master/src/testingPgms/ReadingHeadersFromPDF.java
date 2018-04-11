package testingPgms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
//import javafx.scene.text.Text; 

import Configuration.Configuration;

public class ReadingHeadersFromPDF {

	public static void main(String[] args) {

		
		PDFParser parser = null;
	    PDDocument pdDoc = null;
	    COSDocument cosDoc = null;
	    PDFTextStripper pdfStripper;

	    String parsedText;
//	    String fileName = "/home/user/mac/input_file/mgl-trading-policy.pdf";
	   // File file = new File(pdfFilePath);
	    try {
	    	File txtFile = new File(Configuration.INPUT_FILE_PATH+"input.txt");
	    	FileWriter fw = new FileWriter(txtFile,true);
	        parser = new PDFParser(new FileInputStream("/home/user/mac/input_file/mgl-trading-policy.pdf"));
	        parser.parse();
	        cosDoc = parser.getDocument();
	        System.out.println("--------->"+parser.getDocument().getCOSObject().getFilterManager());
	        System.out.println("--------->"+parser.getPDDocument().getPageMap());
	        pdfStripper = new PDFTextStripper();
	        pdDoc = new PDDocument(cosDoc);
	        parsedText = pdfStripper.getText(pdDoc);
	        System.out.println("Pdf parsing complete");
	        
	        //fw.write(parsedText);
	        
	        String[] strData= parsedText.split("\\n");
	        
	        int count = 1;
	        boolean headFlag = false;
	        
	        for(String eachLine: strData) {
	        	/*if(eachLine.matches("^[A-Z]*$")) {
	        		System.out.println("---------------->");
	        		fw.write(eachLine+"\n");
	        	}*/
	        	
	        	//System.out.println("------------------->"+eachLine.trim().matches("[(PAGE)|(page)\\s]\\d "));
	        	
	        	if(!eachLine.trim().isEmpty() ) {
	        		//System.out.println(eachLine.trim().matches("^[(A-Z)|\\s]*$")+"---------------->"+eachLine);
	        		
	        		/*if(!headFlag) {
        				fw.write("\n");		
        			}*/
	        		
	        		if(eachLine.trim().matches("^[(A-Z)|\\s]*$")) {
	        		//	fw.write(Configuration.delemeters+"\n");
	        			
	        			
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
	        
	        
	       
	       // System.out.println("File Write is complete------------\n"+parsedText);
	        System.out.println("total length------->"+parsedText.split("\\n").length);
	        
	        System.out.println("--11111-->\n"+parsedText.replaceAll("[^A-Za-z0-9. ]+", ""));
	        System.out.println("total length------->"+parsedText.replaceAll("[^A-Za-z0-9. ]+", "").length());

	        fw.close();
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
