package org.bomartin.repository;

import io.quarkus.arc.Priority;
import org.bomartin.model.Phrase;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.List;
import java.util.UUID;

@Priority(1)
@Alternative
@ApplicationScoped
public class MockPhraseRepository extends PhraseRepository {
    @Override
    public List<Phrase> findByTvShowId(UUID id) {
//        return List.of(
//                BingoTestData.phraseOf(id, "Buffalo Butt"),
//                BingoTestData.phraseOf(id, "JJ's Girlfriends"),
//                BingoTestData.phraseOf(id, "Raising money")
//        );
        return BingoTestData.randomPhrases(40);
    }

}
