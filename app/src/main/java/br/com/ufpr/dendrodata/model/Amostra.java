package br.com.ufpr.dendrodata.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Amostra implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id = 0;
    private String parcela;
    private String coordX;
    private String coordY;
    private String observacao;

    @Ignore
    public Amostra(long id, String parcela, String coordX, String coordY, String observacao) {
        this.id = id;
        this.parcela = parcela;
        this.coordX = coordX;
        this.coordY = coordY;
        this.observacao = observacao;
    }

    public Amostra() {

    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public String getCoordX() {
        return coordX;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordY() {
        return coordY;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @NonNull
    @Override
    public String toString() {
        return parcela;
    }
}
