package Entidades;

public class CarrinhoItem
{
    private int id;
    private int idPlano;
    private int idCarrinho;
    private int quantidade;

    public CarrinhoItem(int id, int idCarrinho, int idPlano, int quantidade) {
        this.id = id;
        this.idCarrinho = idCarrinho;
        this.idPlano = idPlano;
        this.quantidade = quantidade;
    }

    public CarrinhoItem(int idCarrinho, int idPlano, int quantidade) {
        this.idCarrinho = idCarrinho;
        this.idPlano = idPlano;
        this.quantidade = quantidade;
    }

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public int getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(int idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public int getQuantidade() { return quantidade;}

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
