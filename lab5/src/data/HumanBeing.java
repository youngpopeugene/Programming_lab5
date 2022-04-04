package data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import util.CollectionManager;

import java.time.LocalDateTime;
import java.util.Objects;

public class HumanBeing implements Comparable<HumanBeing>{
    @Expose
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @Expose
    private final String name; //Поле не может быть null, Строка не может быть пустой
    @Expose
    private final Coordinates coordinates; //Поле не может быть null

    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @Expose(deserialize = false)
    @SerializedName("creationDate")
    private String creationDateStr;
    @Expose
    private final boolean realHero;
    @Expose
    private final boolean hasToothpick;
    @Expose
    private final Float impactSpeed; //Поле может быть null
    @Expose
    private final long minutesOfWaiting;
    @Expose
    private final WeaponType weaponType; //Поле может быть null
    @Expose
    private final Mood mood; //Поле не может быть null
    @Expose
    private final Car car; //Поле не может быть null

    public HumanBeing(String name, Coordinates coordinates, LocalDateTime creationDate, boolean realHero,
                      boolean hasToothpick, Float impactSpeed, long minutesOfWaiting, WeaponType weaponType, Mood mood, Car car) {

        id = 1L;
        while(CollectionManager.getUsedId().contains(id)){
            id+=1;
        }
        CollectionManager.getUsedId().add(id);

        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.minutesOfWaiting = minutesOfWaiting;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
        this.creationDateStr = String.valueOf(creationDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isRealHero() {
        return realHero;
    }

    public Float getImpactSpeed() {
        return impactSpeed;
    }

    public void setCreationDate(LocalDateTime creationDate){
        this.creationDate = creationDate;
    }
    public void setCreationDateStr(String creationDateStr){
        this.creationDateStr = creationDateStr;
    }
    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getCreationDateStr() {
        return creationDateStr;
    }

    public boolean isHasToothpick() {
        return hasToothpick;
    }

    public long getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public Mood getMood() {
        return mood;
    }

    public Car getCar() {
        return car;
    }

    public boolean validation(){
        if(name == null || name.equals("")) return false;
        if(coordinates == null || coordinates.getX() > 695) return false;
        if(mood == null) return false;
        if(creationDate == null) return false;
        if(id < 0) return false;
        return car != null;
    }

    @Override
    public int compareTo(HumanBeing o) {
        return this.name.compareTo(o.name);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanBeing that = (HumanBeing) o;
        return realHero == that.realHero && hasToothpick == that.hasToothpick
                && minutesOfWaiting == that.minutesOfWaiting && Objects.equals(id, that.id)
                && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates)
                && Objects.equals(creationDate, that.creationDate) && Objects.equals(creationDateStr, that.creationDateStr)
                && Objects.equals(impactSpeed, that.impactSpeed) && weaponType == that.weaponType && mood == that.mood
                && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, creationDateStr, realHero, hasToothpick, impactSpeed, minutesOfWaiting, weaponType, mood, car);
    }

    @Override
    public String toString() {
       return "HumanBeing { " + "id = " + id +
               ", name = " + name +
               ", coordinate X = " + coordinates.getX() +
               ", coordinate Y = " + coordinates.getY() +
               ", creationDate = " + creationDate +
               ", realHero = " + realHero +
               ", hasToothpick = " + hasToothpick +
               ", impactSpeed = " + impactSpeed +
               ", minutesOfWaiting = " + minutesOfWaiting +
               ", weaponType = " + weaponType +
               ", mood = " + mood +
               ", car.cool = " + car.getCool()
               + " }";
    }
}
