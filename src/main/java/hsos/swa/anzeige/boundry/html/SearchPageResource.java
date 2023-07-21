package hsos.swa.anzeige.boundry.html;

import hsos.swa.anzeige.boundry.AnzeigeDTO;
import hsos.swa.anzeige.control.AnzeigeController;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("search")
public class SearchPageResource {

    @Inject
    Template search;

    @Inject
    AnzeigeController anzeigeController;

    @GET
    @Path("")
    public TemplateInstance getAnzeigeByName(@QueryParam("name") String name) {
        if (name == null) {
            return search.data("anzeigen", anzeigeController.getAllAnzeigen());
        } else {
            AnzeigeDTO AnzeigeDTO = anzeigeController.getAnzeigeByName(name);
            return search.data("anzeigen", AnzeigeDTO);
        }
    }
}
