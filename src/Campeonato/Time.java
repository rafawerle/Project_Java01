package Campeonato;

public class Time {
	
	private String nome;
	private JogadorDeFutebol[] elenco;
	private int nroGol;
	private int jogadorLivre;
	
	public Time(String nome){
		this.nome = nome;
		elenco = new JogadorDeFutebol[5];
		nroGol =0;
		jogadorLivre=0;
	}
	
	public String getNome(){return nome;}
	public int getGols(){return nroGol;}
	public int getJogadorLivre(){return jogadorLivre;}
	
	public void zeraGols(){nroGol = 0;}

 	public void adicionaJogador(JogadorDeFutebol novoJogador){
	  if (jogadorLivre < elenco.length){
		
		if(jogadorLivre == 0){
			  elenco[0] = novoJogador;
			  jogadorLivre++;
			  System.out.println("Adicionado: "+novoJogador.getNome()+" - camisa "+novoJogador.getCamisa());
		}
		else{
			
			boolean jaTem = false;
			for(int i=0; i<jogadorLivre && !jaTem; i++)
				if(elenco[i].getCamisa() == novoJogador.getCamisa())
					jaTem = true;
			if(jaTem)
				System.out.println("Ja existe essa camisa no time");
			else{
				elenco[jogadorLivre] = novoJogador;
				jogadorLivre++;
				System.out.println("Adicionado: "+novoJogador.getNome()+" - camisa "+novoJogador.getCamisa());
			}
		}
	  }
	  else
		  System.out.println("O time já está completo");
	  }
	
	public void substituiJogador (JogadorDeFutebol jogadorEntra, int camisaSai) throws JogadorNotFoundException{
		boolean aux = false;	
		if(jogadorEntra instanceof Goleiro){
		   
		   for(int i=0; i<jogadorLivre && !aux; i++)
				if(elenco[i] instanceof Goleiro)
					aux = true;}		   
		if(aux)
			  System.out.println("Já tem goleiro no time.");		
		if(!aux){
			
			try{
			retiraJogador(camisaSai);
			adicionaJogador(jogadorEntra);
			} catch (JogadorNotFoundException e){
				System.out.println(e);				
			}			
		}
	}
	
	public void retiraJogador (int camisa) throws JogadorNotFoundException{
		boolean achou = false;
		
		for (int i=0; i<jogadorLivre && !achou; i++)
			if (elenco[i].getCamisa() == camisa){
				achou = true;
				System.out.println("Retirado: "+elenco[i].getNome() +" - camisa: "+camisa);
								
				for(int j = i; j<jogadorLivre; j++)
					if(j == jogadorLivre - 1)
						elenco[j] = null;
					else 
						elenco [j] = elenco[j+1];
				
				jogadorLivre--;
			}		
		if(!achou)
			throw new JogadorNotFoundException(camisa);
	}
	
	public void incrementaGol (int camisa){
		boolean aux = false;
		for(int i=0; i<jogadorLivre && !aux; i++){
			if(elenco[i].getCamisa() == camisa){
				aux = true;
				if (elenco[i] instanceof Atacante)
					((Atacante)elenco[i]).marcaGol();
				if (elenco[i] instanceof Goleiro)
					((Goleiro)elenco[i]).marcaGol();
				if (elenco[i] instanceof Zagueiro)
					((Zagueiro)elenco[i]).marcaGol();
				if(elenco[i] instanceof Lateral) 
					((Lateral)elenco[i]).marcaGol();
				nroGol++;
			}
		}
	}
	
	public void exibeDados(){
		
		System.out.println("_____________________________________");
		System.out.println("Time: "+nome);
		System.out.println("\n --> Elenco do time");
		for (int i = 0; i<jogadorLivre; i++)
			System.out.println(elenco[i].getNome()+" - camisa "+elenco[i].getCamisa()+" - "+elenco[i].getPosicao());
		System.out.println("\nGols marcados: "+nroGol);
		System.out.println("_____________________________________");
	}
	
	public void exibeDadosJogador(int camisa){
		boolean aux = false;
		for(int i=0; i<jogadorLivre && !aux; i++)
			if(elenco[i].getCamisa() == camisa){
				aux = true;
		        elenco[i].exibeDados();}
	}
}
