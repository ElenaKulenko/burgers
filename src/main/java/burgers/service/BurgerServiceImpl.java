package burgers.service;

import burgers.dao.Burger;
import burgers.repository.BurgersCrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BurgerServiceImpl implements BurgerService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private BurgersCrudRepository burgersCrudRepository;

    @Autowired
    CacheManager cacheManager;

    @Override
    public List<Burger> getAssortment() {
        return (List<Burger>) burgersCrudRepository.findAll();
    }

    @Override
    public void add(Burger burger) {
        Burger savedBurger = burgersCrudRepository.save(burger);
        cacheManager.getCache("burgers").put(savedBurger.getId(), savedBurger);
    }

    @Override
    @Cacheable(value = "burgers", key = "#id")
    public Burger get(long id) {
        LOG.info("Getting burger from DB with ID {}.", id);
        return burgersCrudRepository.findById(id).get();
    }

    @Override
    public void change(Burger burger) {
        Burger changedBurger = burgersCrudRepository.save(burger);
        cacheManager.getCache("burgers").put(changedBurger.getId(), changedBurger);
    }

    @Override
    @CacheEvict(value = "burgers", key = "#id")
    public void delete(long id) {
        burgersCrudRepository.deleteById(id);
    }

}
