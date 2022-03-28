package data;

import com.google.gson.annotations.Expose;

import java.util.Objects;

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
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return cool.equals(car.cool);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cool);
    }
}
