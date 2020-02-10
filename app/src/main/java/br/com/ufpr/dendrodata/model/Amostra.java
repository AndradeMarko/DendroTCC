package br.com.ufpr.dendrodata.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static androidx.room.ForeignKey.CASCADE;

@Entity
public class Amostra implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String numero;
    private String coordX;
    private String coordY;
    private String espacamento;
    private String observacao;
    private Calendar dataCadastro = Calendar.getInstance();
    @ForeignKey(entity = Projeto.class,
            parentColumns = "id",
            childColumns = "projetoId",
            onUpdate = CASCADE,
            onDelete = CASCADE)
    private int projetoId;

    public int getProjetoId() {
        return projetoId;
    }

    public String getEspacamento() {
        return espacamento;
    }

    public void setEspacamento(String espacamento) {
        this.espacamento = espacamento;
    }

    public void setProjetoId(int projetoId) {
        this.projetoId = projetoId;
    }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public String dataFormatada() {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(dataCadastro.getTime());
    }

    public boolean idValido() {
        return id > 0;
    }

    @NonNull
    @Override
    public String toString() {
        return numero;
    }
}
