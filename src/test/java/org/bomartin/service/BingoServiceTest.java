package org.bomartin.service;

import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.bomartin.model.BingoCardDto;
import org.bomartin.model.Phrase;
import org.bomartin.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

@QuarkusTest
class BingoServiceTest {

    @Inject
    TvShowRepository mockTvShowRepo;
    @Inject
    PhraseRepository mockPhraseRepo;

    BingoService sut;

    @BeforeEach
    public  void setup() {
        QuarkusMock.installMockForInstance(new MockTvShowRepository(), mockTvShowRepo);
        QuarkusMock.installMockForInstance(new MockPhraseRepository(), mockPhraseRepo);
        sut = new BingoService(mockTvShowRepo, mockPhraseRepo);
    }

    @Test
    void squareTitlesFromPhrases() {
        List<Phrase> inputPhrases = BingoTestData.randomPhrases(30);
        Assertions.assertEquals(30, inputPhrases.size());

        List<String> actual = sut.squareTitlesFromPhrases(inputPhrases);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(24, actual.size());
    }

    @Test
    void generateCardForShow() {

        BingoCardDto actual = sut.generateCardForShow(BingoTestData.SHOW_ID);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(24, actual.getSquareTitleList().size());
        Assertions.assertEquals(BingoTestData.CENTER_SQUARE, actual.getCenterSquare());

    }
}