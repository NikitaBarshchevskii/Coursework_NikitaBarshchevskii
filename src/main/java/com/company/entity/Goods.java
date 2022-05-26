package com.company.entity;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@Entity(name = "GOODS")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(
            name = "NAME",
            nullable = false

    )
    private String name;

    @Column(
            name = "PRIORITY",
            nullable = false
    )
    private double priority;

    public Goods(String name,
                 double priority) {
        this.name = name;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }
}
