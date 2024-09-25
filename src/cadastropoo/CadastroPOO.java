package cadastropoo;

import model.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author edson-202308892185
 */
public class CadastroPOO {

    private final static PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
    private final static PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();
            processarOpcao(opcao);
        } while (opcao != 0);

        System.out.println("Programa finalizado.");
    }

    private static void exibirMenu() {
        System.out.println("=====================");
        System.out.println("Escolha uma opcao:");
        System.out.println("1 - Incluir Pessoa");
        System.out.println("2 - Alterar Pessoa");
        System.out.println("3 - Excluir Pessoa");
        System.out.println("4 - Buscar pelo ID");
        System.out.println("5 - Exibir Todos");
        System.out.println("6 - Persistir Dados");
        System.out.println("7 - Recuperar Dados");
        System.out.println("0 - Finalizar Programa");
        System.out.println("=====================");
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                incluir();
                break;
            case 2:
                alterar();
                break;
            case 3:
                excluir();
                break;
            case 4:
                exibirPorId();
                break;
            case 5:
                exibirTodos();
                break;
            case 6:
                salvarDados();
                break;
            case 7:
                recuperarDados();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção invalida.");
        }
    }

    private static void incluir() {
        System.out.println("F - Fisica | J - Juridica");
        String tipo = scanner.nextLine();
        switch (tipo) {

            case "F":
            case "f":
                PessoaFisica pf = criarPessoaFisica();
                repoFisica.inserir(pf);
                break;
            case "J":
            case "j":
                PessoaJuridica pj = criarPessoaJuridica();
                repoJuridica.inserir(pj);
                break;
            default:
                System.out.println("Tipo invalido.");
                break;
        }
    }

    private static void alterar() {
        System.out.println("F - Fisica | J - Juridica");
        String tipo = scanner.nextLine();

        System.out.print("Digite o ID da pessoa: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        switch (tipo) {
            case "F":
            case "f":
                PessoaFisica pf = repoFisica.obter(id);
                if (pf != null) {
                    System.out.println("Dados atuais: ");
                    pf.exibir();
                    System.out.println("Digite os novos dados:");
                    PessoaFisica novaPf = criarPessoaFisica();
                    novaPf.setId(id);
                    repoFisica.alterar(novaPf);
                } else {
                    System.out.println("Pessoa fisica não encontrada.");
                }
                break;
            case "J":
            case "j":
                PessoaJuridica pj = repoJuridica.obter(id);
                if (pj != null) {
                    System.out.println("Dados atuais: ");
                    pj.exibir();
                    System.out.println("Digite os novos dados:");
                    PessoaJuridica novaPj = criarPessoaJuridica();
                    novaPj.setId(id);
                    repoJuridica.alterar(novaPj);
                } else {
                    System.out.println("Pessoa juridica não encontrada.");
                }
                break;
            default:
                System.out.println("Tipo invalido.");
                break;
        }
    }

    private static void excluir() {
        System.out.println("F - Fisica | J - Juridica");
        String tipo = scanner.nextLine();

        System.out.print("Digite o ID da pessoa: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        switch (tipo) {
            case "F":
            case "f":
                repoFisica.excluir(id);
                System.out.println("Pessoa fisica removida.");
                break;
            case "J":
            case "j":
                repoJuridica.excluir(id);
                System.out.println("Pessoa juridica removida.");
                break;
            default:
                System.out.println("Tipo invalido.");
                break;
        }
    }

    private static void exibirPorId() {
        System.out.println("F - Fisica | J - Juridica");
        String tipo = scanner.nextLine();

        System.out.print("Digite o ID da pessoa: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        switch (tipo) {
            case "F":
            case "f":
                PessoaFisica pf = repoFisica.obter(id);
                if (pf != null) {
                    pf.exibir();
                } else {
                    System.out.println("Pessoa fisica não encontrada.");
                }
                break;
            case "J":
            case "j":
                PessoaJuridica pj = repoJuridica.obter(id);
                if (pj != null) {
                    pj.exibir();
                } else {
                    System.out.println("Pessoa juridica não encontrada.");
                }
                break;
            default:
                System.out.println("Tipo invalido.");
                break;
        }
    }

    private static void exibirTodos() {
        System.out.println("F - Fisica | J - Juridica");
        String tipo = scanner.nextLine();

        switch (tipo) {
            case "F":
            case "f":
                for (PessoaFisica pf : repoFisica.obterTodos()) {
                    pf.exibir();
                }
                break;
            case "J":
            case "j":
                for (PessoaJuridica pj : repoJuridica.obterTodos()) {
                    pj.exibir();
                }
                break;
            default:
                System.out.println("Tipo invalido.");
                break;
        }
    }

    private static void salvarDados() {
        System.out.println("Digite o prefixo do arquivo");
        String prefixo = scanner.nextLine();

        try {
            repoFisica.persistir(prefixo + ".fisica.bin");
            repoJuridica.persistir(prefixo + ".juridica.bin");
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    private static void recuperarDados() {
        System.out.println("Digite o prefixo do arquivo");
        String prefixo = scanner.nextLine();

        try {
            repoFisica.recuperar(prefixo + ".fisica.bin");
            repoJuridica.recuperar(prefixo + ".juridica.bin");
            System.out.println("Dados recuperados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar dados: " + e.getMessage());
        }
    }

    private static PessoaFisica criarPessoaFisica() {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        return new PessoaFisica(0, nome, cpf, idade);
    }

    private static PessoaJuridica criarPessoaJuridica() {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CNPJ: ");
        String cnpj = scanner.nextLine();
        return new PessoaJuridica(0, nome, cnpj);
    }
}

/* ABAIXO ESTÁ A MAIN DO PRIMEIRO PROCEDIMENTO
    public static void main(String[] args) {
        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
        PessoaFisica pessoaFisica1 = new PessoaFisica(1, "Edson", "123.456.789-10", 24);
        PessoaFisica pessoaFisica2 = new PessoaFisica(2, "Sara", "987.654.321-00", 25);
        repo1.inserir(pessoaFisica1);
        repo1.inserir(pessoaFisica2);
        String arquivoPessoaFisica = "pessoasFisicas.bin";
        try {
            repo1.persistir(arquivoPessoaFisica);
            System.out.println("Dados de Pessoa Fisica Armazenados.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar pessoas fisicas: " + e.getMessage());
        }

        PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
        try {
            repo2.recuperar(arquivoPessoaFisica);
            System.out.println("Dados de Pessoa Fisica Recuperados.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar pessoas fisicas: " + e.getMessage());
        }

        for (PessoaFisica pessoaFisica : repo2.obterTodos()) {
            pessoaFisica.exibir();
        }

        PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
        PessoaJuridica pessoaJuridica1 = new PessoaJuridica(3, "Cyber Coffe+", "01.234.567/0001-89");
        PessoaJuridica pessoaJuridica2 = new PessoaJuridica(4, "Nutri Angel", "98.765.432/0001-00");
        repo3.inserir(pessoaJuridica1);
        repo3.inserir(pessoaJuridica2);

        String arquivoPessoaJuridica = "pessoasJuridicas.bin";
        try {
            repo3.persistir(arquivoPessoaJuridica);
            System.out.println("Dados de Pessoa Juridica Armazenados.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar pessoas juridicas: " + e.getMessage());
        }

        PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
        try {
            repo4.recuperar(arquivoPessoaJuridica);
            System.out.println("Dados de Pessoa Juridica Recuperados.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar pessoas juridicas: " + e.getMessage());
        }

        for (PessoaJuridica pessoa : repo4.obterTodos()) {
            pessoa.exibir();
        }

    }
 */
