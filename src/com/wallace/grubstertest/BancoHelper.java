package com.wallace.grubstertest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoHelper extends SQLiteOpenHelper {
	//script de criacao inicial do banco.
	private String scriptInicial = "CREATE TABLE Reserva (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , name VARCHAR NOT NULL , phone VARCHAR NOT NULL ,email VARCHAR NOT NULL , quantity INTEGER NOT NULL , status VARCHAR); INSERT INTO Reserva VALUES(1,'Wallace Amaral','139999-9999','wallace@teste.com',4,'FILA'); INSERT INTO Reserva VALUES(2,'Felipe Vieira','139999-9998','felipe@teste.com',2,'FILA');";
	
	public BancoHelper(Context context){
		super(context, "NOME_BANCO", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(scriptInicial);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
