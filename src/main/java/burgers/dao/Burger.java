package burgers.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "BURGERS")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Burger implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int weight;
    private int calorificValue;
}
