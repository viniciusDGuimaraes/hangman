package psweb.hangman;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;

@Component
@ManagedBean
@SessionScoped
public class HangmanBean extends _Bean
{    
	//
	// Atributos
	//
	private Hangman hangman;
	private boolean ControlSounds = false;
	private int 	cronometro 	  = 10; //Contador de segundos para perder o jogo
	private int 	cronometro2   = 1;  //Contador para Time acabar
	
	//
	// Campos do Formulário
	//
	private String letter = "";
	
	//
	// Construtor
	//
	public HangmanBean()
	{
		this.hangman = new Hangman();
		this.hangman.reset();
	}
	
	//
	// Operações
	//
	public void guess()
	{
		char chr = Character.toUpperCase(letter.toCharArray()[0]);
		if(chr>=65 && chr<=90){
			ControlSounds = hangman.input(chr);
			letter = "";
			
			// Controle de audio para letras erradas
			if (ControlSounds == false){
				ControlSounds = true;
			}else{
				ControlSounds = false;
			}
		}
	}
	
	public void reset()
	{
		hangman.reset();
		letter = "";
		cronometro = 10;
		cronometro2 = 1; 
	}
	
	//
	// Métodos de Acesso
	//
	public String getImage(){
		return hangman.getImage();
	}
	
	public String getWord()
	{
		return hangman.getWordAsString();
	}
	
	public String getAnswer(){
		return hangman.getAnswerAsString();
	}
	
	public Integer getChances()
	{
		return hangman.getChances();
	}
	
	public String getAttempts()
	{
		return hangman.getInputHistory().toString();
	}
	
	public boolean isGameOver()
	{
		return hangman.isGameOver() || isTimeOut();
	}
	
	/**
	 * Metodo que verifica se o tempo acabou.
	 * 
	 * @author Natalia
	 */
	public boolean isTimeOut()
	{
		return hangman.isTimeOut(cronometro2);
	}
	
	public boolean isGameWin()
	{
		return hangman.isComplete();
	}
	
	public boolean isGameLose()
	{
		return hangman.getChances()==0 || isTimeOut();
	}
	
	public String getLetter() 
	{
		return letter;
	}
	
	public void setLetter(String letter) 
	{
		this.letter = letter;
	}
	
	/**
	 * Interatividade de inatividade 
	 */
	public void onIdle() 
	{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                                        "Vamos.", "Esta pensando ou sera que desistiu?"));
    }
 
	/**
	 * Interatividade de inatividade
	 */
    public void onActive() 
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        "Muito bem!", "Mas seu tempo ainda esta acabando!"));
    }
    
    public boolean isControl()
    {
    	return ControlSounds;
    	
    }

	public int getCronometro() {
		return cronometro;
	}

	public void setCronometro(int cronometro) {
		this.cronometro = cronometro;
	}
	
	public int getCronometro2() {
		return cronometro2;
	}

	public void setCronometro2(int cronometro) {
		this.cronometro2 = cronometro;
	}
    
   
}  



