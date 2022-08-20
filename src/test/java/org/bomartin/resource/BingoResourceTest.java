package org.bomartin.resource;

import io.quarkus.arc.Priority;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.bomartin.model.BingoCardDto;
import org.bomartin.model.Phrase;
import org.bomartin.model.TvShow;
import org.bomartin.repository.PhraseRepository;
import org.bomartin.repository.TvShowRepository;
import org.bomartin.service.BingoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@QuarkusTest
class BingoResourceTest {

    public static final String SHOW_TITLE = "Good Times";
    public static final UUID SHOW_ID = UUID.randomUUID();
    public static final String CENTER_SQUARE = "Dynomite!";
    public static final String GAME_TITLE = "Ain't we lucky we got 'em?";
    @Inject
    TvShowRepository tvShowRepository;
    @Inject
    PhraseRepository phraseRepository;

    @Test
    public void testGetCardForShow_good() {
        QuarkusMock.installMockForInstance(new MockTvShowRepository(), tvShowRepository);
        QuarkusMock.installMockForInstance(new MockPhraseRepository(), phraseRepository);
        BingoResource sut = new BingoResource(tvShowRepository, phraseRepository, new BingoService());

        BingoCardDto actual = sut.getCardForShow(UUID.randomUUID());

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual.getCenterSquare(), CENTER_SQUARE);
        Assertions.assertEquals(actual.getShowTitle(), SHOW_TITLE);
        Assertions.assertEquals(actual.getGameTitle(), GAME_TITLE);
        Assertions.assertNotNull(actual.getSquareTitleList());
        Assertions.assertEquals(3, actual.getSquareTitleList().size());
    }
    @Priority(1)
    @Alternative
    @ApplicationScoped
    public static class MockTvShowRepository extends TvShowRepository {
        public TvShow findById(UUID id) {
            TvShow tvShow = new TvShow();
            tvShow.showTitle = SHOW_TITLE;
            tvShow.centerSquare = CENTER_SQUARE;
            tvShow.id = id;
            tvShow.gameTitle = GAME_TITLE;
            return tvShow;
        }
    }
    @Priority(1)
    @Alternative
    @ApplicationScoped
    public static class MockPhraseRepository extends PhraseRepository {
        @Override
        public List<Phrase> findByTvShowId(UUID id) {
            return List.of(
                    phraseOf(id, "Buffalo Butt"),
                    phraseOf(id, "JJ's Girlfriends"),
                    phraseOf(id, "Raising money")
            );
        }

        private Phrase phraseOf(UUID showId, String words) {
            Phrase phrase = new Phrase();
            phrase.setId(UUID.randomUUID());
            phrase.setTvshowId(showId);
            phrase.setWords(words);
            return phrase;
        }
    }

}