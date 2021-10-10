package myPractice.domain;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "seq_cham_gen", sequenceName = "seq_cham")
public class Champion {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cham_gen")
    private Long id;

    private String name;

    public Champion(){}

    public Champion(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
