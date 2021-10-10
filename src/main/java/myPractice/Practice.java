package myPractice;

import myPractice.domain.Car;
import myPractice.domain.Champion;
import myPractice.domain.Continent;
import util.QueryRunner;

import javax.persistence.EntityManager;
import java.util.stream.IntStream;

public class Practice {
    public static void main(String[] args) {

        QueryRunner.execute(em -> {
            System.out.println("=========");

            Champion champion = new Champion("ww");
            em.persist(champion);

            Car car = create("car");
            em.persist(car);

            Champion champion1 = new Champion("Asd");
            em.persist(champion1);

            System.out.println("=========");

            System.out.println("champion.getId = " + champion.getId());
            System.out.println("car.getId = " + car.getId());
            System.out.println("champion1.getId = " + champion1.getId());
        });
    }

    private static void testIDENTITY(EntityManager em){
        IntStream.range(0, 500).forEach(i->{
            Car car = create("car" + i);
            em.persist(car);
        });
    }

    private static Car create(String name){
        Car car = new Car();
        car.setName(name);
        car.setContinent(Continent.ASIA);
        car.setiDontWantToBeInDB(234);
        return car;
    }

}
