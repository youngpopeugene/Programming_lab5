package data;

import com.google.gson.annotations.Expose;

import java.util.Objects;

public class Coordinates {
    @Expose
    private long x; //Максимальное значение поля: 695
    @Expose
    private Long y; //Поле не может быть null

    public Coordinates(long x, Long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates that)) return false;
        return x == that.x && y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
