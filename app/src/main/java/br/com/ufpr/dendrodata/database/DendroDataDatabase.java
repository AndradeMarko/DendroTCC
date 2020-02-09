package br.com.ufpr.dendrodata.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.ufpr.dendrodata.database.converter.ConversorCalendar;
import br.com.ufpr.dendrodata.database.dao.ProjetoDAO;
import br.com.ufpr.dendrodata.model.Amostra;
import br.com.ufpr.dendrodata.model.Individuo;
import br.com.ufpr.dendrodata.model.Projeto;

import static br.com.ufpr.dendrodata.database.DendroDataMigrations.ALL_MIGRATIONS;

@Database(entities = {Projeto.class, Amostra.class, Individuo.class}, version = 2, exportSchema = false)
@TypeConverters({ConversorCalendar.class})
public abstract class DendroDataDatabase extends RoomDatabase {

    private static final String NAME_BD = "dendrodata.db";


    public abstract ProjetoDAO getRoomProjetoDAO();

    public static DendroDataDatabase getInstance(Context context) {

        return Room.databaseBuilder(context, DendroDataDatabase.class, NAME_BD).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
//                .addMigrations(ALL_MIGRATIONS)
                .build();
    }
}
