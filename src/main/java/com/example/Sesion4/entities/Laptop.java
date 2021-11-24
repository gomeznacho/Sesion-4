package com.example.Sesion4.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name= "laptops")
@ApiModel("informacion sobre la clase Laptop")
public class Laptop {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("no se que mensaje")

    private Long id;
    private String marca;
    private String modelo;
    @ApiModelProperty("version del tal y el cual")
    private Double version;

    public Laptop(){

    }

    public Laptop(Long id, String marca, String modelo, Double version){
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.version = version;
    }

    public Long getId(){

        return id;
    }
    public void setId(Long id){

        this.id = id;
    }
    public String getMarca(){

        return marca;
    }
     public void setMarca(String marca){

        this.marca = marca;
     }
    public String getModelo(){

        return modelo;
    }
     public void setModelo(String modelo){

        this.modelo = modelo;
     }

    public Double getVersion(){

        return version;
    }
    public void setVersion(Double version){

        this.version = version;
    }


}
