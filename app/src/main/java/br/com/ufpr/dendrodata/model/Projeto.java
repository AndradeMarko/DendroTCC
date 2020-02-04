package br.com.ufpr.dendrodata.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Projeto implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String codigo;
    private String fazenda;
    private String municipio;
    private String especie;
    private String area;
    private String tamanho;
    private String quantidade;
    private String descricao;

    @Ignore
    public Projeto(String codigo, String fazenda, String municipio, String especie, String area, String tamanho, String quantidade, String descricao) {
        this.codigo = codigo;
        this.fazenda = fazenda;
        this.municipio = municipio;
        this.especie = especie;
        this.area = area;
        this.tamanho = tamanho;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public Projeto() {

    }

    public String getFazenda() {
        return fazenda;
    }

    public void setFazenda(String fazenda) {
        this.fazenda = fazenda;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean idValido() {
        return id > 0;
    }

    @NonNull
    @Override
    public String toString() {
        return codigo;
    }
}
