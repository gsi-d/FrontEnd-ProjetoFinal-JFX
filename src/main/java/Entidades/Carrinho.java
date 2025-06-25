package Entidades;

import java.util.Date;

public class Carrinho {
    private int id;
    private int idUsuario;
    private int total;
    private Date dataCriacao;

    public Carrinho(int id, int idUsuario, int total, Date dataCriacao) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.total = total;
        this.dataCriacao = dataCriacao;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
