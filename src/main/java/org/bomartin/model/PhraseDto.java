package org.bomartin.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class PhraseDto implements Serializable {
    private final UUID id;
    private final UUID tvshowId;
    private final String words;

    public PhraseDto(UUID id, UUID tvshowId, String words) {
        this.id = id;
        this.tvshowId = tvshowId;
        this.words = words;
    }

    public PhraseDto(Phrase entity) {
        this.id = entity.getId();
        this.tvshowId = entity.getTvshowId();
        this.words = entity.getWords();
    }

    public UUID getId() {
        return id;
    }

    public UUID getTvshowId() {
        return tvshowId;
    }

    public String getWords() {
        return words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhraseDto entity = (PhraseDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.tvshowId, entity.tvshowId) &&
                Objects.equals(this.words, entity.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tvshowId, words);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "tvshowId = " + tvshowId + ", " +
                "words = " + words + ")";
    }
}
