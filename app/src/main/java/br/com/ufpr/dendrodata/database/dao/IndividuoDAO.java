package br.com.ufpr.dendrodata.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.ufpr.dendrodata.model.Individuo;

@Dao
public interface IndividuoDAO {

    @Insert
    void salva(Individuo individuo);

    @Query("SELECT i.* FROM Individuo i JOIN Amostra a ON i.amostraId = a.id WHERE i.amostraId = :amostraId")
    List<Individuo> todos(int amostraId);

    @Delete
    void remove(Individuo individuo);

    @Update
    void edita(Individuo individuo);

    @Query("SELECT * FROM Individuo")
    List<Individuo> todosIndividuos();
}
