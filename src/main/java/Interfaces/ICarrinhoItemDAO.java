package Interfaces;

import Entidades.CarrinhoItem;

import java.sql.SQLException;
import java.util.List;

public interface ICarrinhoItemDAO {
    public void Adicionar(CarrinhoItem carrinhoItem) throws SQLException;
    public int Remover(int idItem) throws SQLException;
    public int Remover(CarrinhoItem carrinhoItem) throws SQLException;
    public List<CarrinhoItem> BuscarTodosItens(int idCarrinho) throws SQLException;
}
