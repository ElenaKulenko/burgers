package burgers.controller;

import burgers.dao.Burger;
import burgers.service.BurgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/burger")
public class BurgerServiceController {

    @Autowired
    private BurgerService burgerService;

    @GetMapping("/assortment")
    public ResponseEntity<String> getAssortment() {
        List<Burger> assortment = burgerService.getAssortment();
        return assortment.isEmpty() ? ResponseEntity.ok("Empty assortment") : ResponseEntity.ok(assortment.toString());
    }

    @PostMapping("/add")
    public ResponseEntity<String> createBurger(@RequestBody Burger burger) {
        burgerService.add(burger);
        return ResponseEntity.ok("Burger has been added");
    }

    @PutMapping("/change")
    public ResponseEntity<String> changeBurger(@RequestBody Burger burger) {
        burgerService.change(burger);
        return ResponseEntity.ok("Burger has been changed");
    }

    @GetMapping("/get")
    public ResponseEntity<String> getBurger(@RequestParam(value = "id") Long id) {
        Burger burger = burgerService.get(id);
        return Objects.isNull(burger) ? ResponseEntity.noContent().build() : ResponseEntity.ok(burger.toString());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBurger(@RequestParam(value = "id") Long id) {
        burgerService.delete(id);
        return ResponseEntity.ok("Burger has been removed");
    }

}
