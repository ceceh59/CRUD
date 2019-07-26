package com.example.crud;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager extends SQLiteOpenHelper {
    /**
     * Attributs de la class DataBaseManager
     */
    private static final String DATABASE_NAME =  "gescom";
    private static final int DATABASE_VERSION  = 1;

    /*------------------------------------------------------------------------------*/
    /**
     *Constructeur
     */
    public DataBaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*------------------------------------------------------------------------------*/
    /**
     * les méthodes
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String requete =  "CREATE table auteur (idAuteur INTEGER primary key autoincrement, nom text not null)";

        // nous passons notre requete avec la methodes execute
        db.execSQL(requete);

        Log.i("DB","Creation ok");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DB","OnUpgrate ok");
    }

    /**
     * CRUD
     */
    /*------------------------------------------------------------------------*/
//pour ajouter un auteur
    public void insertAuteur(String nom){
        String req =  "INSERT INTO inscrit(nom) values('"+nom+"')";

        this.getReadableDatabase().execSQL(req);

        Log.i("DB","insert ok");

    }

    /*------------------------------------------------------------------------*/
    //pour supprimer un auteur avec son id
    public void deleteAuteur(Integer idAuteur){
        String req =  "DELETE from auteur WHERE idAuteur = values('"+idAuteur+ "')";
        this.getReadableDatabase().execSQL(req);

        Log.i("DB", "suppression ok");
    }
    /*------------------------------------------------------------------------*/
    //Pour reccuperer la liste
    public List<Auteur> getAllAuteur(){
        List<Auteur> listeAuteur =  new ArrayList<>();

        String req =  "select * from auteur";
        Cursor cursor = this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Auteur auteur =  new Auteur(cursor.getInt(0),cursor.getString(1),
                    cursor.getString(2),  cursor.getString(3));

            listeAuteur.add(auteur);

            cursor.moveToNext();
        }
        cursor.close();
        return listeAuteur;
    }
}
