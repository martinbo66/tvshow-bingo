package org.bomartin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class BingoCardDto implements Serializable {
    private final UUID tvshowId;
    private final String showTitle;
    private final String gameTitle;
    private final String centerSquare;

    private List<PhraseDto> phraseList;

    public BingoCardDto(UUID tvshowId, String showTitle, String gameTitle, String centerSquare) {
        this.tvshowId = tvshowId;
        this.showTitle = showTitle;
        this.gameTitle = gameTitle;
        this.centerSquare = centerSquare;
        this.phraseList = new ArrayList<>();
    }

    public BingoCardDto(TvShow showEntity) {
        this.tvshowId = showEntity.id;
        this.showTitle = showEntity.showTitle;
        this.gameTitle = showEntity.gameTitle;
        this.centerSquare = showEntity.centerSquare;
        this.phraseList = new ArrayList<>();
    }

    public void setPhraseList(List<Phrase> phrases) {
        this.phraseList = phrases.stream().map(PhraseDto::new).collect(Collectors.toUnmodifiableList());
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

    public List<PhraseDto> getPhraseList() {
        return phraseList;
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
                "id = " + tvshowId + ", " +
                "showTitle = " + showTitle + ", " +
                "gameTitle = " + gameTitle + ", " +
                "centerSquare = " + centerSquare + ")";
    }
}
