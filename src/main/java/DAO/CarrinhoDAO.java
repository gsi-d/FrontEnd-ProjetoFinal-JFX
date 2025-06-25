package DAO;

import Entidades.Carrinho;
import Entidades.ConnectionDB;
import Interfaces.ICarrinhoDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CarrinhoDAO implements ICarrinhoDAO {
    @Override
    public void Inserir(int idUsuario) throws SQLException {
        try{
            String SQL = "INSERT INTO \"Carrinho\" (\"Id_Usuario\", \"Total\", \"DataCriacao\") VALUES ('" + idUsuario + "', '" + 0 + "', '" + new Date() + "')";
            ConnectionDB.getS().executeUpdate(SQL);
        }catch (Exception e){
            System.out.println("Erro ao inserir carrinho: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Carrinho Buscar(int idUsuario) throws SQLException {
        try{
            String SQL = "SELECT * from \"Carrinho\" WHERE \"Id_Usuario\" = '" + idUsuario + "'";
            ResultSet rs = ConnectionDB.getS().executeQuery(SQL);

            if (rs.next()) {
                Carrinho carrinho = new Carrinho(rs.getInt("id"), rs.getInt("Id_Usuario"), rs.getInt("Total"), rs.getDate("DataCriacao"));
                return carrinho;
            }
            return null;

        }catch (Exception e){
            System.out.println("Erro ao buscar carrinho: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void Remover(int idUsuario) throws SQLException {
        try{
            String SQL = "DELETE from \"Carrinho\" WHERE \"Id_Usuario\" = " + idUsuario;
            ResultSet rs = ConnectionDB.getS().executeQuery(SQL);
        }catch (Exception e){
            System.out.println("Erro ao remover carrinho: " + e.getMessage());
        }
    }


}
