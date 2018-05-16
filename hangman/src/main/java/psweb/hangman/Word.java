package psweb.hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Word 
{
	private char[] wordChars;
	private char[] wordMask;
	
	public Word()
	{
		String word = getRandomWord();
		this.wordChars = word.toUpperCase().toCharArray();
		this.wordMask  = new char[wordChars.length];
		
		for (int i=0;i<wordMask.length;i++)
			wordMask[i] = '_';		
	}

	public String getRandomWord(){
		Random num = new Random();
		String filePath = "/home/vinicius/word_list.txt";
		File wordList = new File(filePath);
		List<String> words = new ArrayList<String>();
		Scanner reader = null;
		
		try {
			reader = new Scanner(wordList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while(reader.hasNextLine()){
			words.add(reader.nextLine());
		}

		return words.get(num.nextInt(words.size()));
	}
	
	public boolean input(char chr)
	{
		chr = Character.toUpperCase(chr);
		
		boolean match = false;
		
		for (int i=0;i<wordChars.length;i++)
		{
			if (wordChars[i] == chr && wordMask[i] == '_')
			{
				match = true;
				wordMask[i] = chr;
			}									
		}
		
		return match;
	}

	public boolean isComplete()
	{
		for (int i=0;i<wordChars.length;i++)
		{
			if (wordMask[i] == '_')
			{
				return false;
			}									
		}
		
		return true;
	}
	
	public String getWordAsString()
	{
		return new String(wordMask);
	}	
	
	public String getAnswerAsString()
	{
		return new String(wordChars);
	}	
}
