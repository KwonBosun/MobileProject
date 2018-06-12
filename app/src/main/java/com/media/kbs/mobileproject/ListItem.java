package com.media.kbs.mobileproject;

public class ListItem {
    private String foodName;
    private String nameBreak;
    private String nameLunch;
    private String nameDinner;
    private String nameExer;
    private double totalKcal;
    private double carbonKcal;
    private double proteinKcal;
    private double fatKcal;
    private double sodiumKcal;
    private double exerKcal;

    public String getFoodName(){return foodName;
    }
    public void setFoodName(String foodName){this.foodName=foodName;}

    public String getNameBreak() {
        return nameBreak;
    }

    public void setNameBreak(String nameBreak) {
        this.nameBreak = nameBreak;
    }

    public String getNameLunch() {
        return nameLunch;
    }

    public void setNameLunch(String nameLunch) {
        this.nameLunch = nameLunch;
    }

    public String getNameDinner() {
        return nameDinner;
    }

    public void setNameDinner(String nameDinner) {
        this.nameDinner = nameDinner;
    }

    public double getTotalKcal() {
        return totalKcal;
    }

    public void setTotalKcal(double totalKcal) {
        this.totalKcal = totalKcal;
    }

    public double getCarbonKcal() {
        return carbonKcal;
    }

    public void setCarbonKcal(double carbonKcal) {
        this.carbonKcal = carbonKcal;
    }

    public double getProteinKcal() {
        return proteinKcal;
    }

    public void setProteinKcal(double proteinKcal) {
        this.proteinKcal = proteinKcal;
    }

    public double getFatKcal() {
        return fatKcal;
    }

    public void setFatKcal(double fatKcal) {
        this.fatKcal = fatKcal;
    }

    public double getSodiumKcal() {
        return sodiumKcal;
    }

    public void setSodiumKcal(double sodiumKcal) {
        this.sodiumKcal = sodiumKcal;
    }

    public double getExerKcal() {
        return exerKcal;
    }

    public void setExerKcal(double exerKcal) {
        this.exerKcal = exerKcal;
    }

    public String getNameExer() { return nameExer; }

    public void setNameExer(String nameExer) {
        this.nameExer = nameExer;
    }
}
