package org.bomartin.service;

import io.quarkus.logging.Log;
import org.bomartin.model.BingoCardDto;
import org.bomartin.model.Phrase;
import org.bomartin.model.TvShow;
import org.bomartin.repository.PhraseRepository;
import org.bomartin.repository.TvShowRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class BingoService {
    private final TvShowRepository tvShowRepository;
    private final PhraseRepository phraseRepository;

    public BingoService(TvShowRepository tvShowRepository,
                        PhraseRepository phraseRepository) {
        this.tvShowRepository = tvShowRepository;
        this.phraseRepository = phraseRepository;
    }

    /**
     * Pull 24 random bingo square titles from the full list of phrases for a show.
     *
     * @param allThePhrases List of phrases
     * @return Random list of strings for the bingo squares
     */
    public List<String> squareTitlesFromPhrases(List<Phrase> allThePhrases) {

        if (null == allThePhrases || allThePhrases.isEmpty()) {
            return new ArrayList<>();
        }
        return new Random()
                .ints(0, allThePhrases.size())
                .distinct()
                .limit(24)
                .mapToObj(i -> allThePhrases.get(i).getWords())
                .collect(Collectors.toList());
    }

    /**
     * Generate the data for a full bingo card.
     *
     * @param tvshowId TV show ID
     * @return Bingo card DTO
     */
    public BingoCardDto generateCardForShow(final UUID tvshowId) {
        // Retrieve the show
        TvShow tvshowEntity = tvShowRepository.findById(tvshowId);
        if (null == tvshowEntity) {
            throw new NotFoundException(String.format("TV Show %s not found", tvshowId));
        }
        // Retrieve the phrases
        List<Phrase> phrases = phraseRepository.findByTvShowId(tvshowId);
        if (null == phrases || phrases.size() < 24) {
            Log.debugf("Not enough phrases found for show %s", tvshowEntity.showTitle);
            phrases = new ArrayList<>();
        }

        BingoCardDto bingoCard = new BingoCardDto(tvshowEntity);
        bingoCard.setSquareTitleList(squareTitlesFromPhrases(phrases));
        return bingoCard;
    }
}
