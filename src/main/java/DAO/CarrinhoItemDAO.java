package DAO;

import Entidades.CarrinhoItem;
import Entidades.ConnectionDB;
import Interfaces.ICarrinhoItemDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoItemDAO implements ICarrinhoItemDAO {
    @Override
    public void Adicionar(CarrinhoItem carrinhoItem) throws SQLException {
        try{
            String SQL = "INSERT INTO \"CarrinhoItem\" (\"Id_Plano\", \"Id_Carrinho\", \"Quantidade\") VALUES ('" + carrinhoItem.getIdPlano() + "', '" + carrinhoItem.getIdCarrinho() + "', '" + carrinhoItem.getQuantidade() + "')";
            ConnectionDB.getS().executeUpdate(SQL);

        }catch (Exception e){
            System.out.println("Erro ao adicionar item no carrinho");
            e.printStackTrace();
        }
    }

    @Override
    public int Remover(CarrinhoItem carrinhoItem) throws SQLException {
        try{
            String SQL = "DELETE from \"CarrinhoItem\" WHERE \"Id_Carrinho\" = '" + carrinhoItem.getIdCarrinho() + "' AND  \"Id_Plano\"  = '" + carrinhoItem.getIdPlano() + "'";
            int linhasAfetadas = ConnectionDB.getS().executeUpdate(SQL);
            return linhasAfetadas;
        }catch (Exception e){
            System.out.println("Erro ao remover item do carrinho.");
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int Remover(int idItem) throws SQLException {
        try{
            String SQL = "DELETE from \"CarrinhoItem\" WHERE id = " + idItem;
            int linhasAfetadas = ConnectionDB.getS().executeUpdate(SQL);
            return linhasAfetadas;
        }catch (Exception e){
            System.out.println("Erro ao remover item do carrinho.");
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<CarrinhoItem> BuscarTodosItens(int idCarrinho) throws SQLException {
        try{
            List<CarrinhoItem> itens = new ArrayList<CarrinhoItem>();
            String SQL = "SELECT * from \"CarrinhoItem\" WHERE \"Id_Carrinho\" = '" + idCarrinho + "'";
            ResultSet rs = ConnectionDB.getS().executeQuery(SQL);

            while (rs.next()) {
                CarrinhoItem item = new CarrinhoItem(
                        rs.getInt("Id"),
                        rs.getInt("Id_Carrinho"),
                        rs.getInt("Id_Plano"),
                        rs.getInt("Quantidade")
                );
                itens.add(item);
            }
            return itens.isEmpty() ? new ArrayList<>() : itens;

        }catch (Exception e){
            System.out.println("Erro ao buscar itens do carrinho: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
