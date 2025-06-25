package Entidades;

public class Usuario {
    public String login;
    public String senha;
    public String sobrenome;
    public String nome;
    public String validacaoSenha;
    public boolean administrador;

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public Usuario(String nome, String sobrenome, String login, String senha, String validacaoSenha, boolean administrador) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.login = login;
        this.senha = senha;
        this.validacaoSenha = validacaoSenha;
        this.administrador = administrador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getValidacaoSenha() {
        return validacaoSenha;
    }

    public void setValidacaoSenha(String validacaoSenha) {
        this.validacaoSenha = validacaoSenha;
    }
}
