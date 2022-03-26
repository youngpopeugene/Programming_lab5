package util;

import data.*;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CreatorHumanBeing {
    private static Scanner input = new Scanner(System.in);
    private static boolean boolcheck = false;

    public HumanBeing createHumanBeing(){
        boolcheck = false;
        String name = getName();
        Coordinates coordinates = getCoordinates();
        LocalDateTime creationDate = LocalDateTime.now();
        boolean realHero = getRealHero();
        boolean hasToothpick = getHasToothpick();
        Float impactSpeed = getImpactSpeed();
        long minutesOfWaiting = getMinutesOfWaiting();
        WeaponType weaponType = getWeaponType();
        Mood mood = getMood();
        Car car = getCar();

        if (!boolcheck && name != null && coordinates != null
                && (mood != null) && (car != null) && (minutesOfWaiting>=0) && (weaponType != null)){

            return new HumanBeing(name, coordinates, creationDate,
                    realHero, hasToothpick, impactSpeed,
                    minutesOfWaiting, weaponType, mood, car);
        }
        return null;


    }

    public static void setInput(Scanner input) {
        CreatorHumanBeing.input = input;
    }

    public String getName(){
        try {
            String line;
            System.out.println(Text.getBlueText("Name:"));
            line = input.nextLine();
            if (!getNameCorrectStatus(line)) return null;
            return line;
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage()); //можно потом удалить или нет
            return null;
        }
    }

    public Coordinates getCoordinates(){
        try{
            String line;
            long x = 0;
            Long y;
            boolean x_is_wrong = false;
            System.out.println(Text.getBlueText("Coordinate x:"));
            line = input.nextLine();
            if (!getCoordinateXCorrectStatus(line)) x_is_wrong = true;
            else x = Long.parseLong(line);


            System.out.println(Text.getBlueText("Coordinate y:"));
            line = input.nextLine();
            if (!getCoordinateYCorrectStatus(line) || x_is_wrong) return null;
            y = Long.parseLong(line);
            return new Coordinates(x, y);

        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public boolean getRealHero(){
        try {
            String line;
            System.out.println(Text.getBlueText("realHero (true or false):"));
            line = input.nextLine();
            if (!line.equals("true") && !line.equals("false")) {
                System.out.println(Text.getRedText("RealHero should be true or false"));
                boolcheck = true;
            }
            return line.equals("true");
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean getHasToothpick(){
        try {
            String line;
            System.out.println(Text.getBlueText("HasToothpick (true or false):"));
            line = input.nextLine();
            if (!line.equals("true") && !line.equals("false")) {
                System.out.println(Text.getRedText("HasToothpick should be true or false!"));
                boolcheck = true;
            }
            return line.equals("true");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Float getImpactSpeed(){
        try {
            String line;
            System.out.println(Text.getBlueText("ImpactSpeed:"));
            line = input.nextLine();
            if(!getImpactSpeedCorrectStatus(line)) return null;
            return Float.parseFloat(line);
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public long getMinutesOfWaiting(){
        try {
            String line;
            System.out.println(Text.getBlueText("MinutesOfWaiting:"));
            line = input.nextLine();
            if (!getMinutesOfWaitingCorrectStatus(line)) return -1;
            return Long.parseLong(line);
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public WeaponType getWeaponType(){
        try {
            String line;
            System.out.println(Text.getBlueText("WeaponType (HAMMER, AXE, PISTOL, SHOTGUN or BAT):"));
            line = input.nextLine();
            if (!getWeaponTypeCorrectStatus(line)) return null;
            return WeaponType.valueOf(line);
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Mood getMood(){
        try {
            String line;
            System.out.println(Text.getBlueText("Mood (SADNESS, APATHY, CALM, RAGE or FRENZY):"));
            line = input.nextLine();
            if (!getMoodCorrectStatus(line)) return null;
            return Mood.valueOf(line);
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Car getCar(){
        try {
            String line;
            Boolean cool;
            System.out.println(Text.getBlueText("Car.cool (true or false):"));
            line = input.nextLine();
            if (!line.equals("true") && !line.equals("false")) {
                System.out.println(Text.getRedText("Cool should be true or false!"));
                boolcheck = true;
            }
            cool = line.equals("true");
            return new Car(cool);
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean getNameCorrectStatus(String line){
        if (!(line != null && !line.equals(""))){
            System.out.println(Text.getRedText("Name cannot be null, string cannot be empty!"));
            return false;
        }
        return true;

    }

    public boolean getCoordinateXCorrectStatus(String line){
        try {
            Long.parseLong(line);
            if (Long.parseLong(line) <= 695){
                return true;
            }else System.out.println(Text.getRedText("Max value of coordinate X : 695 !"));
            return false;
        } catch (NumberFormatException exception) {
            System.out.println(Text.getRedText("Coordinate X should be long!"));
            return false;
        }
    }

    public boolean getCoordinateYCorrectStatus(String line){
        try {
            Long.parseLong(line);
            return true;
        } catch (NumberFormatException exception) {
            System.out.println(Text.getRedText("Coordinate Y should be long!"));
            return false;
        }
    }

    public boolean getImpactSpeedCorrectStatus(String line){
        try {
            Float.parseFloat(line);
            return true;
        } catch (NumberFormatException exception) {
            System.out.println(Text.getRedText("Impact speed should be float!"));
            return false;
        }
    }

    public boolean getMinutesOfWaitingCorrectStatus(String line){
        try {
            Long.parseLong(line);
            if (Long.parseLong(line) >= 0){
                return true;
            }
            System.out.println(Text.getRedText("Obvious, minutes of waiting cannot be negative :)"));
            return false;
        } catch (NumberFormatException exception) {
            System.out.println(Text.getRedText("Minutes of waiting should be long!"));
            return false;
        }
    }

    public boolean getWeaponTypeCorrectStatus(String line){
        if (!WeaponType.isIncludeElement(line)){
            System.out.println(Text.getRedText("Wrong WeaponType!"));
        }
        return WeaponType.isIncludeElement(line);
    }

    public boolean getMoodCorrectStatus(String line){
        if (!Mood.isIncludeElement(line)){
            System.out.println(Text.getRedText("Wrong Mood!"));
        }
        return Mood.isIncludeElement(line);
    }
}
