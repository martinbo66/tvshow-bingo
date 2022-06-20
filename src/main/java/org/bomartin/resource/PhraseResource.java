package org.bomartin.resource;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import org.bomartin.model.Phrase;
import org.bomartin.repository.PhraseRepository;

import java.util.UUID;

public interface PhraseResource extends PanacheRepositoryResource<PhraseRepository, Phrase, UUID> {
}