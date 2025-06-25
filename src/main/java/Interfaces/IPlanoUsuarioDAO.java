package Interfaces;

import Entidades.PlanoUsuario;

import java.sql.SQLException;

public interface IPlanoUsuarioDAO {
    public void Inserir(PlanoUsuario plano) throws SQLException;

}
