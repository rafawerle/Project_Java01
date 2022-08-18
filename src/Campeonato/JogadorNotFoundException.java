package Campeonato;

public class JogadorNotFoundException extends Exception{
	
	private int camisa;
	
	public JogadorNotFoundException (int camisa){
		this.camisa = camisa;
	}
	
	
	public String toString (){
		return("Não foi possível localizar jogador (camisa: "+camisa+").");
	}

}
