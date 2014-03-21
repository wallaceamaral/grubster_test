package com.wallace.grubstertest;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ListagemActivity extends Activity implements OnItemClickListener {

	private ListView lista;
	private ArrayList<ReservaBean> conteudo;
	private ReservaBD banco ;
	private ReservaAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listagem);

		//busco o banco 
		banco = new ReservaBD(this);
		conteudo = banco.listarReservas();
		adapter = new ReservaAdapter(this, conteudo);
	
		//passando o adapter com os dados para a lista
		lista = (ListView) findViewById(R.id.listView1);
		lista.setAdapter(adapter);
		
		lista.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listagem, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		//puxando os  dados do item clicado(o final permite que as innerclass possam acessar)
		final ReservaBean reservaClidada = conteudo.get(arg2);
		
		//criando um alerta
		AlertDialog.Builder alerta = new Builder(this);
		alerta.setTitle("Reservas").setMessage("O que deseja fazer?");
		
		LinearLayout layout = new LinearLayout(this);
		
		alerta.setMessage("Atender o Cliente\n" +"Remover cliente da Fila");
		
		alerta.setView(layout);
		
		//implemento os botoes
		alerta.setNegativeButton("Cancelar", null);
		
		alerta.setNeutralButton("Atender", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			try{
					reservaClidada.status = "ATENDIDO";
					banco.atualizarReserva(reservaClidada);
					
					
				}catch(Exception ex){}
				
			}
		});
		
		
		alerta.setPositiveButton("Remover", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {

				//atualizo o bean e mando para o BD
				try{
					reservaClidada.status = "CANCELADO";
					banco.atualizarReserva(reservaClidada);
					
					
				}catch(Exception ex){}
				
			}
		});
		
		alerta.show();
		
	}


}
