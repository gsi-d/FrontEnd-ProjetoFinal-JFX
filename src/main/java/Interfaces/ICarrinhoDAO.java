package Interfaces;

import Entidades.Carrinho;

import java.sql.SQLException;

public interface ICarrinhoDAO {
    public void Inserir(int idUsuario) throws SQLException;
    public Carrinho Buscar(int idUsuario) throws SQLException;
    public void Remover(int idUsuario) throws SQLException;
}
