package br.com.erick.ceep.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.erick.ceep.R;
import br.com.erick.ceep.dao.NotaDAO;
import br.com.erick.ceep.model.Nota;
import br.com.erick.ceep.ui.recyclerview.adapter.ListaNotasAdapter;

public class ListaNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);
        List<Nota> todasNotas = notasDeExemplo();
        configuraRecyclerView(todasNotas);
    }

    private List<Nota> notasDeExemplo() {
        NotaDAO dao = new NotaDAO();

        dao.insere(new Nota("Primeira nota", "Descricao pequena"),
                    new Nota("Segunda nota", "Segunda descricao é bem maior que a da primeira nota"));

        List<Nota> todasNotas = dao.todos();
        return todasNotas;
    }

    private void configuraRecyclerView(List<Nota> todasNotas) {
        RecyclerView listaNotas = configuraAdapter(todasNotas);
    }

    @NonNull
    private RecyclerView configuraAdapter(List<Nota> todasNotas) {
        RecyclerView listaNotas = findViewById(R.id.lista_notas_recyclerview);
        listaNotas.setAdapter(new ListaNotasAdapter(this, todasNotas));
        return listaNotas;
    }
}