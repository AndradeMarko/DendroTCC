package br.com.ufpr.dendrodata.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys =
        {@ForeignKey(entity = Amostra.class,
                parentColumns = "id",
                childColumns = "amostraId",
                onDelete = CASCADE,
                onUpdate = CASCADE),
                @ForeignKey(entity = Projeto.class,
                        parentColumns = "id",
                        childColumns = "projetoId",
                        onUpdate = CASCADE,
                        onDelete = CASCADE)
        }, indices = {@Index("amostraId"), @Index("projetoId")}
)
public class Individuo implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String placa;
    private String dap;
    private String hcomercial;
    private String htotal;
    private String sanidade;
    private String qualidade;
    private String observacao;
    @ForeignKey(entity = Amostra.class,
            parentColumns = "id",
            childColumns = "amostraId",
            onUpdate = CASCADE,
            onDelete = CASCADE)
    private int amostraId;
    @ForeignKey(entity = Projeto.class,
            parentColumns = "id",
            childColumns = "projetoId",
            onUpdate = CASCADE,
            onDelete = CASCADE)
    private int projetoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getDap() {
        return dap;
    }

    public void setDap(String dap) {
        this.dap = dap;
    }

    public String getHcomercial() {
        return hcomercial;
    }

    public void setHcomercial(String hcomercial) {
        this.hcomercial = hcomercial;
    }

    public String getHtotal() {
        return htotal;
    }

    public void setHtotal(String htotal) {
        this.htotal = htotal;
    }

    public String getSanidade() {
        return sanidade;
    }

    public void setSanidade(String sanidade) {
        this.sanidade = sanidade;
    }

    public String getQualidade() {
        return qualidade;
    }

    public void setQualidade(String qualidade) {
        this.qualidade = qualidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getAmostraId() {
        return amostraId;
    }

    public void setAmostraId(int amostraId) {
        this.amostraId = amostraId;
    }

    public int getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(int projetoId) {
        this.projetoId = projetoId;
    }

    public boolean idValido() {
        return id > 0;
    }
//    @NonNull
//    @Override
//    public String toString() {
//        return placa;

//    }


    @NotNull
    @Override
    public String toString() {
        return id + " " + placa + " " + dap + " " + hcomercial + " " + htotal + " " + sanidade + " " + qualidade + " " + observacao + " " + amostraId;
    }

}
