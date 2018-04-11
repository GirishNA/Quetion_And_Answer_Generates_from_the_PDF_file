package testingPgms;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.*;

import Configuration.Configuration;

public class ListOfFilesFromFolder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File listOfFile = new File(Configuration.INPUT_FILE_PATH);

		for(File file: listOfFile.listFiles()) {
			if(file.getName().endsWith(".pdf")) {
				System.out.println(file.getName());
				try {
					Files.move(file.toPath(), Paths.get(Configuration.FILE_MOVE_PATH+file.getName()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
