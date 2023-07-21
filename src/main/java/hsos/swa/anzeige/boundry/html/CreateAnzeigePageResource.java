package hsos.swa.anzeige.boundry.html;

import hsos.swa.anzeige.boundry.AnzeigeDTO;
import hsos.swa.anzeige.control.AnzeigeController;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.lang.annotation.Inherited;
import java.util.List;

@Path("erstellen")
public class CreateAnzeigePageResource {

    @Inject
    Template createAnzeige;

    @Inject
    AnzeigeController anzeigeController;

    @Inject
    SecurityIdentity sc;

    @GET
    @Path("")
    public TemplateInstance getPage(){
        return createAnzeige.data("auth", sc.getPrincipal().hashCode());
    }

}
