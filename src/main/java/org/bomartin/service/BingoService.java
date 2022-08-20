package org.bomartin.service;

import org.bomartin.model.Phrase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@ApplicationScoped
public class BingoService {

    public List<String> squareTitlesFromPhrases(List<Phrase> allThePhrases) {
        return new Random()
                .ints(0, allThePhrases.size())
                .distinct()
                .limit(24)
                .mapToObj(i -> allThePhrases.get(i).getWords())
                .collect(Collectors.toList());
    }
}
