package com.wallace.grubstertest;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ReservaBD {
	//variavel de instancia do BD
	private SQLiteDatabase banco;
	
	public ReservaBD(Context contexto){
		banco = new BancoHelper(contexto).getWritableDatabase();
	}
	
	public boolean cadastrarReserva(ReservaBean reserva){
		boolean sucesso = false;
		
		banco.beginTransaction();

		try{
			
			//Fazendo insers‹o no  banco
			//vinculo as variaveis com os nome das colunas do banco
			ContentValues campos = new ContentValues();
			campos.put("name"    ,reserva.name );
			campos.put("phone"   ,reserva.phone );
			campos.put("email"   ,reserva.email );
			campos.put("quantity",reserva.quantity);
			campos.put("status"  ,reserva.status );

			long resultado = banco.insert("Reserva", null, campos);
			
			if(resultado!=-1){
				sucesso = true;
				banco.setTransactionSuccessful();
			}
			
			
		}finally{
			//dependendo da resposta faz um rollback ou commit
			banco.endTransaction();
		}
		return sucesso;
	}
	
	public ArrayList<ReservaBean> listarReservasFila(){
		Cursor cursor = banco.rawQuery("SELECT id,name,phone,email,quantity,status FROM Reserva WHERE status='FILA' ", null );
		
		ArrayList<ReservaBean> lista = new ArrayList<ReservaBean>();
		
		//percorro cada item da consulta
		while (cursor.moveToNext()){
			ReservaBean reserva = new ReservaBean();
			
			//colocando os dados de cada linha do banco de dados para o bean
			reserva.id = cursor.getInt(0);
			reserva.name 	 = cursor.getString(1);
			reserva.phone 	 = cursor.getString(2);
			reserva.email 	 = cursor.getString(3);
			reserva.quantity = cursor.getInt(4);
			reserva.status 	 = cursor.getString(5);
			
			lista.add(reserva);
		}
		cursor.close();
		
		return lista;
	}
	
	public boolean atualizarReserva(ReservaBean reserva){
		boolean sucesso = false;
		
		banco.beginTransaction();
		
		try{
			ContentValues campos = new ContentValues();
			
			//vinculo as variaveis com o nome das colunas no banco
			campos.put("status"  ,reserva.status );

			long resultado = banco.update("Reserva", campos, "id = "+reserva.id, null);
			
			if(resultado != -1){
				sucesso = true;
				banco.setTransactionSuccessful();
			}
			
		}finally{
			banco.endTransaction();
		}
		
		return sucesso;	
	}
	
}
