package Utility;

import java.awt.Desktop;
import java.io.File;
import Configuration.Configuration;


public class VideoPlayer {
	public static void play() {
		try {
	         
			HtmlWriter hw= new HtmlWriter();
			HtmlWriter.writeHtml();
		
			String htmlFilePath =Configuration.HTML_FILE_PATH;
			File htmlFile = new File(htmlFilePath);
	        Desktop.getDesktop().browse(htmlFile.toURI());
	        
	       }
	       catch (java.io.IOException e) {
	           System.out.println(e.getMessage());
	       }
		
	}
		public static void main(String [] args)
		{
			new VideoPlayer();
			VideoPlayer.play();
		}
		
	
}
