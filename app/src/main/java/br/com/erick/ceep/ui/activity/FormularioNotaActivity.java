package br.com.erick.ceep.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import br.com.erick.ceep.R;
import br.com.erick.ceep.dao.NotaDAO;
import br.com.erick.ceep.model.Nota;

public class FormularioNotaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_nota_salva, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_formulario_nota_ic_salva){
            EditText titulo = findViewById(R.id.formulario_nota_titulo);
            EditText descricao = findViewById(R.id.formulario_nota_descricao);

            Nota notaCriada = new Nota(titulo.getText().toString(), descricao.getText().toString());
            new NotaDAO().insere(notaCriada);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }
}