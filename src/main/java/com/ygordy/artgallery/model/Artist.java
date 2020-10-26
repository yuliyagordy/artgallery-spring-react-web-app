package com.ygordy.artgallery.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "artists")
public class Artist implements Serializable {

    private static final long serialVersionUID = -3586110317805360126L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name", nullable = false, unique = true)
    private String FullName;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Item> items;

}
