package org.bomartin.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.bomartin.model.TvShow;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class TvShowRepository implements PanacheRepositoryBase<TvShow, UUID> {
}
