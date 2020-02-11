package br.com.ufpr.dendrodata.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.ufpr.dendrodata.model.Amostra;

@Dao
public interface AmostraDAO {

    @Insert
    void salva(Amostra amostra);

    @Query("SELECT a.* FROM Amostra a JOIN Projeto p ON a.projetoId = p.id WHERE a.projetoId = :projetoId")
    List<Amostra> todas(int projetoId);

    @Delete
    void remove(Amostra amostra);

    @Update
    void edita(Amostra amostra);
}
