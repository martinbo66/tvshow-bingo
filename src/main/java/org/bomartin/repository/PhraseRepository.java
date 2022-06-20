package org.bomartin.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.bomartin.model.Phrase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PhraseRepository implements PanacheRepositoryBase<Phrase, UUID> {

    public List<Phrase> findByTvShowId(UUID tvshowId){
        return find("tvshowId", tvshowId).list();
    }
}
