###From PDF File to Question and Answer Generation Bot

	## Before Running Project 
		1. create some folders like as mention in 
			-> go to project -> src -> Configuration -> Configuration.java in this class you will get all the paths which used in the project and if you want to change folder structure you can change and update in the 'Configuration' class.
		
			1. INPUT_FILE_PATH :- where our all pdf files are store to process for generate the Q&A 
			2. FILE_MOVE_PATH :- Once the PDF is read completely that file will be moved to other folder
			3. QUESTION_DISTRACTOR_LOG_PATH :- This path is use to crete Q&A file 
			4. JSON_FILE :- This path is use to create the Json file from the Q&A
			5. JSON_CONVERT :- here we pass the boolean -> true - is convert the Q&A file to Json file, 
								       false - it wont convert into the json file.
			6. NOUN_PHRASES_FILE_PATH:- This create a noun.txt file and give the path here.
			
			Note:- Based on OS you have to change the all paths in the 'Configuration.java' class 

	## Steps to Start 
		## Runing Services in Background

		1. Go to Terminal window
		2. go to project folder
		3. $./runSSTServer.sh
		4. open new terminal window
			$./runStanfordParserServer.sh
		5. open new terminal window
			$cd pythonscripts/
			$python python_wordnet_server.py

		## Runing Project in Eclips
		
		1. Import the project in eclips 
		2. go to src->edu.cmu.ark package -> QuestionGenerationDriver.java class -> right click on class 
			-> Run As -> Java Application

	
