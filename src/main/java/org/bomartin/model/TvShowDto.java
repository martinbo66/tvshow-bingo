package org.bomartin.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class TvShowDto implements Serializable {
    private final UUID id;
    private final String showTitle;
    private final String gameTitle;
    private final String centerSquare;

    public TvShowDto(UUID id, String showTitle, String gameTitle, String centerSquare) {
        this.id = id;
        this.showTitle = showTitle;
        this.gameTitle = gameTitle;
        this.centerSquare = centerSquare;
    }

    public UUID getId() {
        return id;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public String getCenterSquare() {
        return centerSquare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TvShowDto entity = (TvShowDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.showTitle, entity.showTitle) &&
                Objects.equals(this.gameTitle, entity.gameTitle) &&
                Objects.equals(this.centerSquare, entity.centerSquare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, showTitle, gameTitle, centerSquare);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "showTitle = " + showTitle + ", " +
                "gameTitle = " + gameTitle + ", " +
                "centerSquare = " + centerSquare + ")";
    }
}
