package DAO;

import Entidades.CarrinhoItem;
import Entidades.ConnectionDB;
import Entidades.Plano;
import Interfaces.IPlanoDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanoDAO implements IPlanoDAO {

    public PlanoDAO() {
    }

    @Override
    public void Inserir(Plano plano) throws SQLException {
        try{
            String SQL = "INSERT INTO \"Plano\" (descricao,preco,duracao) VALUES ('" + plano.getDescricao() + "', '" + plano.getPreco() + "', " + plano.getDuracao() + ")";
            ConnectionDB.getS().executeUpdate(SQL);
            System.out.println("Dados Inseridos!");
        }catch (Exception e){
            System.out.println("Erro ao inserir Plano: " + e.getMessage());
        }
    }

    @Override
    public void Buscar(int idPlano) throws SQLException {
        try{
            String SQL = "SELECT * from \"Plano\" WHERE id = '" + idPlano + "'";
            ResultSet rs = ConnectionDB.getS().executeQuery(SQL);
            if (rs.next()) {
                System.out.println("Plano encontrado:");
                System.out.println("Descricao: " + rs.getString("descricao") + " - preco:" + rs.getString("preco") + " - duracao:" + rs.getString("duracao"));
            } else {
                System.out.println("Plano não encontrado!");
            }
        }catch (Exception e) {
            System.out.println("Erro ao buscar Plano: " + e.getMessage());
        }
    }

    @Override
    public Plano BuscarPlano(int idPlano) throws SQLException {
        try{
            String SQL = "SELECT * from \"Plano\" WHERE id = '" + idPlano + "'";
            ResultSet rs = ConnectionDB.getS().executeQuery(SQL);
            if (rs.next()) {
                return new Plano(rs.getString("descricao"), rs.getDouble("preco"), rs.getInt("duracao"), rs.getInt("id"));
            } else {
                System.out.println("Plano não encontrado!");
            }
        }catch (Exception e) {
            System.out.println("Erro ao buscar Plano: " + e.getMessage());
        }
        return null;
    }

    @Override
    public double RetornaPreco(int idPlano) throws SQLException {
        try{
            String SQL = "SELECT Preco from \"Plano\" WHERE id = '" + idPlano + "'";
            ResultSet rs = ConnectionDB.getS().executeQuery(SQL);
            if (rs.next()) {
                return rs.getDouble("Preco");
            } else {
                System.out.println("Plano não encontrado!");
            }
        }catch (Exception e){
            System.out.println("Erro ao buscar preço!");
        }
        return 0;
    }

    @Override
    public void Remover(int idPlano) throws SQLException {
        try{
            String SQL = "DELETE from \"Plano\" WHERE id = '" + idPlano + "'";
            int linhasAfetadas = ConnectionDB.getS().executeUpdate(SQL);
            if (linhasAfetadas > 0) {
                System.out.println("Plano removido com sucesso.");
            } else {
                System.out.println("Plano não encontrado!");
            }
        }catch (Exception e){
            System.out.println("Erro ao remover plano");
        }
    }

    @Override
    public void Atualizar(Plano plano) throws SQLException {
        try{
            String SQL = "UPDATE \"Plano\" SET descricao = '" + plano.getDescricao() + "', preco = '" + plano.getPreco() + "', duracao = " + plano.getDuracao() + " WHERE Id = " + plano.getId();
            ConnectionDB.getS().executeUpdate(SQL);
            System.out.println("Plano atualizado com sucesso!");
        }catch (Exception e){
            System.out.println("Erro ao atualizar plano");
            e.printStackTrace();
        }
    }

    @Override
    public List<Plano> BuscarTodosPlanos() throws SQLException {
        try{
            List<Plano> itens = new ArrayList<Plano>();
            String SQL = "SELECT * from \"Plano\"";
            ResultSet rs = ConnectionDB.getS().executeQuery(SQL);

            while (rs.next()) {
                Plano item = new Plano(
                        rs.getString("Descricao"),
                        rs.getInt("Preco"),
                        rs.getInt("Duracao"),
                        rs.getInt("Id")
                );
                itens.add(item);
            }
            return itens.isEmpty() ? new ArrayList<>() : itens;

        }catch (Exception e){
            System.out.println("Erro ao buscar planos: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
