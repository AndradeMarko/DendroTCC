package br.com.ufpr.dendrodata.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.ufpr.dendrodata.database.converter.ConversorCalendar;
import br.com.ufpr.dendrodata.database.dao.AmostraDAO;
import br.com.ufpr.dendrodata.database.dao.IndividuoDAO;
import br.com.ufpr.dendrodata.database.dao.ProjetoDAO;
import br.com.ufpr.dendrodata.model.Amostra;
import br.com.ufpr.dendrodata.model.Individuo;
import br.com.ufpr.dendrodata.model.Projeto;

@Database(entities = {Projeto.class, Amostra.class, Individuo.class}, version = 8, exportSchema = false)
@TypeConverters({ConversorCalendar.class})

public abstract class DendroDataDatabase extends RoomDatabase {

    public static final String NAME_BD = "dendrodata.db";

    public abstract ProjetoDAO getRoomProjetoDAO();
    public abstract AmostraDAO getRoomAmostraDAO();

    public abstract IndividuoDAO getRoomIndividuoDAO();

    public static DendroDataDatabase getInstance(Context context) {

        return Room.databaseBuilder(context, DendroDataDatabase.class, NAME_BD).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
//                .addMigrations(ALL_MIGRATIONS)
                .build();
    }
}
