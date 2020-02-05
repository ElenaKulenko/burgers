package burgers.repository;

import burgers.dao.Burger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BurgersCrudRepository extends CrudRepository<Burger, Long> {
}
