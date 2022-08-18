package Campeonato;

public class Lateral extends JogadorDeFutebol implements AcaoJogador {
    
    private int qtGol, qtCartaoAmarelo, qtCartaoVermelho;
    
    public Lateral (String nome, int camisa){
        super (nome, "lateral", camisa);
        qtGol = 0;
        qtCartaoAmarelo = 0;
        qtCartaoVermelho = 0;
    }
    
    public int getGol(){return qtGol;}
    public int getCartaoAmarelo() { return qtCartaoAmarelo;}
    public int getCartaoVermelhor(){ return qtCartaoVermelho;}
    
    @Override 
    public void marcaGol(){
        qtGol++;
    }
    
    @Override
    public void recebeCartao(char tipoCartao){ //a - amarelo     v - vermelho
        if (tipoCartao == 'a' || tipoCartao == 'A')
           qtCartaoAmarelo++;
        else 
        if(tipoCartao == 'v' || tipoCartao == 'V')
           qtCartaoVermelho++;
        else
           System.out.println ("Informa��o N�O armazenada. Cart�o inv�lido. ('a' para cart�o amarelo. 'v' para cart�o vermelho)");
    }
    
    @Override
    public void exibeDados(){
        System.out.println("     Dados do jogador");
        System.out.println("-----------------------------");
        System.out.println("Nome: " + nome);
        System.out.println("Posi��o: "+posicao);
        System.out.println("Gols marcados: "+qtGol);
        System.out.println("Cart�o amarelo: "+qtCartaoAmarelo);
        System.out.println("Cart�o vermelho: "+qtCartaoVermelho);
    }
}