package Entidades;

import java.util.Calendar;
import java.util.Date;

public class PlanoUsuario {
    private int id;
    private int id_plano;
    private int id_usuario;
    private Date dataInicio;
    private Date dataFim;
    private double mensalidade;
    private int formaPagamento;

    public int getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(int formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_plano() {
        return id_plano;
    }

    public void setId_plano(int id_plano) {
        this.id_plano = id_plano;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public double getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(double mensalidade) {
        this.mensalidade = mensalidade;
    }

    public PlanoUsuario(int id, int id_plano, int id_usuario, Date dataInicio, Date dataFim, int formaPagamento, double mensalidade) {
        this.id = id;
        this.id_plano = id_plano;
        this.id_usuario = id_usuario;
        this.dataInicio = dataInicio;

        this.dataFim = dataFim;
        this.formaPagamento = formaPagamento;
        this.mensalidade = mensalidade;
    }

    public PlanoUsuario(int id_plano, int id_usuario, Date dataInicio, int duracao, int formaPagamento, double mensalidade) {
        this.id_plano = id_plano;
        this.id_usuario = id_usuario;
        this.dataInicio = dataInicio;
        this.formaPagamento = formaPagamento;
        this.mensalidade = mensalidade;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, duracao);
        this.dataFim = calendar.getTime();
    }
}
