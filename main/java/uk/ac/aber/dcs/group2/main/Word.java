package uk.ac.aber.dcs.group2.main;

import com.google.gson.annotations.SerializedName;

public class Word {
    @SerializedName("english")
    String english;

    @SerializedName("welsh")
    String welsh;

    @SerializedName("wordType")
    String wordType;

    public Word(String english, String welsh, String wordType) {
        this.english = english;
        this.welsh = welsh;
        this.wordType = wordType;
    }

    public Word() {}

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getWelsh() {
        return welsh;
    }

    public void setWelsh(String welsh) {
        this.welsh = welsh;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

}
