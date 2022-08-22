package org.bomartin.repository;

import io.quarkus.arc.Priority;
import org.bomartin.model.TvShow;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.UUID;

import static org.bomartin.repository.BingoTestData.CENTER_SQUARE;
import static org.bomartin.repository.BingoTestData.GAME_TITLE;
import static org.bomartin.repository.BingoTestData.SHOW_TITLE;

@Priority(1)
@Alternative
@ApplicationScoped
public class MockTvShowRepository extends TvShowRepository {

    @Override
    public TvShow findById(UUID id) {
        TvShow tvShow = new TvShow();
        tvShow.showTitle = SHOW_TITLE;
        tvShow.centerSquare = CENTER_SQUARE;
        tvShow.id = id;
        tvShow.gameTitle = GAME_TITLE;
        return tvShow;
    }
}