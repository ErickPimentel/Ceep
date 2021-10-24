package br.com.erick.ceep.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import br.com.erick.ceep.R;
import br.com.erick.ceep.model.Nota;


public class ListaNotasAdapter extends BaseAdapter {

    private final Context context;
    private final List<Nota> notas;

    public ListaNotasAdapter(Context context, List<Nota> notas) {
        this.context = context;
        this.notas = notas;
    }

    @Override
    public int getCount() {
        return notas.size();
    }

    @Override
    public Nota getItem(int posicao) {
        return notas.get(posicao);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View renderedView = LayoutInflater.from(context).inflate(R.layout.item_nota, viewGroup, false);
        Nota note = notas.get(position);

        TextView title = renderedView.findViewById(R.id.item_nota_titulo);
        title.setText(note.getTitulo());

        TextView descricao = renderedView.findViewById(R.id.item_nota_descricao);
        descricao.setText(note.getDescricao());

        return renderedView;
    }
}
