package hsos.swa.anzeige.boundry.html;

import hsos.swa.anzeige.boundry.AnzeigeDTO;
import hsos.swa.anzeige.control.AnzeigeController;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;

@Path("anzeige")
public class AnzeigeDetailPageResource {

    @Inject
    Template anzeige;

    @Inject
    AnzeigeController anzeigeController;

    @GET
    @Path("/{id}")
    public TemplateInstance getAnzeigeById(@PathParam("id") long id) {
        AnzeigeDTO anzeigeDTO = anzeigeController.getAnzeigeById(id);
        if (anzeigeDTO != null) {
            return anzeige.data("anzeige", anzeigeDTO);
        } else {
            return anzeige.data("anzeige", null);
        }
    }

}
