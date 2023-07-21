package hsos.swa.warenkorb.boundry;

import hsos.swa.anzeige.boundry.AnzeigeDTO;
import hsos.swa.warenkorb.control.WarenkorbController;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.hibernate.Hibernate;

@Path("warenkorb")
@RequestScoped
@Transactional
public class WarenkorbPageResource {

    @Inject
    Template warenkorb;

    @Inject
    WarenkorbController warenkorbController;

    @GET
    @Path("/{id}")
    public TemplateInstance getWarenkorbById(@PathParam("id") long id) {
        WarenkorbDTO warenkorbDTO = warenkorbController.getWarenkorbById(id);
        if (warenkorbDTO != null) {
            return warenkorb.data("warenkorb", warenkorbDTO);
        } else {
            throw new NotFoundException();
        }
    }
}
