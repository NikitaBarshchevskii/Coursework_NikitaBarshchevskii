package com.company.entity;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@Entity(name = "WAREHOUSE2")
public class Warehouse2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",
            nullable = false)
    private Long id;

    @ManyToOne(
            targetEntity = Goods.class,
            cascade = { CascadeType.MERGE, CascadeType.REFRESH }
    )
    @JoinColumn(
            name = "GOOD_ID",
            foreignKey = @ForeignKey(
                    name = "fk_warehouse2_goods"
            )
    )
    private Goods goodId;

    @Column(
            name = "GOOD_COUNT"
    )
    private Long goodCount;

    public Warehouse2(Goods goodId, Long goodCount) {
        this.goodId = goodId;
        this.goodCount = goodCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Goods getGoodId() {
        return goodId;
    }

    public void setGoodId(Goods goodId) {
        this.goodId = goodId;
    }

    public Long getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Long goodCount) {
        this.goodCount = goodCount;
    }
}
