package org.bomartin.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;
@Entity
@Table(name = "tvshow")
public class TvShow extends PanacheEntityBase {
    @Id
    @Column(name = "id", nullable = false)
    public UUID id;

    @Column(name = "show_title")
    public String showTitle;

    @Column(name = "game_title")
    public String gameTitle;

    @Column(name = "center_square")
    public String centerSquare;

}