package model;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author edson-202308892185
 */
public class PessoaFisicaRepo {

    private ArrayList<PessoaFisica> listaPessoaFisica = new ArrayList<>();
    private int proximoId = 1;

    public void inserir(PessoaFisica pessoaFisica) {
        pessoaFisica.setId(proximoId);
        listaPessoaFisica.add(pessoaFisica);
        proximoId++;
    }

    public void alterar(PessoaFisica pessoaFisica) {
        for (int i = 0; i < listaPessoaFisica.size(); i++) {
            if (listaPessoaFisica.get(i).getId() == pessoaFisica.getId()) {
                listaPessoaFisica.set(i, pessoaFisica);
                break;
            }
        }
    }

    public void excluir(int id) {
        listaPessoaFisica.removeIf(pessoaFisica -> pessoaFisica.getId() == id);
    }

    public PessoaFisica obter(int id) {
        return listaPessoaFisica.stream()
                .filter(pessoaFisica -> pessoaFisica.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public ArrayList<PessoaFisica> obterTodos() {
        return listaPessoaFisica;
    }

    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(listaPessoaFisica);
        }
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            listaPessoaFisica = (ArrayList<PessoaFisica>) ois.readObject();
        }
    }
}
