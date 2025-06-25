package Interfaces;

import Entidades.Plano;

import java.sql.SQLException;
import java.util.List;

public interface IPlanoDAO {
    public void Inserir(Plano plano) throws SQLException;
    public void Buscar(int idPlano) throws SQLException;
    public Plano BuscarPlano(int idPlano) throws SQLException;
    public void Remover(int idPlano) throws SQLException;
    public void Atualizar(Plano plano) throws SQLException;
    public double RetornaPreco(int idPlano) throws SQLException;
    public List<Plano> BuscarTodosPlanos() throws SQLException;
}
