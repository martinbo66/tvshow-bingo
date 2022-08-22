package org.bomartin.repository;

import io.quarkus.arc.Priority;
import org.bomartin.service.BingoService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@Priority(1)
@Alternative
@ApplicationScoped
public class MockBingoService extends BingoService {

    public MockBingoService() {
        super(new MockTvShowRepository(), new MockPhraseRepository());
    }

    public MockBingoService(TvShowRepository tvShowRepository, PhraseRepository phraseRepository) {
        super(tvShowRepository, phraseRepository);
    }
}
