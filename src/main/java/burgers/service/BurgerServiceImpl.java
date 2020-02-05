package burgers.service;

import burgers.dao.Burger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import burgers.repository.BurgersCrudRepository;

import java.util.List;

@Service
public class BurgerServiceImpl implements BurgerService {

    @Autowired
    private BurgersCrudRepository burgersCrudRepository;

    @Override
    public List<Burger> getAssortment() {
        return (List<Burger>) burgersCrudRepository.findAll();
    }

    @Override
    public void add(Burger burger) {
        burgersCrudRepository.save(burger);
    }

    @Override
    public Burger get(long id) {
        return burgersCrudRepository.findById(id).get();
    }

    @Override
    public void change(Burger burger) {
        burgersCrudRepository.deleteById(burger.getId());
        burgersCrudRepository.save(burger);
    }

    @Override
    public void delete(long id) {
        burgersCrudRepository.deleteById(id);
    }

}
