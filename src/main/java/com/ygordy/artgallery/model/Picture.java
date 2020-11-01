package com.ygordy.artgallery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
@Table(name = "pictures")
public class Picture implements Serializable {

    private static final long serialVersionUID = 3467272201750582912L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    @NonNull
    private Long version;

    @NonNull
    private String title;
    private int width;
    private int height;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "artist_id", nullable = false)
    @JsonIgnore
    private Artist artist;

    @ManyToMany
    @JoinTable(name = "picture_style",
            joinColumns = @JoinColumn(name = "picture_id"),
            inverseJoinColumns = @JoinColumn(name = "style_id"))
    private Set<Style> styles;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Picture picture = (Picture) o;
        if (picture.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), picture.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Picture{"
                + "id=" + getId()
                + ", version='" + getVersion() + "'"
                + ", title='" + getTitle() + "'"
                + ", width='" + getWidth() + "'"
                + ", height='" + getHeight() + "'"
//                if(artist != null) {
//                    + ", artist='" + getArtist().toString() + "'";
//                }

                + "}";
    }
}
