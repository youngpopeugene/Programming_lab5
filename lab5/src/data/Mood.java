package data;

public enum Mood {
    SADNESS("SADNESS"),
    APATHY("APATHY"),
    CALM("CALM"),
    RAGE("RAGE"),
    FRENZY("FRENZY");

    private final String mood;

    Mood(String mood) {
      this.mood = mood;
    }

    public String getMood() {
        return mood;
    }

    public static boolean isIncludeElement(String mood){
        for (Mood x : Mood.values()){
            if (mood.equals(x.getMood())){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Mood{" +
                "mood='" + mood + '\'' +
                '}';
    }
}
