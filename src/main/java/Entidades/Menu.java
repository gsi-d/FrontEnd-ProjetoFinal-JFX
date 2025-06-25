package Entidades;

import DAO.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);

    public void exibirOpcaoInicial() throws SQLException {
        int opcao = 0;
        String nome, sobrenome, login, senha, validacaoSenha;
        boolean admin = false;
        System.out.println("digite a opção que voce deseja: \n1 - Fazer Login\n2 - Cadastrar-se\n3 - sair");
        opcao = scanner.nextInt();

        switch (opcao) {
            //LOGIN
            case 1:
                System.out.println("Informe o usuário: ");
                login = scanner.next();
                System.out.println("Informe a senha: ");
                senha = scanner.next();

                new UsuarioDAO().Login(login, senha);
                break;
            //REGISTRAR
            case 2:
//                System.out.println("Nome: ");
//                nome = scanner.next();
//
//                System.out.println("Sobrenome: ");
//                sobrenome = scanner.next();
//
//                System.out.println("Login: ");
//                login = scanner.next();
//
//                System.out.println("Senha: ");
//                senha = scanner.next();
//
//                System.out.println("Confirme a senha: ");
//                validacaoSenha = scanner.next();
//
//                Usuario usuario = new Usuario(nome, sobrenome, login, senha, validacaoSenha);
//                new UsuarioDAO().Inserir(usuario);
//                exibirOpcaoInicial();
                break;
            case 3:
                System.out.println("Encerrando...");
                System.exit(0);
            default:
                exibirOpcaoInicial();
                break;

        }
    }


    public void exibirMenuADM() throws SQLException {
        int opcao = 0;
        System.out.println("digite a opção que voce deseja: \n1 - Cadastrar um novo Plano\n2 - Buscar um Plano\n3 - Remover Plano\n4 - Atualizar Plano\n5 - Sair");
        opcao = scanner.nextInt();

        switch (opcao) {
            case 1: 
                //INSERIR Plano
                inserirPlano();
                break;
            case 2:
                // BUSCAR Plano
                buscarPlano(true);
                break;
            case 3:
                // REMOVER Plano
                removerPlano();
                break;
             case 4:
                // ATUALIZAR Plano
                 atualizarPlano();
                 break;
            default:
                exibirOpcaoInicial();
                break;
        }
    }

    public void inserirPlano() throws SQLException {
        double preco;
        String descricao;
        int duracao;
        scanner.nextLine();
        System.out.println("Descrição: ");
        descricao = scanner.nextLine();

        System.out.println("Preço: ");
        preco = scanner.nextDouble();

        System.out.println("Duração (meses): ");
        duracao = scanner.nextInt();

        Plano plano = new Plano(descricao, preco, duracao);
        new PlanoDAO().Inserir(plano);
        exibirMenuADM();
    }

    public void removerPlano() throws SQLException{
        System.out.println("Informe o id do Plano: ");
        int idPlanoDelete = scanner.nextInt();

        new PlanoDAO().Remover(idPlanoDelete);
        exibirMenuADM();
    }

    public void atualizarPlano() throws SQLException{
        scanner.nextLine();

        System.out.println("Informe o id do Plano que você quer atualizar: ");
        int idPlanoAtualizar = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Informe a Descrição: ");
        String descricao = scanner.nextLine();

        System.out.println("Informe o Preço: ");
        double preco = scanner.nextDouble();

        System.out.println("Informe a Duração: ");
        int duracao = scanner.nextInt();

        Plano planoAtualizar = new Plano(descricao, preco, duracao, idPlanoAtualizar);
        new PlanoDAO().Atualizar(planoAtualizar);
        exibirMenuADM();
    }


    public void exibirMenu() throws SQLException {
        int opcao = 0;
        System.out.println("digite a opção que voce deseja: \n1 - Buscar um Plano\n2 - Adicionar ao carrinho\n3 - Retirar do carrinho\n4 - Confirmar compra\n5 - Sair");
        opcao = scanner.nextInt();
        switch (opcao) {
            case 1:
                buscarPlano(false);
                break;
            case 2:
                // ADICIONAR AO CARRINHO
                adicionarPlano();
            case 3:
                // RETIRAR DO CARRINHO
                retirarDoCarrinho();
            case 4:
                // CONFIRMAR COMPRA
                confirmarCompra();
            default:
                exibirOpcaoInicial();
                break;
        }
    }

    public void buscarPlano(boolean isAdm) throws SQLException{
        // BUSCAR Plano
        System.out.println("Informe o id do Plano: ");
        int idPlano = scanner.nextInt();

        Plano plano = new PlanoDAO().BuscarPlano(idPlano);
        if(plano != null) {
            System.out.println("Plano encontrado:");
            plano.exibirPlano();
        }

        if(isAdm){
            exibirMenuADM();
        }else{
            exibirMenu();
        }
    }

    public void adicionarPlano() throws SQLException{
        System.out.println("Informe o id do Plano: ");
        int idPlano = scanner.nextInt();

        System.out.println("Informe a quantidade: ");
        int quantidade = scanner.nextInt();

        Carrinho carrinhoExistente = new CarrinhoDAO().Buscar(Sessao.getIdUsuario());

        if(carrinhoExistente == null){
            new CarrinhoDAO().Inserir(Sessao.getIdUsuario());
            carrinhoExistente = new CarrinhoDAO().Buscar(Sessao.getIdUsuario());
            System.out.println(carrinhoExistente);
        }
        CarrinhoItem carrinhoItem = new CarrinhoItem(carrinhoExistente.getId(), idPlano, quantidade);
        new CarrinhoItemDAO().Adicionar(carrinhoItem);
        System.out.println("Item adicionado com sucesso!");
        exibirMenu();
    }

    public void retirarDoCarrinho() throws SQLException{
        System.out.println("Informe o id do Plano: ");
        int idPlanoDelete = scanner.nextInt();

        Carrinho carrinhoExistente = new CarrinhoDAO().Buscar(Sessao.getIdUsuario());
        CarrinhoItem carrinhoItemDelete = new CarrinhoItem(carrinhoExistente.getId(), idPlanoDelete, 0);
        int linhasAfetadas = new CarrinhoItemDAO().Remover(carrinhoItemDelete);
        if(linhasAfetadas > 0){
            System.out.println("Item removido com sucesso!");
        }else{
            System.out.println("Nenhum item foi removido.");
        }
        exibirMenu();
    }

    public void exibeItensCarrinho(List<CarrinhoItem> itens) throws SQLException {
        try{
            for (CarrinhoItem item : itens) {
                System.out.println("Id Item: " + item.getId() );
                Plano plano = new PlanoDAO().BuscarPlano(item.getIdPlano());
                plano.exibirPlano();
                System.out.println("Quantidade: " + item.getQuantidade());
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir itens do carrinho: " + e.getMessage());
        }
    }

    public void exibeTotalCarrinho(List<CarrinhoItem> itens) throws SQLException {
        try{
            double totalCarrinho = 0;
            for (CarrinhoItem item : itens) {
                double precoPlano = new PlanoDAO().RetornaPreco(item.getIdPlano());
                totalCarrinho += precoPlano * item.getQuantidade();
            }

            System.out.println("Total do carrinho: " + totalCarrinho);
        }catch (Exception e){
            System.out.println("Erro ao exibir total do carrinho: " + e.getMessage());
        }
    }

    public void confirmarCompra() throws SQLException{

        Carrinho carrinho = new CarrinhoDAO().Buscar(Sessao.getIdUsuario());
        List<CarrinhoItem> itens = new CarrinhoItemDAO().BuscarTodosItens(carrinho.getId());
        if(!itens.isEmpty()){
            System.out.println("Itens do seu carrinho: ");
            exibeItensCarrinho(itens);
            exibeTotalCarrinho(itens);
            System.out.println("Deseja mesmo confirmar a compra? (s/n)");
            scanner.nextLine();
            String confirmar = scanner.nextLine();
            if (confirmar.equals("s")) {
                System.out.println("Qual será a forma de pagamento? \n0 - Cartão de crédito \n1 - Cartão de débito \n2 - Pix");
                int formaPagamento = scanner.nextInt();
                for (CarrinhoItem item : itens) {
                    Plano plano = new PlanoDAO().BuscarPlano(item.getIdPlano());
                    double mensalidade = plano.getPreco() * item.getQuantidade();
                    PlanoUsuario planoUsuario = new PlanoUsuario(item.getIdPlano(), Sessao.getIdUsuario(), new Date(), plano.getDuracao(), formaPagamento, mensalidade);
                    new PlanoUsuarioDAO().Inserir(planoUsuario);
                }
                for (CarrinhoItem item : itens) {
                    new CarrinhoItemDAO().Remover(item);
                }
                new CarrinhoDAO().Remover(Sessao.getIdUsuario());
                System.out.println("Compra confirmada!");

            }else{
                exibirMenu();
            }
        }else{
            System.out.println("Não existem itens no carrinho.");
            exibirMenu();
        }

    }
}
