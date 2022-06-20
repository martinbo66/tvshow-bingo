package org.bomartin.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "phrase")
public class Phrase extends PanacheEntityBase {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "tvshow_id", nullable = false)
    private UUID tvshowId;

    @Column(name = "words")
    private String words;

}