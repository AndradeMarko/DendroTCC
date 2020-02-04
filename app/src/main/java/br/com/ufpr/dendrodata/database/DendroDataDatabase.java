package br.com.ufpr.dendrodata.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import br.com.ufpr.dendrodata.model.Amostra;
import br.com.ufpr.dendrodata.model.Individuo;
import br.com.ufpr.dendrodata.model.Projeto;

@Database(entities = {Projeto.class, Amostra.class, Individuo.class}, version = 1, exportSchema = false)
public abstract class DendroDataDatabase extends RoomDatabase {
}
