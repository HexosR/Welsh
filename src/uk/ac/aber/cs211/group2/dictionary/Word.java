package uk.ac.aber.cs211.group2.dictionary;

/** Word class, Contains the code for the word object which holds all the data for each word that will be stored in the program, it contains the English, Welsh and attributes of a singular word.
 * @author Kacper Szymczyc[kas102]
 * @author Robert Mlynarczyk[rom57]
 * @author Huzefa Syed[hus3]
 */
public class Word{

    @com.google.gson.annotations.SerializedName("english")
    String english;

    @com.google.gson.annotations.SerializedName("welsh")
    String welsh;

    @com.google.gson.annotations.SerializedName("wordType")
    private String wordType;
    private transient String displayType;

    public transient String practice;

    /**
     * This method is the constructor for this class
     * @param english
     * @param welsh
     * @param wordType
     */
    public Word(String english, String welsh, String wordType){
        this.english = english;
        this.welsh = welsh;
        this.wordType = wordType;
    }

    public Word(){}

    /**
     * Method that returns the English attribute of the word.
     * @return
     */
    public String getEnglish() {
        return english;
    }

    /**
     * Method that takes a string and changes the English attribute to the string inputted.
     * @param english
     */
    public void setEnglish(String english) {
        this.english = english;
    }

    /**
     * Method that returns the Welsh attribute of the word.
     * @return
     */
    public String getWelsh() {
        return welsh;
    }

    /**
     * Method that takes a string and changes the Welsh attribute to the string inputted.
     * @param welsh
     */
    public void setWelsh(String welsh) {
        this.welsh = welsh;
    }

    /**
     * Returns a string in English that is displayed to the user in a table indicating that word is in the practice list.
     * @return
     */
    public String getPractice() {
        return practice;
    }

    /**
     * Method that sets the Practice variable to say it is in the practice list.
     * @param practice
     */
    public void setPractice(String practice) {
        this.practice = practice;
    }

    /**
     * Method that returns the wordType attribute of the word.
     * @return
     */
    public String getWordType() {
        return wordType;
    }

    /**
     * Method that returns the word type.
     * @return
     */
    public String getDisplayType() {
        return displayType;
    }

    /**
     * Method that sets the word type.
     * @param displayType
     */
    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    /**
     * Used by a comparator to compare two word objects to see if they are equal.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        Word word = (Word) o;
        return english.equals(word.english);
    }

    /**
     * Method returns the hash value of the object.
     * @return
     */
    @Override
    public int hashCode() {
        return english.hashCode();
    }


    /**
     * toString for this class to display objects in string format.
     * @return
     */
    @Override
    public String toString() {
        return this.english + " - " + this.welsh;
    }

}
