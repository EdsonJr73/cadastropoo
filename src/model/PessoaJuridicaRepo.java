package model;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author edson-202308892185
 */
public class PessoaJuridicaRepo {

    private ArrayList<PessoaJuridica> listaPessoaJuridica = new ArrayList<>();
    private int proximoId = 1;

    public void inserir(PessoaJuridica pessoaJuridica) {
        pessoaJuridica.setId(proximoId);
        listaPessoaJuridica.add(pessoaJuridica);
        proximoId++;
    }

    public void alterar(PessoaJuridica pessoaJuridica) {
        for (int i = 0; i < listaPessoaJuridica.size(); i++) {
            if (listaPessoaJuridica.get(i).getId() == pessoaJuridica.getId()) {
                listaPessoaJuridica.set(i, pessoaJuridica);
                break;
            }
        }
    }

    public void excluir(int id) {
        listaPessoaJuridica.removeIf(pessoaJuridica -> pessoaJuridica.getId() == id);
    }

    public PessoaJuridica obter(int id) {
        return listaPessoaJuridica.stream()
                .filter(pessoaJuridica -> pessoaJuridica.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public ArrayList<PessoaJuridica> obterTodos() {
        return listaPessoaJuridica;
    }

    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(listaPessoaJuridica);
        }
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            listaPessoaJuridica = (ArrayList<PessoaJuridica>) ois.readObject();
        }
    }
}
