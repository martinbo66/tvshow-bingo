package org.bomartin.resource;

import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.bomartin.model.BingoCardDto;
import org.bomartin.repository.*;
import org.bomartin.service.BingoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.bomartin.repository.BingoTestData.*;

@QuarkusTest
class BingoResourceTest {

    @Inject
    TvShowRepository mockTvShowRepo;
    @Inject
    PhraseRepository mockPhraseRepo;
    @Inject
    BingoService bingoService;
    @Test
    public void testGetCardForShow_good() {
        QuarkusMock.installMockForInstance(new MockBingoService(mockTvShowRepo, mockPhraseRepo), bingoService);
        BingoResource sut = new BingoResource(bingoService);

        BingoCardDto actual = sut.getCardForShow(SHOW_ID);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual.getCenterSquare(), CENTER_SQUARE);
        Assertions.assertEquals(actual.getShowTitle(), SHOW_TITLE);
        Assertions.assertEquals(actual.getGameTitle(), GAME_TITLE);
        Assertions.assertNotNull(actual.getSquareTitleList());
        Assertions.assertEquals(24, actual.getSquareTitleList().size());
    }

}