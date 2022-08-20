package org.bomartin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BingoCardDto implements Serializable {
    private final UUID tvshowId;
    private final String showTitle;
    private final String gameTitle;
    private final String centerSquare;

    private List<String> squareTitleList;

    public BingoCardDto(UUID tvshowId, String showTitle, String gameTitle, String centerSquare) {
        this.tvshowId = tvshowId;
        this.showTitle = showTitle;
        this.gameTitle = gameTitle;
        this.centerSquare = centerSquare;
        this.squareTitleList = new ArrayList<>();
    }

    public BingoCardDto(TvShow showEntity) {
        this.tvshowId = showEntity.id;
        this.showTitle = showEntity.showTitle;
        this.gameTitle = showEntity.gameTitle;
        this.centerSquare = showEntity.centerSquare;
        this.squareTitleList = new ArrayList<>();
    }

    public void setSquareTitleList(List<String> squareTitles) {
        this.squareTitleList = squareTitles;
    }

    public UUID getTvshowId() {
        return tvshowId;
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

    public List<String> getSquareTitleList() {
        return squareTitleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BingoCardDto entity = (BingoCardDto) o;
        return Objects.equals(this.tvshowId, entity.tvshowId) &&
                Objects.equals(this.showTitle, entity.showTitle) &&
                Objects.equals(this.gameTitle, entity.gameTitle) &&
                Objects.equals(this.centerSquare, entity.centerSquare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tvshowId, showTitle, gameTitle, centerSquare);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "showId = " + tvshowId + ", " +
                "showTitle = " + showTitle + ", " +
                "gameTitle = " + gameTitle + ", " +
                "centerSquare = " + centerSquare + ", " +
                "countOfSquareTitles = " + squareTitleList.size() + ")";
    }
}
