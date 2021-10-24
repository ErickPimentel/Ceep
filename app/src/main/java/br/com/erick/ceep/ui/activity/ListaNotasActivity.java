package br.com.erick.ceep.ui.activity;

import static br.com.erick.ceep.ui.activity.NotaActivityConstantes.CHAVE_NOTA;
import static br.com.erick.ceep.ui.activity.NotaActivityConstantes.CODIGO_REQUISICAO_INSERE_NOTA;
import static br.com.erick.ceep.ui.activity.NotaActivityConstantes.CODIGO_RESULTADO_NOTA_CRIADA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.erick.ceep.R;
import br.com.erick.ceep.dao.NotaDAO;
import br.com.erick.ceep.model.Nota;
import br.com.erick.ceep.ui.recyclerview.adapter.ListaNotasAdapter;

public class ListaNotasActivity extends AppCompatActivity {


    private ListaNotasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);
        List<Nota> todasNotas = pegaTodasNotas();
        configuraRecyclerView(todasNotas);

        configuraBotaoInsereNota();

    }

    private void configuraBotaoInsereNota() {
        View botaoInsereNota = findViewById(R.id.lista_notas_insere_nota);
        botaoInsereNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaiParaFormularioNotaActivity();
            }
        });
    }

    private void vaiParaFormularioNotaActivity() {
        Intent iniciaFormularioNota = new Intent(ListaNotasActivity.this, FormularioNotaActivity.class);
        startActivityForResult(iniciaFormularioNota, CODIGO_REQUISICAO_INSERE_NOTA);
    }

    private List<Nota> pegaTodasNotas() {
        NotaDAO dao = new NotaDAO();
        List<Nota> todasNotas = dao.todos();
        return todasNotas;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(ehResultadoComNota(requestCode, resultCode, data)){
            Nota notaRecebida = (Nota) data.getSerializableExtra(CHAVE_NOTA);
            adiciona(notaRecebida);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void adiciona(Nota notaRecebida) {
        new NotaDAO().insere(notaRecebida);
        adapter.adiciona(notaRecebida);
    }

    private boolean ehResultadoComNota(int requestCode, int resultCode, @Nullable Intent data) {
        return ehCodigoRequisicaoInsereNota(requestCode) && ehCodigoResultadoNotaCriada(resultCode) && temNota(data);
    }

    private boolean temNota(@Nullable Intent data) {
        return data.hasExtra(CHAVE_NOTA);
    }

    private boolean ehCodigoResultadoNotaCriada(int resultCode) {
        return resultCode == CODIGO_RESULTADO_NOTA_CRIADA;
    }

    private boolean ehCodigoRequisicaoInsereNota(int requestCode) {
        return requestCode == CODIGO_REQUISICAO_INSERE_NOTA;
    }

    private void configuraRecyclerView(List<Nota> todasNotas) {
        RecyclerView listaNotas = findViewById(R.id.lista_notas_recyclerview);
        configuraAdapter(todasNotas, listaNotas);

    }

    @NonNull
    private void configuraAdapter(List<Nota> todasNotas, RecyclerView listaNotas) {
        adapter = new ListaNotasAdapter(this, todasNotas);
        listaNotas.setAdapter(adapter);
    }
}