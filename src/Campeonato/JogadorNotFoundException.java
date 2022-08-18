package Campeonato;

public class JogadorNotFoundException extends Exception{
	
	private int camisa;
	
	public JogadorNotFoundException (int camisa){
		this.camisa = camisa;
	}
	
	
	public String toString (){
		return("N�o foi poss�vel localizar jogador (camisa: "+camisa+").");
	}

}
