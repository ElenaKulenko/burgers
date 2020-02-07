package burgers.service;

import burgers.dao.Burger;
import burgers.repository.BurgersCrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        addBurgerToCache(savedBurger);
    }

    @Override
    @Cacheable(value = "burgers", key = "#id")
    @Nullable
    public Burger get(long id) {
        LOG.info("Getting burger from DB with ID {}.", id);
        Optional<Burger> burgerById = burgersCrudRepository.findById(id);
        if (burgerById.isPresent()) {
            return burgerById.get();
        } else {
            LOG.error("Burger with ID {} not found", id);
            return null;
        }
    }

    @Override
    public void change(Burger burger) {
        Burger changedBurger = burgersCrudRepository.save(burger);
        addBurgerToCache(changedBurger);
    }

    @Override
    @CacheEvict(value = "burgers", key = "#id")
    public void delete(long id) {
        burgersCrudRepository.deleteById(id);
    }

    private void addBurgerToCache(Burger burger) {
        Cache burgersCache = cacheManager.getCache("burgers");
        if (Objects.nonNull(burgersCache)) {
            burgersCache.put(burger.getId(), burger);
        } else {
            LOG.error("Burgers cache is null");
        }
    }
}
