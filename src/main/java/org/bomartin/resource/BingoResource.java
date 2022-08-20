package org.bomartin.resource;

import io.quarkus.logging.Log;
import org.bomartin.model.BingoCardDto;
import org.bomartin.model.Phrase;
import org.bomartin.model.TvShow;
import org.bomartin.repository.PhraseRepository;
import org.bomartin.repository.TvShowRepository;
import org.bomartin.service.BingoService;

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

    private final BingoService bingoService;

    @Inject
    public BingoResource(TvShowRepository tvShowRepository,
                         PhraseRepository phraseRepository,
                         BingoService bingoService) {
        this.tvShowRepository = tvShowRepository;
        this.phraseRepository = phraseRepository;
        this.bingoService = bingoService;
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
        if (null == phrases || phrases.size() < 24) {
            Log.debugf("Not enough phrases found for show %s", tvshowEntity.showTitle);
            phrases = new ArrayList<Phrase>();
        }

        BingoCardDto bingoCard = new BingoCardDto(tvshowEntity);
        bingoCard.setSquareTitleList(bingoService.squareTitlesFromPhrases(phrases));
        return bingoCard;
    }
}
