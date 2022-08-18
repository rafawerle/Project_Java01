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
           System.out.println ("Informação NÃO armazenada. Cartão inválido. ('a' para cartão amarelo. 'v' para cartão vermelho)");
    }
    
    @Override
    public void exibeDados(){
        System.out.println("     Dados do jogador");
        System.out.println("-----------------------------");
        System.out.println("Nome: " + nome);
        System.out.println("Posição: "+posicao);
        System.out.println("Gols marcados: "+qtGol);
        System.out.println("Cartão amarelo: "+qtCartaoAmarelo);
        System.out.println("Cartão vermelho: "+qtCartaoVermelho);
    }
}