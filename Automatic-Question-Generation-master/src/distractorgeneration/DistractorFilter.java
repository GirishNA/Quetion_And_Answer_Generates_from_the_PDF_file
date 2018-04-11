package distractorgeneration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Configuration.Configuration;

//import Configuration.Configuration;

import Utility.SuperSenseTagHelper;
import Utility.WordNetPythonAPI;
import edu.cmu.ark.PorterStemmer;

public class DistractorFilter {
	public static List<String> applyFiltersToDistractorList(String resolvedAnswerPhrase,String answerSentence,List<String> distractorList){
		Set<String> removedList=new HashSet<String>();
		List<String> filteredList=new ArrayList<String>();
		Set<String> filterWords=new HashSet<String>();
		//In extreme cases answerphrase might not be present in answer sentence
		//So add resolvedAnswerPhrase explicitly here
		filterWords.add(resolvedAnswerPhrase);
		//Filter 1:
		//converting all words in answerPhrase to lowercase
		answerSentence=answerSentence.toLowerCase();
		//remove dots at the end of the string alone and not from floating point numbers like "7.12"
		answerSentence=answerSentence.replaceAll("\\.(?!\\d)","");
		//remove all punctuation marks
		answerSentence = answerSentence.replaceAll("[!?,]", "");
	//	System.out.println(answerSentence);
		String[] strs = answerSentence.split("\\s+");
	//	System.out.println(strs);
		int maximumDistractorWordCount = 1;
		for(String str:distractorList){
			maximumDistractorWordCount=Math.max(maximumDistractorWordCount, str.split("\\s+").length);
		}

	//	System.out.println("Filter words for answerSentence :"+answerSentence);
		//System.out.println("maximumDistractorWordCount :"+maximumDistractorWordCount);
		for(int i=0;i<strs.length;i++){
			String filterWord="";
			int spaceRequired=0;
			for(int j=i;j<i+maximumDistractorWordCount&&j<strs.length;j++){
				if(spaceRequired==0){
					spaceRequired=1;
				}
				else{
					filterWord+=" ";
				}
				filterWord+=strs[j];
				//filterWord=filterWord.trim();
			//	System.out.println("w:"+filterWord+":w");
				filterWords.add(filterWord);
			}
		}
		
		//Filter 2:
		//also remove the stemmed word of the resolvedAnswerPhrase
		String rootWord=PorterStemmer.getInstance().stem(resolvedAnswerPhrase);
		filterWords.add(rootWord.toLowerCase());
		
		//Filter 3:
		//remove distractors that are synonymous to answephrase
		//we do 2 kinds of removal here
		// Given string A(answerPhrase) and B(distractor)
		// B cannot be distractor and thus should be added to filterWords list
		// a) if A's synonym set contain B
		// b) intersection of A's synonym set and B's synonym set is not null
		if(Configuration.INPUT_FILE_NAME==null){
			System.out.println("Input file name is missing .Making input.txt as input file");
			Configuration.INPUT_FILE_NAME="input.txt";
			
		}
			String sstOfResolvedAnswerPhrase = SuperSenseTagHelper.getSSTForGivenWord(Configuration.INPUT_FILE_PATH+Configuration.INPUT_FILE_NAME,resolvedAnswerPhrase);
			Set<String> synonymsOfResolvedAnswerPhrase=new HashSet<String>(WordNetPythonAPI.getResponse("synonym", resolvedAnswerPhrase,sstOfResolvedAnswerPhrase));
			filterWords.addAll(synonymsOfResolvedAnswerPhrase);
			//the following for loop is for filter 3 subtask b
			for(String distractor:distractorList){
				String sstOfDistractor=SuperSenseTagHelper.getSSTForGivenWord(Configuration.INPUT_FILE_PATH+Configuration.INPUT_FILE_NAME,distractor);
				List<String> distractorSynonyms = WordNetPythonAPI.getResponse("synonym",distractor,sstOfDistractor);
				for(String str:distractorSynonyms){
					if(!str.equals("NO_RESPONSE")&&synonymsOfResolvedAnswerPhrase.contains(str)){
						filterWords.add(distractor);
						break;
					}
				}
			}
		
		//changing all distractors to lowercase words
		for(int i=0;i<distractorList.size();i++){
			distractorList.set(i,distractorList.get(i).toLowerCase());
		}
		removedList.addAll(distractorList);

		removedList.removeAll(filterWords);
	/*	System.out.println("Before filtering");
		for(String str:removedList){
			System.out.println(str);
		}
		System.out.println("Filter words ");
		for(String str:filterWords){
			System.out.println(str);
		}
		*/
		/*System.out.println("After filtering");
		for(String str:removedList){
			System.out.println(str);
		}*/
		
		for(String str:removedList){
			filteredList.add(str);
		}
		return filteredList;
	}
	
	
	public static List<String> removeSSTDistractorsFromPOSDistractorList(List<String> posDistractorList,List<String> sstDistractorList){
		for(String word:sstDistractorList){
			posDistractorList.remove(word);
		}
		return posDistractorList;
		
	}
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list.add("Hardin County");
		list.add("Nancy Lincoln");
		list.add("Abraham Lincoln");

		list=applyFiltersToDistractorList("Thomas","Although Thomas lacked formal education, he was an excellent farmer and carpenter, and often times served as a member of the jury.", list);
		System.out.println();
		System.out.println();
		System.out.println("After removing:");
		for(String word:list)
			System.out.println(word);
	}
	
}
