package com.github.ipan97.springplayground.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ipan Taupik Rahman.
 * Date: 27/12/17
 * Time: 17:20
 * Project spring-data-jpa
 * Package com.github.ipan97.springdatajpa.entity
 */
@Entity
@Table(name = "komentar")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Komentar implements Serializable {

    @Id
    @GenericGenerator(
            name = "komentarSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequenceName", value = "komentarSequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "komentarSequenceGenerator")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "isi", nullable = false)
    private String isi;

    @Column(name = "waktu_publikasi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuPublikasi;

/*  @ManyToOne
    @JoinColumn(name = "id_berita", nullable = false)
    private Berita berita;
*/

    @ManyToOne
    @JoinTable(
            name = "komentar_berita",
            joinColumns = @JoinColumn(name = "id_komentar", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "id_berita", insertable = false, updatable = false)
    )
    private Berita berita;
}
