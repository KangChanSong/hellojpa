package myPractice.domain;

import javax.persistence.*;

@Entity
//@SequenceGenerator(name = "seq_car_gen", sequenceName = "seq_car", allocationSize = 50)
public class Car {

    @Id
    @GeneratedValue
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Continent continent;

    @Transient
    private int iDontWantToBeInDB;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public int getiDontWantToBeInDB() {
        return iDontWantToBeInDB;
    }

    public void setiDontWantToBeInDB(int iDontWantToBeInDB) {
        this.iDontWantToBeInDB = iDontWantToBeInDB;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Lob
    private String description;
}
