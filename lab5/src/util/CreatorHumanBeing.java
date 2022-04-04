package util;

import data.*;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CreatorHumanBeing {
    private static Scanner input = new Scanner(System.in);
    private static boolean boolcheck = false;
    private static boolean minutescheck = false;
    private static boolean speedcheck = false;
    private static boolean weaponcheck = false;
    private static int exeStatus = 0;

    public HumanBeing createHumanBeing(){
        boolcheck = false;
        minutescheck = false;
        speedcheck = false;
        weaponcheck = false;
        if (exeStatus == 0){
            input = new Scanner(System.in);
        }
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

//        System.out.println("begin");
//        System.out.println(name);
//        System.out.println(coordinates);
//        System.out.println(coordinates);
//        System.out.println(creationDate);
//        System.out.println(realHero);
//        System.out.println(hasToothpick);
//        System.out.println(impactSpeed);
//        System.out.println(minutesOfWaiting);
//        System.out.println(weaponType);
//        System.out.println(mood);
//        System.out.println(car);
//        System.out.println(boolcheck);
//        System.out.println(minutescheck);
//        System.out.println(speedcheck);
//        System.out.println(weaponcheck);
//        System.out.println("end");

        if (!boolcheck && name != null && coordinates != null
                && (mood != null) && (car != null) && !minutescheck && !speedcheck && !weaponcheck){
            return new HumanBeing(name, coordinates, creationDate,
                    realHero, hasToothpick, impactSpeed,
                    minutesOfWaiting, weaponType, mood, car);
        }

        return null;


    }

    public static int getExeStatus() {
        return exeStatus;
    }

    public static void setExeStatus(int exeStatus) {
        CreatorHumanBeing.exeStatus = exeStatus;
    }

    public static void setInput(Scanner input) {
        CreatorHumanBeing.input = input;
    }

    public String getName(){
        try {
            String line;
            System.out.println(Text.getBlueText("Name:"));
            line = input.nextLine();
            line = line.trim();

            while(!getNameCorrectStatus(line) && exeStatus == 0){
                System.out.println(Text.getBlueText("Name:"));
                line = input.nextLine();
                line = line.trim();
            }

            if (!getNameCorrectStatus(line)) return null;
            return line;
        } catch (NoSuchElementException e){
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

    public Coordinates getCoordinates(){
        try{
            String line;
            long x = 0;
            Long y;
            boolean x_is_wrong = false;
            System.out.println(Text.getBlueText("Coordinate x:"));
            line = input.nextLine();

            while(exeStatus == 0 && !getCoordinateXCorrectStatus(line)){
                System.out.println(Text.getBlueText("Coordinate x:"));
                line = input.nextLine();
            }

            if (!getCoordinateXCorrectStatus(line)) x_is_wrong = true;
            else x = Long.parseLong(line);


            System.out.println(Text.getBlueText("Coordinate y:"));
            line = input.nextLine();

            while(!getCoordinateYCorrectStatus(line) && exeStatus == 0){
                System.out.println(Text.getBlueText("Coordinate y:"));
                line = input.nextLine();
            }

            if (!getCoordinateYCorrectStatus(line) || x_is_wrong) return null;
            y = Long.parseLong(line);
            return new Coordinates(x, y);

        } catch (NoSuchElementException e){
            return null;
        }

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
            System.out.println(Text.getRedText("Coordinate Y should be not null Long!"));
            return false;
        }
    }

    public boolean getRealHero(){
        try {
            String line;
            System.out.println(Text.getBlueText("realHero (true or false):"));
            line = input.nextLine();
            while(!getRealHeroCorrectStatus(line) && exeStatus == 0){
                System.out.println(Text.getBlueText("realHero (true or false):"));
                line = input.nextLine();
            }
            return line.equals("true");
        } catch (NoSuchElementException e){
            boolcheck = true;
            return false;
        }
    }

    public boolean getRealHeroCorrectStatus(String line){
        if (!line.equals("true") && !line.equals("false")) {
            System.out.println(Text.getRedText("RealHero should be true or false!"));
            boolcheck = true;
            return false;
        }else {
            boolcheck = false;
            return true;
        }
    }

    public boolean getHasToothpick(){
        try {
            String line;
            System.out.println(Text.getBlueText("HasToothpick (true or false):"));
            line = input.nextLine();
            while(!getHasToothpickCorrectStatus(line) && exeStatus == 0){
                System.out.println(Text.getBlueText("HasToothpick (true or false):"));
                line = input.nextLine();
            }
            return line.equals("true");
        } catch (NoSuchElementException e) {
            boolcheck = true;
            return false;
        }
    }

    public boolean getHasToothpickCorrectStatus(String line){
        if (!line.equals("true") && !line.equals("false")) {
            System.out.println(Text.getRedText("HasToothpick should be true or false!"));
            boolcheck = true;
            return false;
        }else {
            boolcheck = false;
            return true;
        }
    }

    public Float getImpactSpeed(){
        try {
            String line;
            System.out.println(Text.getBlueText("ImpactSpeed:"));
            line = input.nextLine();

            while(!getImpactSpeedCorrectStatus(line) && exeStatus == 0){
                System.out.println(Text.getBlueText("ImpactSpeed:"));
                line = input.nextLine();
            }

            if (line.equals("")){
                speedcheck = false;
                return null;
            }

            if(!getImpactSpeedCorrectStatus(line)) return null;
            return Float.parseFloat(line);
        } catch (NoSuchElementException e){
            speedcheck = true;
            return null;
        }
    }

    public boolean getImpactSpeedCorrectStatus(String line){
        if (line.equals("")){
            speedcheck = false;
            return true;
        }
        try {
            Float.parseFloat(line);
            speedcheck = false;
            return true;
        } catch (NumberFormatException exception) {
            System.out.println(Text.getRedText("Impact speed should be float! " +
                    "But also it could be null! To make it null you had to input an empty string!"));
            speedcheck = true;
            return false;
        }
    }

    public long getMinutesOfWaiting(){
        try {
            String line;
            System.out.println(Text.getBlueText("MinutesOfWaiting:"));
            line = input.nextLine();

            while(!getMinutesOfWaitingCorrectStatus(line) && exeStatus == 0){
                System.out.println(Text.getBlueText("MinutesOfWaiting:"));
                line = input.nextLine();
            }

            if(!getMinutesOfWaitingCorrectStatus(line)) return 0;
            return Long.parseLong(line);
        } catch (NoSuchElementException e){
            minutescheck = true;
            return 0;
        }
    }

    public boolean getMinutesOfWaitingCorrectStatus(String line){
        try {
            Long.parseLong(line);
            minutescheck = false;
            return true;
        } catch (NumberFormatException exception) {
            System.out.println(Text.getRedText("Minutes of waiting should be long!"));
            minutescheck = true;
            return false;
        }
    }

    public WeaponType getWeaponType(){
        try {
            String line;
            System.out.println(Text.getBlueText("WeaponType (HAMMER, AXE, PISTOL, SHOTGUN or BAT):"));
            line = input.nextLine();

            while (!getWeaponTypeCorrectStatus(line) && exeStatus == 0 ){
                System.out.println(Text.getBlueText("WeaponType (HAMMER, AXE, PISTOL, SHOTGUN or BAT):"));
                line = input.nextLine();
            }

            if (line.equals("")){
                weaponcheck = false;
                return null;
            }

            if (!getWeaponTypeCorrectStatus(line)) return null;
            return WeaponType.valueOf(line);
        } catch (NoSuchElementException e){
            weaponcheck = true;
            return null;
        }
    }

    public boolean getWeaponTypeCorrectStatus(String line){
        if (line.equals("")){
            weaponcheck = false;
            return true;
        }
        if (!WeaponType.isIncludeElement(line)){
            System.out.println(Text.getRedText("Wrong WeaponType! But it's ok, this field will be null"));
            weaponcheck = true;
        }else{
            weaponcheck = false;
        }
        return WeaponType.isIncludeElement(line);
    }

    public Mood getMood(){
        try {
            String line;
            System.out.println(Text.getBlueText("Mood (SADNESS, APATHY, CALM, RAGE or FRENZY):"));
            line = input.nextLine();

            while(!getMoodCorrectStatus(line) && exeStatus == 0){
                System.out.println(Text.getBlueText("Mood (SADNESS, APATHY, CALM, RAGE or FRENZY):"));
                line = input.nextLine();
            }

            if (!getMoodCorrectStatus(line)) return null;
            return Mood.valueOf(line);
        } catch (NoSuchElementException e){
            return null;
        }
    }

    public boolean getMoodCorrectStatus(String line){
        if (!Mood.isIncludeElement(line)){
            System.out.println(Text.getRedText("Wrong Mood!"));
        }
        return Mood.isIncludeElement(line);
    }

    public Car getCar(){
        try {
            String line;
            Boolean cool;
            System.out.println(Text.getBlueText("Car.cool (true or false):"));
            line = input.nextLine();
            while (!getCoolCorrectStatus(line) && exeStatus == 0){
                System.out.println(Text.getBlueText("Car.cool (true or false):"));
                line = input.nextLine();
            }
            cool = line.equals("true");
            return new Car(cool);
        } catch (NoSuchElementException e){
            boolcheck = true;
            return null;
        }
    }

    public boolean getCoolCorrectStatus(String line){
        if (!line.equals("true") && !line.equals("false")) {
            System.out.println(Text.getRedText("Cool should be true or false!"));
            boolcheck = true;
            return false;
        }else{
            boolcheck = false;
            return true;
        }
    }
}
