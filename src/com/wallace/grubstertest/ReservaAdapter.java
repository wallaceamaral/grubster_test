package com.wallace.grubstertest;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ReservaAdapter extends BaseAdapter {
	
	private Context contexto;
	private ArrayList<ReservaBean> conteudo;
	private LayoutInflater inflater;
	
	public ReservaAdapter(Context tela, ArrayList<ReservaBean> lista){
		conteudo = lista;
		contexto = tela;
		inflater = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return conteudo.size();
	}

	@Override
	public Object getItem(int position) {
		return conteudo.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//verificando se Ž necess‡rio criar um novo item
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_reserva, null);
		}
		
		//carregando sub-itens do convertView
		TextView txtNome 	   = (TextView)convertView.findViewById(R.id.textView1);
		TextView txtPhone 	   = (TextView)convertView.findViewById(R.id.textView2);
		TextView txtEmail 	   = (TextView)convertView.findViewById(R.id.textView3);
		TextView txtQuantidade = (TextView)convertView.findViewById(R.id.textView4);
		TextView txtStatus 	   = (TextView)convertView.findViewById(R.id.textView5);

		//colocando os dados referente a linha clicada
		ReservaBean b = conteudo.get(position);
		
		txtNome.setText(b.name);
		txtPhone.setText(b.phone);
		txtEmail.setText(b.email);
		txtQuantidade.setText("QTDE:"+b.quantity);
		txtStatus.setText(b.status);
		
		return convertView;
	}

}
