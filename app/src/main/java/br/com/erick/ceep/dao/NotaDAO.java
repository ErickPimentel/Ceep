package br.com.erick.ceep.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.erick.ceep.model.Nota;


public class NotaDAO {

    private final static ArrayList<Nota> notas = new ArrayList<>();

    public List<Nota> listAll() {
        return (List<Nota>) notas.clone();
    }

    public void insere(Nota... notas) {
        NotaDAO.notas.addAll(Arrays.asList(notas));
    }

    public void remove(int posicao) {
        notas.remove(posicao);
    }

    public void troca(int initialPosition, int endPosition) {
        Collections.swap(notas, initialPosition, endPosition);
    }

    public void removeTodos() {
        notas.clear();
    }
}
