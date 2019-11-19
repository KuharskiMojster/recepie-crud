package si.kuharskimojster.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;
}
