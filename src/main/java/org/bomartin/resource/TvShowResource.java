package org.bomartin.resource;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import org.bomartin.model.TvShow;
import org.bomartin.repository.TvShowRepository;

import java.util.UUID;

public interface TvShowResource extends PanacheRepositoryResource<TvShowRepository, TvShow, UUID> {
}