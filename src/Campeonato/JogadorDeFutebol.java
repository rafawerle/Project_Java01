package Campeonato;

public abstract class JogadorDeFutebol{
    
    protected String nome;
    protected String posicao;
    protected int camisa;
    
    public JogadorDeFutebol (String nome, String posicao, int camisa){
        this.nome = nome;
        this.posicao = posicao;
        this.camisa = camisa;
    }
    
    public String getNome (){ return nome;}
    public String getPosicao() { return posicao;}
    public int getCamisa() {return camisa;}
    
    public abstract void exibeDados();
}