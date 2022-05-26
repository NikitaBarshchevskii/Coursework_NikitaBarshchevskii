package com.company.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@ToString
@NoArgsConstructor
@Entity(name = "SALES")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToOne(
            targetEntity = Goods.class,
            cascade = { CascadeType.MERGE, CascadeType.REFRESH }
    )
    @JoinColumn(
            name = "GOOD_ID",
            foreignKey = @ForeignKey(
                    name = "fk_sales_goods"
            )
    )
    private Goods goodId;

    @Column(
            name = "GOOD_COUNT"
    )
    private long goodCount;
    @Column(
            name = "CREATE_DATE",
            nullable = false,
            updatable = false
    )
    private String createDate;

    public Sales(Goods goodId, long goodCount, String createDate) {
        this.goodId = goodId;
        this.goodCount = goodCount;
        this.createDate = createDate;
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

    public long getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(long goodCount) {
        this.goodCount = goodCount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
