package os.expert.demo.helidon;

import jakarta.data.repository.BasicRepository;
import jakarta.data.repository.Repository;

@Repository
public interface MachineRepository extends BasicRepository<Machine, String> {
}
