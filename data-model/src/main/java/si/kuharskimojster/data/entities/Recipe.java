package si.kuharskimojster.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Recipe extends AbstractEntity {

    @Column
    private String title;

    @Column
    private int time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
