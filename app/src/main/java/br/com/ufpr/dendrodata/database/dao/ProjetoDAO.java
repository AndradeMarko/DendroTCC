package br.com.ufpr.dendrodata.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.ufpr.dendrodata.model.Projeto;

@Dao
public interface ProjetoDAO {

    @Insert
    void salva(Projeto projeto);

    @Query("SELECT * FROM projeto")
    List<Projeto> todos();

    @Delete
    void remove(Projeto projeto);

    @Update
    void edita(Projeto projeto);
}
