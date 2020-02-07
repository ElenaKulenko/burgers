package burgers.controller;

import burgers.dao.Burger;
import burgers.service.BurgerService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Burgers assortment")
    @GetMapping("/assortment")
    public ResponseEntity<String> getAssortment() {
        List<Burger> assortment = burgerService.getAssortment();
        return assortment.isEmpty() ? ResponseEntity.ok("Empty assortment") : ResponseEntity.ok(assortment.toString());
    }

    @ApiOperation(value = "Create burger operation")
    @PostMapping("/add")
    public ResponseEntity<String> createBurger(@RequestBody Burger burger) {
        burgerService.add(burger);
        return ResponseEntity.ok("Burger has been added");
    }

    @ApiOperation(value = "Change burger operation")
    @PutMapping("/change")
    public ResponseEntity<String> changeBurger(@RequestBody Burger burger) {
        burgerService.change(burger);
        return ResponseEntity.ok("Burger has been changed");
    }

    @ApiOperation(value = "Get burger operation. Cached burgers with weight > 200")
    @GetMapping("/get")
    public ResponseEntity<String> getBurger(@RequestParam(value = "id") long id) {
        Burger burger = burgerService.get(id);
        return Objects.isNull(burger) ? ResponseEntity.ok("Not found") : ResponseEntity.ok(burger.toString());
    }

    @ApiOperation(value = "Delete burger operation")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBurger(@RequestParam(value = "id") long id) {
        burgerService.delete(id);
        return ResponseEntity.ok("Burger has been removed");
    }
}
