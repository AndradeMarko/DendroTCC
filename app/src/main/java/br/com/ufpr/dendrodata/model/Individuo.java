package br.com.ufpr.dendrodata.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Individuo implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id = 0;

    public Individuo() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
