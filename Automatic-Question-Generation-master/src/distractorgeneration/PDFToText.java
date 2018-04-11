package distractorgeneration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import Configuration.Configuration;

public class PDFToText {
	//public static void main(String args[]) {
	  public static void convertPdfToTxtFile(File pdfFilePath) {
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
	        parser = new PDFParser(new FileInputStream(pdfFilePath));
	        parser.parse();
	        cosDoc = parser.getDocument();
	        pdfStripper = new PDFTextStripper();
	        pdDoc = new PDDocument(cosDoc);
	        parsedText = pdfStripper.getText(pdDoc);
	        System.out.println("Pdf parsing complete");
	        fw.write(parsedText);
	        System.out.println("File Write is complete");
	        //fw.write(parsedText.replaceAll("[^A-Za-z0-9. ]+", ""));
	       // System.out.println("--11111-->"+parsedText.replaceAll("[^A-Za-z0-9. ]+", ""));
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


