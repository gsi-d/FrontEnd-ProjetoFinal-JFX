package Entidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
    private static Connection Conn;
    private static Statement s;

    public static void ConectaBanco() throws SQLException, ClassNotFoundException {
        try{
            Class.forName("org.postgresql.Driver");
            Conn = DriverManager.getConnection("jdbc:postgresql://db.hzbsbvjwjsymnjuribgq.supabase.co:5432/postgres?user=postgres&password=TESTE123");
            s = Conn.createStatement();
            ConectaDataBase();
            System.out.println("BD Conectado ao servidor.");
        }catch (Exception e){
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
        }

    }

    // Conectar à database específica
    public static void ConectaDataBase() throws SQLException {
        Conn = DriverManager.getConnection("jdbc:postgresql://db.hzbsbvjwjsymnjuribgq.supabase.co:5432/postgres?user=postgres&password=TESTE123");
        s = Conn.createStatement();
    }

    public static Statement getS() {
        return s;
    }

    public static void setS(Statement s) {
        ConnectionDB.s = s;
    }

    public static Connection getConn() {
        return Conn;
    }

    public static void setConn(Connection conn) {
        Conn = conn;
    }
}
