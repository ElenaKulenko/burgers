package burgers.service;

import burgers.Application;
import burgers.dao.Burger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class BurgerServiceTest {

    @Autowired
    private BurgerService burgerService;

    @Test
    @Transactional
    public void getAssortment() {

        List<Burger> expectedResult = List.of(
                new Burger().setName("Wopper1").setWeight(100).setCalorificValue(100),
                new Burger().setName("Wopper2").setWeight(100).setCalorificValue(100),
                new Burger().setName("Wopper3").setWeight(100).setCalorificValue(100));

        burgerService.add(expectedResult.get(0));
        burgerService.add(expectedResult.get(1));
        burgerService.add(expectedResult.get(2));

        Assert.assertEquals(expectedResult.size(), burgerService.getAssortment().size());
        Assert.assertEquals(expectedResult, burgerService.getAssortment());
    }
}