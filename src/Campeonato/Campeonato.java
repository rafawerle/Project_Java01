package Campeonato;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class Campeonato {
	
	private Time[] times;
	private int nroTimes;
	private Time[][] quartasDeFinal;
	private Time[][] semiFinal;
	private Time [][] aFinal;
 	private Time primeiro, segundo;
	
	public Campeonato(){
		times = new Time[8];
		quartasDeFinal = new Time[4][2];
		semiFinal = new Time[2][2];
		aFinal = new Time [1][2];
		nroTimes = 0;
	}
	
	
	public void adicionaTime (Time t){
		if(nroTimes == 8)
			System.out.println("Não é possível adicionar mais times");
		else{
			times[nroTimes] = t;
			nroTimes++;	    
		}	
	}
	
	public void sorteiaGrupos(){
		if(times[times.length-1]==null)
			System.out.println("Não foi possível definir grupos. Necessário 8 times.");
		else{
			
			//cria um array com numeros aleatórios de 1 a 8 sem repetição.
			int[] num = new int[8];
			int numero = 0;
			boolean jaTem = false;			
			for(int i=0; i<num.length; i++){
				if(i==0)
					num[i] = 1 + (int) (Math.random() * 8);
				else {					
					do {
					jaTem = false;
					numero = 1 + (int) (Math.random() * 8);
					for(int j=0; j<i; j++)
						if(num[j]==numero)
								jaTem = true;
					} while (jaTem==true);
					
					if(!jaTem)
						num[i]=numero;
				}				
			}
			
			//atualiza array grupos de acordo com o sorteio dos numeros
			int indice = 0;			
			for(int i = 0; i<4; i++){
				quartasDeFinal[i][0] = times[num[indice]-1];
				indice++;
				quartasDeFinal[i][1] = times[num[indice]-1];
				indice++;
			}
			  
			//cria um arquivo com os grupos das quartas de finais
			try{
				File f = new File ("QuartasDeFinal.txt");
				FileWriter fw = new FileWriter (f, false);
				PrintWriter pw = new PrintWriter (fw);

				for(int i=0 ; i<4; i++){
				   pw.println("Grupo "+(i+1)+" ;"+quartasDeFinal[i][0].getNome()+" ;"+quartasDeFinal[i][1].getNome());		
				}
				pw.close();
				}
				catch(IOException e){
				System.out.println("Erro na leitura do arquivo");
			    }
		}
	}
	
	public void iniciaPartida(Time time1, Time time2){
		if(time1!=null && time2!=null){
		 time1.zeraGols();
		 time2.zeraGols();
		
			
			//-------------------------------------------------------------------
			Teclado t = new Teclado();
			int opcao;		       
		    do{
		       System.out.println ("______________________________________");
		       System.out.println ("     Início de Jogo ["+time1.getNome()+" x "+time2.getNome()+"]");
		       System.out.println ("  ");
		       System.out.println ("1 - Jogador faz Gol");
		       System.out.println ("2 - Expulsa jogador");
		       System.out.println ("3 - Substituir Jogador");
		       System.out.println ("4 - Encerra partida");		       
		       do{
		         opcao = t.leInt();		         
		         if (opcao > 4 || opcao < 1){
		           System.out.println ("  ");
		           System.out.println ("Opção inválida");
		           System.out.println ("  ");}
		       } while (opcao > 4 || opcao < 1);
		     //-------------------------------------------------------------------  
		       	       
		       if (opcao == 1){ //jogador faz gol
		    	   String nome = t.leString("Nome do time: ");	 
		    	   int camisa = t.leInt("Camisa do jogado: ");
		    	   if(time1.getNome().equalsIgnoreCase(nome))
		    		   jogadorFazGol(time1.getNome(), camisa);
		    	   else
		    	   if(time2.getNome().equalsIgnoreCase(nome))
		    		   this.jogadorFazGol(time2.getNome(), camisa);
		    	   else
		    		   System.out.println("Não foi possível localizar esse time.");	    	   
		       }
		       else
		    	   
		    //---------------------------------------------------------------------	   
		       	   
		       if(opcao == 2){ //expulsa jogador
		    	   String nome = t.leString("Nome do time: ");	 
		    	   int camisa = t.leInt("Camisa do jogado: ");
		    	   if(time1.getNome().equalsIgnoreCase(nome))
		    		  try{
		    			  time1.retiraJogador(camisa);
		    		  } catch (JogadorNotFoundException e){
		    				  System.out.println(e);}
		      	   else
		      	   if(time2.getNome().equalsIgnoreCase(nome))
			    		  try{
			    			  time2.retiraJogador(camisa);
			    		  } catch (JogadorNotFoundException e){
			    				  System.out.println(e);}
		      	   else
		      		   System.out.println("Não foi possível localizar esse time.");	  
		       }
		       else
		    	   
		    //-----------------------------------------------------------------------------	   
		    	    	  
		       if (opcao == 3){ //substituir jogador
		    	   
		    	   String nome = t.leString("nome do Time: ");
		    	   int camisa = t.leInt("Camisa do jogador que sai: ");
		    	//----------------------------------------------------------------------------------------
		    	   JogadorDeFutebol novo = null;
		    	   System.out.println("\nO novo jogador é Goleiro(1), Atacante(2), Zagueiro(3) ou Lateral(4)");
		    	   int x;
		    	   do{
		    	     	x = t.leInt("digite o número da opção desejada!");
		    	    	if(x<1 || x>4)
		    		    System.out.println("\nOpção inválida\n");
		    	    } while(x<1 || x>4);		    	   
		    	   if(x==1) 
		    		   novo = new Goleiro(t.leString("Digite o nome do goleiro: "), t.leInt("Número da camisa: "));
		    	   if(x==2) 
		    		   novo = new Atacante(t.leString("Digite o nome do atacante: "), t.leInt("Número da camisa: "));
		    	   if(x==3) 
		    		   novo = new Zagueiro(t.leString("Digite o nome do zagueiro: "), t.leInt("Número da camisa: "));
		    	   if(x==4) 
		    		   novo = new Lateral(t.leString("Digite o nome do lateral: "), t.leInt("Número da camisa: "));
		       //-----------------------------------------------------------------------------------
		    	   
		    	   if(time1.getNome().equalsIgnoreCase(nome))
		    		   try{
		    			   time1.substituiJogador(novo, camisa);
		    		   } catch (JogadorNotFoundException e){
		    			   System.out.println(e);}
		    	   else
		    	   if(time2.getNome().equalsIgnoreCase(nome))
		    		   try{
		    			   time2.substituiJogador(novo, camisa);
		    		   } catch (JogadorNotFoundException e){
		    			   System.out.println(e);}
		    	   else		    	   
		    		   System.out.println("Não foi possível localizar esse time.");	
		       }
		       
		   //---------------------------------------------------------------------------------------    
		    } while (opcao != 4); // encerra  a partida se for == 4  
		}
		else
			System.out.println("Não foi possível identificar times.");
	}
  	
	public void jogadorFazGol(String time, int camisa){
		boolean achou = false;
		for(int i=0; i<times.length && !achou; i++)
			if(times[i] != null && times[i].getNome().equalsIgnoreCase(time)){
				times[i].incrementaGol(camisa);
				achou = true;
			}
		if(!achou)
			System.out.println("Não foipossível localizar time");
	}

	public Time ganhouPenaltis(Time time1, Time time2){
		
		Teclado t = new Teclado();
		System.out.println("Cobrança de pênaltis");
		char op;
		int t1=0;
		int t2=0;
		for(int i=1; i<6; i++){
			op = t.leChar("Time: "+time1.getNome()+" acertou "+i+"º pênalt? [s-sim / n-não]");
			if(op == 's')
				t1++;
			op = t.leChar("Time: "+time2.getNome()+" acertou "+i+"º pênalt? [s-sim / n-não]");
			if(op == 's')
				t2++;
		}
		
		if(t1 > t2) return time1;
		else 
		if(t1 < t2) return time2;
		else{
			System.out.println("Primeiro time com vantagem ganha.");
			do{
				op = t.leChar("Time: "+time1.getNome()+" acertou o pênalti? [s-sim / n-não]");
				if(op == 's')
					t1++;
				op = t.leChar("Time: "+time2.getNome()+" acertou o pênalti? [s-sim / n-não]");
				if(op == 's')
					t2++;
			} while(t1 == t2);
			
			if(t1 > t2) return time1;
			else 
			  return time2;	
		}
	}

	public void menuCampeonato(){
		Teclado t = new Teclado(); 
	      int opcao;
	       
	      do{
	       System.out.println ("______________________________________");
	       System.out.println ("         MENU DO CAMPEONATO");
	       System.out.println ("  ");
	       System.out.println ("1 - Incluir Time");
	       System.out.println ("2 - Sortear grupos para quartas de final");
	       System.out.println ("3 - Iniciar as quartas de final");
	       System.out.println ("4 - Inicar a semifinal");
	       System.out.println ("5 - Iniciar a final");
	       System.out.println ("6 - Exibir time vencedor");
	       System.out.println ("7 - Exibir dados de jogador");
	       System.out.println ("8 - Exibir dados de Time");
	       System.out.println ("9 - Encerra Campeonato");
	       System.out.println ("______________________________________");
	       
	       do{
	         opcao = t.leInt();
	         
	         if (opcao > 9 || opcao < 1){
	           System.out.println ("  ");
	           System.out.println ("Opção inválida");
	           System.out.println ("  ");}
	       } while (opcao > 9 || opcao < 1);
	       
	   //---------------------------------------------------------------------------------
	       
	       if (opcao == 1){ //incluir time
	    	 
	    	 if(nroTimes < 8){  
	    	  this.adicionaTime(new Time(t.leString("Digite o nome do Time: ")));
	          
	    	  System.out.println("Time "+times[nroTimes-1].getNome()+" incluido ao campeonato com sucesso\n");
	    		  
	    	  times[nroTimes-1].adicionaJogador( new Goleiro (t.leString("Digite o nome do goleiro: "), 
	    				                                      t.leInt("Número da camisa: ")));
	    		  
	    	  System.out.println("É preciso incluir 5 jogadores ao time.");
	    	  while (times[nroTimes-1].getJogadorLivre() < 5){	        	  
	        	  System.out.println("\n Deseja adicionar atacante(1), zagueiro(2) ou lateral(3)?");
	         	  int x;
	    	      do{
	    	     	x = t.leInt("digite o número da opção desejada!");
	    	    	if(x<1 || x>3)
	    		    System.out.println("\nOpção inválida\n");
	     	      }while(x<1 || x>3);
	    		  
	    	  if(x == 1)
	    		times[nroTimes-1].adicionaJogador( new Atacante (t.leString("Digite o nome do atacante: "), 
                                                        t.leInt("Número da camisa: ")));
	    	  if(x==2)
	    		times[nroTimes-1].adicionaJogador( new Zagueiro (t.leString("Digite o nome do zagueiro: "), 
                                                        t.leInt("Número da camisa: ")));
	    	  if(x==3)
	    		times[nroTimes-1].adicionaJogador( new Lateral (t.leString("Digite o nome do lateral: "), 
                                                       t.leInt("Número da camisa: ")));
	          }	    	   
	        }
	       }
	       
	  //--------------------------------------------------------------------------------------
	       
	       else
	       if(opcao == 2){ //sortear grupos para quartas de final
	        
	    	if(quartasDeFinal[0][0] == null){   //sorteia só uma vez
	           if(nroTimes==8)
	        	   this.sorteiaGrupos();
	           else
	        	   System.out.println("Para realizar o sorteio o campeonato precisa estar completo, adicione mais "+ (8-nroTimes)+" times.");
	    	} 
	    	else
	    		System.out.println("Os grupos ja foram sorteados.");
	       }
	       
	  //-------------------------------------------------------------------------------------
	       
	       else
	       if (opcao == 3){ //iniciar quartas de final
	    	   
	    	 if(quartasDeFinal[0][0] != null){ 
	    	   boolean aux = false;
	    	   for(int i=0; i<2 && !aux; i++)
	    		   for(int j=0; j<2; j++)
	    			   if(semiFinal[i][j] == null)
	    				   aux = true;
	    	   
	    	   if(!aux)
	    		   System.out.println("Não há mais partidas para quartas de final. Inicie a Semifinal.");
	    	   else{
	    		   if(semiFinal[0][0] == null){ //inicia partida etapa 1(quartas de final) grupo 1;
	    			   
	    			   //--------------------------------	    		   
	    			   System.out.println("Inicio das quartas de final\n");
	    			   iniciaPartida(quartasDeFinal[0][0], quartasDeFinal[0][1]);
	    			   if(quartasDeFinal[0][0].getGols() > quartasDeFinal[0][1].getGols())
	    				   semiFinal[0][0] = quartasDeFinal[0][0];
	    			   else
	    			   if(quartasDeFinal[0][0].getGols() < quartasDeFinal[0][1].getGols())
		    			   semiFinal[0][0] = quartasDeFinal[0][1];
	    			   else
	    				   semiFinal[0][0] = this.ganhouPenaltis(quartasDeFinal[0][0], quartasDeFinal[0][1]);
	    			   //--------------------------
	    			   iniciaPartida(quartasDeFinal[1][0], quartasDeFinal[1][1]);
	    			   if(quartasDeFinal[1][0].getGols() > quartasDeFinal[1][1].getGols())
	    				   semiFinal[0][1] = quartasDeFinal[1][0];
	    			   else
	    			   if(quartasDeFinal[1][0].getGols() < quartasDeFinal[1][1].getGols())
		    			   semiFinal[0][1] = quartasDeFinal[1][1];
	    			   else
	    				   semiFinal[0][1] = this.ganhouPenaltis(quartasDeFinal[1][0], quartasDeFinal[1][1]);
	    			   //--------------------------
	    			   iniciaPartida(quartasDeFinal[2][0], quartasDeFinal[2][1]);
	    			   if(quartasDeFinal[2][0].getGols() > quartasDeFinal[2][1].getGols())
	    				   semiFinal[1][0] = quartasDeFinal[2][0];
	    			   else
	    			   if(quartasDeFinal[2][0].getGols() < quartasDeFinal[2][1].getGols())
		    			   semiFinal[1][0] = quartasDeFinal[2][1];
	    			   else
	    				   semiFinal[1][0] = this.ganhouPenaltis(quartasDeFinal[2][0], quartasDeFinal[2][1]);
	    			   //---------------------------
	    			   iniciaPartida(quartasDeFinal[3][0], quartasDeFinal[3][1]);
	    			   if(quartasDeFinal[3][0].getGols() > quartasDeFinal[3][1].getGols())
	    				   semiFinal[1][1] = quartasDeFinal[3][0];
	    			   else
	    			   if(quartasDeFinal[3][0].getGols() < quartasDeFinal[3][1].getGols())
		    			   semiFinal[1][1] = quartasDeFinal[3][1];
	    			   else
	    				   semiFinal[1][1] = this.ganhouPenaltis(quartasDeFinal[3][0], quartasDeFinal[3][1]);
	    		   }
	           }
	    	 }
	    	 else
	    		 System.out.println("É preciso sortear os grupos das quartas de final antes de iniciar partidas.");
	       }
	    	   
	   //-----------------------------------------------------------------------------------------
	       else
	       if (opcao == 4){ //iniciar a semifinal
	    	   
	    	   boolean a = false;
	    	   for(int i=0; i<2 && !a; i++)
	    		   for(int j=0; j<2; j++)
	    			   if(semiFinal[i][j] == null)
	    				   a = true;	    	   
	    	   if(a)
	    		   System.out.println("Não é possível iniciar a semifinal sem concluir as quartas de final antes.");
	    	   else{
	    	   if(aFinal[0][0] != null && aFinal[0][1] != null)
	    		   System.out.println("Não há mais partidas para semifinal. Inicie a final.");
	    	   
	    	   else{
	    		      //--------------------------------	    		   
	    		   System.out.println("Inicio da semifinal\n");   
	    		   iniciaPartida(semiFinal[0][0], semiFinal[0][1]);
	    			   if(semiFinal[0][0].getGols() > semiFinal[0][1].getGols())
	    				   aFinal[0][0] = semiFinal[0][0];
	    			   else
	    			   if(semiFinal[0][0].getGols() < semiFinal[0][1].getGols())
		    			   aFinal[0][0] = semiFinal[0][1];
	    			   else
	    				   aFinal[0][0] = this.ganhouPenaltis(semiFinal[0][0], semiFinal[0][1]);
	    			   //--------------------------
	    			   iniciaPartida(semiFinal[1][0], semiFinal[1][1]);
	    			   if(semiFinal[1][0].getGols() > semiFinal[1][1].getGols())
	    				   aFinal[0][1] = semiFinal[1][0];
	    			   else
	    			   if(semiFinal[1][0].getGols() < semiFinal[1][1].getGols())
		    			   aFinal[0][1] = semiFinal[1][1];
	    			   else
	    				   aFinal[0][1] = this.ganhouPenaltis(semiFinal[1][0], semiFinal[1][1]);
	    		   }
	    	   }
	       }
	    			   
	    	
	  //--------------------------------------------------------------------------------------------  		   
	    	   
	       else
	       if (opcao == 5){ //inicia a final
	    	       	   
	    	   
	    	   if(aFinal[0][0] == null || aFinal[0][1] == null)
	    		   System.out.println("Não é possivel iniciar a final  sem concluir a semifinal");
	    	   
	    	   else{
	    		   System.out.println("Inicio da final\n");    
	    		   iniciaPartida(semiFinal[0][0], semiFinal[0][1]);
	    			   if(aFinal[0][0].getGols() > aFinal[0][1].getGols()){
	    				   primeiro = aFinal[0][0];
	    			       segundo = aFinal[0][1];
	    			   }else
	    			   if(aFinal[0][0].getGols() < aFinal[0][1].getGols()){
	    			       primeiro = aFinal[0][1];
	    			       segundo = aFinal[0][0];
	    			   }else{
	    				   primeiro = this.ganhouPenaltis(aFinal[0][0], aFinal[0][1]);
	    			       if(aFinal[0][0].getNome().equals(primeiro.getNome()))
	    			    	   segundo = aFinal[0][1];
	    			       else
	    			    	   segundo = aFinal[0][0];
	    			   }
	    			   
	    			   
	    			   try{
	    					File f = new File ("Resultado.txt");
	    					FileWriter fw = new FileWriter (f, false);
	    					PrintWriter pw = new PrintWriter (fw);

	    					pw.println("1º lugar - "+primeiro.getNome());	
	    					pw.println("2º lugar - "+segundo.getNome());
	    						    			
	    					pw.close();
	    					}
	    					catch(IOException e){
	    					System.out.println("Erro na leitura do arquivo");
	    				    }
	    			   
	    	   }
	       }	    		   
	    		   
	   //------------------------------------------------------------------------------------ 		   
	       else
	       if (opcao == 6){ //exibe Resultado   
	    	   
	    	   if(primeiro != null){
	    		  
	    		   try{
	    		    FileReader fr = new FileReader("Resultado.txt");
	    			BufferedReader br = new BufferedReader(fr);
	    			String line;
	    					
	    			while((line = br.readLine()) != null) {
	    				
	    				String[] s =line.split("-");	
	    				System.out.println(s[0]+"-"+s[1]);
	    					    						
	    			}
	    			br.close();
	    		   } catch (IOException e){
	    			   System.out.println("Erro na leitura do arquivo");
	    		   }
	    		   
	    	    }
	    	   else
	    		   System.out.println("Não é possível determinar o resultado ainda.");
	    		  
	       
	       }
	    		  
	   //------------------------------------------------------------------------------------ 		   
	       else
	       if (opcao == 7){ //exibe dados do jogador
	        
	    	
	        String nomeTime = t.leString("Nome do Time: ");
	        int camisa = t.leInt("Camisa do jogador: ");
	        boolean achou = false;
	        for(int i=0; i< times.length && !achou; i++){
	          if(times[i] != null)	
	        	if(times[i].getNome().equalsIgnoreCase(nomeTime)){
	        		times[i].exibeDadosJogador(camisa);
	        		achou = true;
	        	}
	        }
	        if(achou)
	        	System.out.println("Jogador Não encontrado.");
	    	
	    	
	       }
	    		   
	    		   
	   //------------------------------------------------------------------------------------ 		   
	       else
	       if (opcao == 8){ //exibe dados do time
	    	   String nome = t.leString("Nome do time: ");
	    	   boolean achou = false;
	    	   for(int i=0; i< times.length && !achou; i++) 
	    		   if(times[i] != null && times[i].getNome().equalsIgnoreCase(nome)){
	    			   times[i].exibeDados();
	    			   achou = true;
	    		   }
	       } 
	      } while(opcao != 9);
	      
	      if(primeiro!=null || nroTimes < 8){
	    	  System.out.println("Campeonato encerrado");
	    	  if(primeiro!= null)
	    	  System.out.println("\nVencedor: "+primeiro.getNome());
	      }
	      else{
	    	  System.out.println("Campeonato não está terminado. Não é possível encerrar.");
	    	  this.menuCampeonato();
	      }
	}
}