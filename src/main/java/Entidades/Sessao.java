package Entidades;

public class Sessao {
    private static int idUsuario;
    private static boolean isAdmin;

    public static boolean getIsAdmin() {
        return isAdmin;
    }

    public static void setIsAdmin(boolean isAdmin) {
        Sessao.isAdmin = isAdmin;
    }

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(int id) {
        idUsuario = id;
    }
}
