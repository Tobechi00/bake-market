package com.occasionalbaker.bakersite.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;


@Entity
@Table(name = "cake_catalogue")
public class CakeCatalogue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private String cakeName;

    @Column
    private String cakePrice;

    @Column
    private String cakeDescription;

    //change to byte
    @Lob
    private byte[] cakeImage;

    @Column
    @NotNull
    private Boolean isAvailable;

    public CakeCatalogue(){

    }

    public CakeCatalogue(String cakeName, String cakePrice, String cakeDescription,byte[] cakeImage) {
        this.cakeName = cakeName;
        this.cakePrice = cakePrice;
        this.cakeDescription = cakeDescription;
        this.cakeImage = cakeImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCakeName() {
        return cakeName;
    }

    public void setCakeName(String cakeName) {
        this.cakeName = cakeName;
    }

    public String getCakePrice() {
        return cakePrice;
    }

    public void setCakePrice(String cakePrice) {
        this.cakePrice = cakePrice;
    }

    public String getCakeDescription() {
        return cakeDescription;
    }

    public void setCakeDescription(String cakeDescription) {
        this.cakeDescription = cakeDescription;
    }

    public byte[] getCakeImage() {
        return cakeImage;
    }

    public void setCakeImage(byte[] cakeImage) {
        this.cakeImage = cakeImage;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "CakeCatalogue{" +
                "id=" + id +
                ", cakeName='" + cakeName + '\'' +
                ", cakePrice='" + cakePrice + '\'' +
                ", cakeDescription='" + cakeDescription + '\'' +
                ", cakeImage=" + Arrays.toString(cakeImage) +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
