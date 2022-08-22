package org.bomartin.resource;

import org.bomartin.model.BingoCardDto;
import org.bomartin.service.BingoService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.UUID;

@Path("bingo")
public class BingoResource {

    private final BingoService bingoService;

    @Inject
    public BingoResource(BingoService bingoService) {
        this.bingoService = bingoService;
    }

    @GET
    @Path("{tvshowId}/card")
    public BingoCardDto getCardForShow(@PathParam("tvshowId") UUID tvshowId) {
        return bingoService.generateCardForShow(tvshowId);
    }
}
