package com.example.bootsbootique.entities;

import com.example.bootsbootique.enums.BootType;

import javax.persistence.*;

@Entity
@Table(name="BOOTS")
public class Boot {

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name="TYPE")
    @Enumerated(EnumType.STRING)
    private BootType type;

    @Column(name="SIZE")
    private float size;

    @Column(name="QUANTITY")
    private int quantity;

    @Column(name="MATERIAL")
    private String material;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BootType getType() {
        return type;
    }

    public void setType(BootType type) {
        this.type = type;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}