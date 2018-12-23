package com.github.ngubex.spring.playground.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Ipan Taupik Rahman.
 * Date: 27/12/17
 * Time: 17:07
 * Project spring-data-jpa
 * Package com.github.ipan97.springdatajpa.entity
 */

@Entity
@Table(name = "berita")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Berita implements Serializable {

    @Id
    @GenericGenerator(
            name = "beritaSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequenceName", value = "beritaSequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "beritaSequenceGenerator")
    private Long id;

    @Column(name = "judul", nullable = false)
    private String judul;

    @Column(name = "isi", nullable = false)
    private String isi;

    @Column(name = "waktu_publikasi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuPublikasi;

/*
    @OneToMany(mappedBy = "berita", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Komentar> daftarKomentar;
*/

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "komentar_berita",
            joinColumns = @JoinColumn(name = "id_berita", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_komentar", nullable = false)
    )
    private List<Komentar> daftarKomentar;


}
