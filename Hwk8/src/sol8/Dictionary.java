package sol8;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Dictionary {
	private List<String> nouns = new ArrayList<String>();
	private List<String> verbs = new ArrayList<String>();
	private List<String> adjectives = new ArrayList<String>();
	private List<String> adverbs = new ArrayList<String>();
	private List<String> pronouns = new ArrayList<String>();
	private List<String> interjections = new ArrayList<String>();
	
	public void addWord(String line){
		String[] phrase = line.split(":");
		if (phrase.length < 2){
			throw new DictionaryFormatException (phrase[0].trim());
		}
		String pos = phrase[0].trim();
		String word = phrase[1].trim();
		
		if (pos.equals("noun")){
			nouns.add(word);
			}
		else if (pos.equals("verb")){
			verbs.add(word);
		}
		else if (pos.equals("adjective")){
			adjectives.add(word);
		}
		else if (pos.equals("adverb")){
			adverbs.add(word);
		}
		else if (pos.equals("pronoun")){
			pronouns.add(word);
		}
		else if (pos.equals("interjection")){
			interjections.add(word);
		}
		else{
			throw new UnsupportedCategoryException ();
		}
	}
	
	public String getWord(String partOfSpeech){
		String ret = "";
		Random random = new Random();
		double indexDoub = random.nextDouble();
		int index;// = (int) random.nextInt(2);
		
		if (partOfSpeech.equals("noun")){
			if (nouns.size() == 0){
				throw new EmptyWordListException();
			}
			index = (int)(indexDoub*nouns.size());
			ret = nouns.get(index);
			nouns.remove(index);
		}
		else if (partOfSpeech.equals("verb")){
			if (verbs.size() == 0){
				throw new EmptyWordListException();
			}
			index = (int)(indexDoub*verbs.size());
			ret = verbs.get(index);
			verbs.remove(index);
		}
		else if (partOfSpeech.equals("adjective")){
			if (adjectives.size() == 0){
				throw new EmptyWordListException();
			}
			index = (int)(indexDoub*adjectives.size());
			ret = adjectives.get(index);
			adjectives.remove(index);
		}
		else if (partOfSpeech.equals("adverb")){
			if (adverbs.size() == 0){
				throw new EmptyWordListException();
			}
			index = (int)(indexDoub*adverbs.size());
			ret = adverbs.get(index);
			adverbs.remove(index);
			}
		else if (partOfSpeech.equals("pronoun")){
			if (pronouns.size() == 0){
				throw new EmptyWordListException();
			}
			index = (int)(indexDoub*pronouns.size());
			ret = pronouns.get(index);
			pronouns.remove(index);
		}
		else if (partOfSpeech.equals("interjection")){
			if (interjections.size() == 0){
				throw new EmptyWordListException();
			}
			index = (int)(indexDoub*interjections.size());
			ret = interjections.get(index);
			interjections.remove(index);
		}
		else{
			throw new UnsupportedCategoryException();
		}
		return ret;
	}
}

class DictionaryFormatException extends RuntimeException{
	public DictionaryFormatException (String message){
		super(message);
	}
}
class EmptyWordListException extends RuntimeException{
	public EmptyWordListException (){
		super();
	}
}
class UnsupportedCategoryException extends RuntimeException{
	public UnsupportedCategoryException (){
		super();
	}
}