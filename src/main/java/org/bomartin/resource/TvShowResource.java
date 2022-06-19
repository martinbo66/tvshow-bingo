package org.bomartin.resource;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import org.bomartin.model.TvShow;
import org.bomartin.repository.TvShowRepository;

import javax.ws.rs.Path;
import java.util.UUID;

@Path("/tvshows")
public interface TvShowResource extends PanacheRepositoryResource<TvShowRepository, TvShow, UUID> {
}