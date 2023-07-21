package hsos.swa.anzeige.boundry.html;

import hsos.swa.anzeige.boundry.AnzeigeDTO;
import hsos.swa.anzeige.control.AnzeigeController;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/anzeige")
@Transactional
public class FrontPageResource {

    @Inject
    Template frontPage;

    @Inject
    AnzeigeController anzeigeController;

    @GET
    @Path("")
    public TemplateInstance getAllAnzeigen(){
        List<AnzeigeDTO> anzeigen = anzeigeController.getAllAnzeigen();
        return frontPage.data("anzeigen", anzeigen);
    }

}
