package org.bomartin.repository;

import com.github.javafaker.Faker;
import org.bomartin.model.Phrase;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BingoTestData {

    public static final String SHOW_TITLE = "Good Times";
    public static final UUID SHOW_ID = UUID.fromString("147604ad-8ae6-407d-9d3b-16f000c033f5");
    public static final String CENTER_SQUARE = "Dynomite!";
    public static final String GAME_TITLE = "Ain't we lucky we got 'em?";

public static Phrase phraseOf(UUID showId, String words) {
        Phrase phrase = new Phrase();
        phrase.setId(UUID.randomUUID());
        phrase.setTvshowId(showId);
        phrase.setWords(words);
        return phrase;
    }

    public static List<Phrase> randomPhrases(int countRequested) {
    Faker faker = new Faker();
    return Stream.generate(() -> faker.hipster().word())
            .distinct()
            .limit(countRequested)
            .map(s -> phraseOf(SHOW_ID, s))
            .collect(Collectors.toList());
    }
}
