package Entidades;

public class Plano {
    private int id;
    private String descricao;
    private double preco;
    private int duracao;

    public Plano(String descricao, double preco, int duracao) {
        this.descricao = descricao;
        this.preco = preco;
        this.duracao = duracao;
    }

    public Plano(String descricao, double preco, int duracao, int id) {
        this.descricao = descricao;
        this.preco = preco;
        this.duracao = duracao;
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void exibirPlano()
    {
        if(this != null) {
            System.out.println("Id: " + this.getId() + " - Descrição: " + this.getDescricao() + " - Preço: " + this.getPreco() + " - Duração: " + this.getDuracao() + " meses");
        }
    }

}
