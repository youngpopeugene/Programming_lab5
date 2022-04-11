package data;
/**
 * Enum for human's weapon
 */
public enum WeaponType {
    HAMMER("HAMMER"),
    AXE("AXE"),
    PISTOL("PISTOL"),
    SHOTGUN("SHOTGUN"),
    BAT("BAT");

    private final String weaponType;

    WeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String getStringWeaponType() {
        return weaponType;
    }

    public static boolean isIncludeElement(String weaponType){
        for (WeaponType x : WeaponType.values()){
            if (weaponType.equals(x.getStringWeaponType())){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "WeaponType{" +
                "weaponType='" + weaponType + '\'' +
                '}';
    }
}
