package psweb.hangman;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Hangman 
{
	private int chances = 6;
	private Word currentWord;
	private List<Character> history;
	
	public Hangman()
	{
		history = new ArrayList<Character>();
	}
	
	// Sorteia uma nova palavra
	public void reset()
	{
		currentWord = new Word();
		chances = 6;
		history = new ArrayList<Character>();
	}
	
	// Faz input de um caractere
	public boolean input(char chr)
	{
		boolean match = currentWord.input(chr);				
		
		// Atualiza o contador de vidas
		if (!match)
			chances--;
		
		// Atualiza o histórico
		history.add(Character.toUpperCase(chr));
		
		return match;
	}
	
	//Retorna a imagem de acordo com a quantidade de chances
	public String getImage(){
		return this.chances + "_chances.png";
	}
	
	public boolean isComplete()
	{
		return currentWord.isComplete();
	}	
	
	public boolean isGameOver()
	{
		return chances==0 || isComplete(); 
	}
	
	
	/**
	 * Verifica se o parametro passado esta zerado, caso sim significa que o tempo esgotou.
	 * 
	 * @author Natalia
	 * @param Interiro que recebe 0 caso o tempo acabar.  
	 */
	public boolean isTimeOut(int cron)
	{
		return cron==0;
	}
	
	//
	// Métodos de acesso
	//	
	public int getChances() {
		return chances;
	}
	
	public String getWordAsString() {
		return currentWord.getWordAsString();
	}
	
	public String getAnswerAsString() {
		return currentWord.getAnswerAsString();
	}
	
	public List<Character> getInputHistory() {
		return history;
	}
	
	
}
