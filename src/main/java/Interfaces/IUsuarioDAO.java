package Interfaces;

import Entidades.Usuario;

import java.sql.SQLException;

public interface IUsuarioDAO {
    public void Inserir(Usuario usuario) throws SQLException;
    public Usuario Login(String login, String senha) throws SQLException;
}
