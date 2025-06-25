package DAO;

import Entidades.ConnectionDB;
import Entidades.Menu;
import Entidades.Sessao;
import Entidades.Usuario;
import Interfaces.IUsuarioDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO implements IUsuarioDAO {

    public UsuarioDAO() {
    }

    @Override
    public void Inserir(Usuario usuario) throws SQLException {
        try{
            String SQL = "INSERT INTO \"Usuario\" (nome, sobrenome, login, senha, validacaosenha) VALUES ('" + usuario.getNome() + "', '" + usuario.getSobrenome() + "', '" + usuario.getLogin() + "', '" + usuario.getSenha() + "', '" + usuario.validacaoSenha + "')";
            ConnectionDB.getS().executeUpdate(SQL);
            System.out.println("Dados Inseridos!");
        }catch (Exception e){
            System.out.println("Erro ao inserir usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario Login(String login, String senha) throws SQLException {
        String SQL = "SELECT * from \"Usuario\" WHERE login = '" + login + "'" + " AND senha = '" + senha + "'";
        ResultSet rs = ConnectionDB.getS().executeQuery(SQL);

        if (rs.next()) {
            Usuario usuario = new Usuario(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("login"), rs.getString("senha"), rs.getString("validacaoSenha"), rs.getBoolean("administrador"));
            System.out.println("Login efetuado com sucesso!:");
            System.out.println("isAdmin: " + usuario.administrador);

            Sessao.setIdUsuario(rs.getInt("id"));
            System.out.println("Id: " + Sessao.getIdUsuario());
            String isAdmin = "";
            if (!usuario.administrador) {
                Sessao.setIsAdmin(false);
                isAdmin = "normal";
            }else{
                Sessao.setIsAdmin(true);
                isAdmin = "administrador";
            }

            System.out.println("Nome: " + rs.getString("nome") + " - Usuário: " + usuario.administrador);
            Menu menu = new Menu();
            //menu.exibirMenuADM();
            return usuario;
        } else {
            System.out.println("Usuário não encontrado!");
            return null;
        }
    }
}
