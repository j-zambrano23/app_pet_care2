package com.example.mipetcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "registro_usuarios.db";
    public static final String TABLE_USUARIOS = "usuarios";
    public static final String TABLE_MASCOTAS = "mascotas";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USUARIO";
    public static final String COL_3 = "CORREO";
    public static final String COL_4 = "CONTRASEÑA";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USUARIOS + " (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "USUARIO TEXT, " +
                "CORREO TEXT, " +
                "CONTRASEÑA TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_MASCOTAS + " (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NOMBRE_MASCOTA TEXT, " +
                "ANIMAL TEXT, " +
                "RAZA TEXT, " +
                "EDAD TEXT, " +
                "SEXO TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MASCOTAS);
        onCreate(db);
    }

    public boolean insertarDatos(String usuario, String correo, String contraseña) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, usuario);
        contentValues.put(COL_3, correo);
        contentValues.put(COL_4, contraseña);
        long result = db.insert(TABLE_USUARIOS, null, contentValues);
        return result != -1;
    }

    public boolean verificarUsuario(String usuario, String contraseña) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS +
                " WHERE USUARIO=? AND CONTRASEÑA=?", new String[]{usuario, contraseña});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean insertarMascota(String nombreMascota, String animal, String raza, String edad, String sexo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOMBRE_MASCOTA", nombreMascota);
        contentValues.put("ANIMAL", animal);
        contentValues.put("RAZA", raza);
        contentValues.put("EDAD", edad);
        contentValues.put("SEXO", sexo);
        long result = db.insert(TABLE_MASCOTAS, null, contentValues);
        db.close();
        return result != -1;
    }
}
