package com.wallace.grubstertest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private EditText caixaNome, caixaPhone, caixaEmail, caixaQuantidade;
	private Button btnSalvar, btnListagem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		caixaNome 		= (EditText)findViewById(R.id.editText1);
		caixaPhone 		= (EditText)findViewById(R.id.editText2);
		caixaEmail 		= (EditText)findViewById(R.id.editText3);
		caixaQuantidade = (EditText)findViewById(R.id.editText4);
		
		btnSalvar 	= (Button)findViewById(R.id.button1);
		btnListagem = (Button)findViewById(R.id.button2);
		
		btnListagem.setOnClickListener(this);
		btnSalvar.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		if(v == btnListagem){
			Intent intent = new Intent(this, ListagemActivity.class);
			startActivity(intent);
		}else{
			//empacotando dados no Bean, e enviado ao BD
			try{
				ReservaBean b = new ReservaBean();
				
				b.name 	   = caixaNome.getText().toString();
				b.phone    = caixaPhone.getText().toString();
				b.email    = caixaEmail.getText().toString();
				b.quantity = Integer.parseInt(caixaQuantidade.getText().toString());
				b.status   = "FILA";
				
				ReservaBD banco  = new ReservaBD(this);
				banco.cadastrarReserva(b);
				
				Toast.makeText(this, "Salvo na Fila!", Toast.LENGTH_SHORT).show();

				
			}catch(Exception ex){
				Toast.makeText(this, "Dados inv‡lidos", Toast.LENGTH_SHORT).show();
			}
			
		}
		
		
	}

}
