package org.bomartin.resource;

import io.quarkus.logging.Log;
import org.bomartin.model.BingoCardDto;
import org.bomartin.model.Phrase;
import org.bomartin.model.TvShow;
import org.bomartin.repository.PhraseRepository;
import org.bomartin.repository.TvShowRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("bingo")
public class BingoResource {
    private final TvShowRepository tvShowRepository;
    private final PhraseRepository phraseRepository;

    @Inject
    public BingoResource(TvShowRepository tvShowRepository, PhraseRepository phraseRepository) {
        this.tvShowRepository = tvShowRepository;
        this.phraseRepository = phraseRepository;
    }

    @GET
    @Path("{tvshowId}/card")
    public BingoCardDto getCardForShow(@PathParam("tvshowId") UUID tvshowId) {
        // TODO: Most of this logic should be in a service class/method

        // Retrieve the show
        TvShow tvshowEntity = tvShowRepository.findById(tvshowId);
        if (null == tvshowEntity) {
            throw new NotFoundException(String.format("TV Show %s not found", tvshowId));
        }
        // Retrieve the phrases
        List<Phrase> phrases = phraseRepository.findByTvShowId(tvshowId);
        if (null == phrases || phrases.isEmpty()) {
            Log.debugf("No phrases found for show %s", tvshowEntity.showTitle);
            phrases = new ArrayList<>();
        }

        BingoCardDto bingoCard = new BingoCardDto(tvshowEntity);
        bingoCard.setPhraseList(phrases);
        return bingoCard;
    }
}
