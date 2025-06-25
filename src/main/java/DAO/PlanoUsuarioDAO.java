package DAO;

import Entidades.ConnectionDB;
import Entidades.PlanoUsuario;
import Interfaces.IPlanoUsuarioDAO;

import java.sql.SQLException;

public class PlanoUsuarioDAO implements IPlanoUsuarioDAO {

    public PlanoUsuarioDAO() {
    }

    @Override
    public void Inserir(PlanoUsuario planoUsuario) throws SQLException {
        try{
            String SQL = "INSERT INTO \"PlanoUsuario\" (\"Id_Plano\",\"Id_Usuario\",\"DataInicio\", \"DataFim\", \"FormaPagamento\", \"Mensalidade\") " +
                    "VALUES ('" + planoUsuario.getId_plano() + "', '" + planoUsuario.getId_usuario() + "', '" + planoUsuario.getDataInicio() + "', '" + planoUsuario.getDataFim() + "', " + planoUsuario.getFormaPagamento() + ", " + planoUsuario.getMensalidade() + ")";
            ConnectionDB.getS().executeUpdate(SQL);
        }catch (Exception e){
            System.out.println("Erro ao inserir PlanoUsuario: " + e.getMessage());
        }
    }
}
