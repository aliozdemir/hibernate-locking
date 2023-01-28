package com.ozdemir.hibernatelocking.model.entity;


import lombok.*;

import javax.persistence.*;

/*
* @Version : optimistic lock mekanizmasını aktif eder.
* */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
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

}
