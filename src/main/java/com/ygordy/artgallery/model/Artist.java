package com.ygordy.artgallery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
//@EqualsAndHashCode
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
    private String fullName;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SELECT)
    @JsonIgnore
    private Set<Picture> pictures;

    public Artist(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Artist artist = (Artist) o;
        if (artist.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), artist.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Artist{"
                + "id=" + getId()
                + ", fullName='" + getFullName() + "'"
                + "}";
    }
}
