package os.expert.demo.helidon;


import jakarta.data.Order;
import jakarta.data.Sort;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

import java.util.List;
import java.util.logging.Logger;

@Path("/machines")
@ApplicationScoped
public class MachineResource {

    private static final Logger LOGGER = Logger.getLogger(MachineResource.class.getName());

    public static final Order<Machine> ORDER_MANUFACTURER = Order.by(Sort.asc("manufacturer"));

    private final MachineRepository repository;

    @Inject
    public MachineResource(MachineRepository repository) {
        this.repository = repository;
    }

    @GET
    public List<Machine> get(@QueryParam("page") @DefaultValue("1") int page,@QueryParam("page_size") @DefaultValue("10") int pageSize) {
        LOGGER.info("Get machines from page " + page + " with page size " + pageSize);
        Page<Machine> machines = this.repository.findAll(PageRequest.ofPage(page).size(pageSize), ORDER_MANUFACTURER);
        return machines.content();
    }

    @PUT
    public void save(Machine machine) {
        LOGGER.info("Saving a machine " + machine);
        this.repository.save(machine);
    }
}
