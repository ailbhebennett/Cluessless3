package com.example.cluessless3;

public class ClothingModel {
    public static int id;
    public static String clothingName, clothingColour, clothingDescription;

    public ClothingModel(int id, String clothingName, String clothingColour, String clothingDescription) {
        ClothingModel.id = id;
        ClothingModel.clothingName = clothingName;
        ClothingModel.clothingColour = clothingColour;
        ClothingModel.clothingDescription = clothingDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        ClothingModel.id = id;
    }

    public String getClothingName() {
        return clothingName;
    }

    public void setClothingName(String clothingName) {
        ClothingModel.clothingName = clothingName;
    }

    public String getClothingColour() {
        return clothingColour;
    }

    public void setClothingColour(String clothingColour) {
        ClothingModel.clothingColour = clothingColour;
    }

    public String getClothingDescription() {
        return clothingDescription;
    }

    public void setClothingDescription(String clothingDescription) {
        ClothingModel.clothingDescription = clothingDescription;
    }

    @Override
    public String toString() {
        return "ClothingModel{" +
                "id=" + id +
                ", clothingName='" + clothingName + '\'' +
                ", clothingColour='" + clothingColour + '\'' +
                ", clothingDescription='" + clothingDescription + '\'' +
                '}';
    }
}
