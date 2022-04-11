package data;

import com.google.gson.annotations.Expose;

import java.util.Objects;
/**
 * Class for human's car
 */
public class Car {
    @Expose
    private final Boolean cool; //Поле не может быть null

    public Car(Boolean cool) {
        this.cool = cool;
    }

    public Boolean getCool() {
        return cool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return cool.equals(car.cool);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cool);
    }
}
