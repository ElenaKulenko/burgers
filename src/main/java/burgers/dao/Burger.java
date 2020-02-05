package burgers.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BURGERS")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Burger {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int weight;
    private int calorificValue;
}
