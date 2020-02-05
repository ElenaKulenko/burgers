package burgers.service;

import burgers.dao.Burger;

import java.util.List;

public interface BurgerService {
    List<Burger> getAssortment();
    void add(Burger burger);
    Burger get(long id);
    void change(Burger burger);
    void delete(long id);
}
