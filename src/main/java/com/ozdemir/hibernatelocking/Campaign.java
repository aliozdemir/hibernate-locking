package com.ozdemir.hibernatelocking;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
* @Version : optimistic lock mekanizmasını aktif eder.
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "campaign")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Version
    @Column(name = "CODE_COUNT")
    private Integer codeCount;

    @Override
    public String toString() {
        return "Campaign{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", codeCount=" + codeCount +
                '}';
    }
}
