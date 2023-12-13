package com.example.chef_up.models;

import java.io.Console;
import java.lang.reflect.Field;

public class Meal {
    private String strMeal;
    private String strInstructions;
    private String strMealThumb;
    private String strIngredient1, strIngredient2,strIngredient3,strIngredient4,strIngredient5,strIngredient6,strIngredient7,strIngredient8,strIngredient9,strIngredient10,strIngredient11,strIngredient12,strIngredient13,strIngredient14,strIngredient15;
    private String strMeasure1, strMeasure2,strMeasure3,strMeasure4,strMeasure5,strMeasure6,strMeasure7,strMeasure8,strMeasure9,strMeasure10,strMeasure11,strMeasure12,strMeasure13,strMeasure14,strMeasure15;

    // Constructors
    public Meal() {}

    public String getFormattedDescription() {
        StringBuilder formatted = new StringBuilder("Ingredients:\n");

        if (strIngredient1 != null && !strIngredient1.trim().isEmpty()) formatted.append(strIngredient1).append(": ").append(strMeasure1 != null ? strMeasure1 : "").append("\n");
        if (strIngredient2 != null && !strIngredient2.trim().isEmpty()) formatted.append(strIngredient2).append(": ").append(strMeasure2 != null ? strMeasure2 : "").append("\n");
        if (strIngredient3 != null && !strIngredient3.trim().isEmpty()) formatted.append(strIngredient3).append(": ").append(strMeasure3 != null ? strMeasure3 : "").append("\n");
        if (strIngredient4 != null && !strIngredient4.trim().isEmpty()) formatted.append(strIngredient4).append(": ").append(strMeasure4 != null ? strMeasure4 : "").append("\n");
        if (strIngredient5 != null && !strIngredient5.trim().isEmpty()) formatted.append(strIngredient5).append(": ").append(strMeasure5 != null ? strMeasure5 : "").append("\n");
        if (strIngredient6 != null && !strIngredient6.trim().isEmpty()) formatted.append(strIngredient6).append(": ").append(strMeasure6 != null ? strMeasure6 : "").append("\n");
        if (strIngredient7 != null && !strIngredient7.trim().isEmpty()) formatted.append(strIngredient7).append(": ").append(strMeasure7 != null ? strMeasure7 : "").append("\n");
        if (strIngredient8 != null && !strIngredient8.trim().isEmpty()) formatted.append(strIngredient8).append(": ").append(strMeasure8 != null ? strMeasure8 : "").append("\n");
        if (strIngredient9 != null && !strIngredient9.trim().isEmpty()) formatted.append(strIngredient9).append(": ").append(strMeasure9 != null ? strMeasure9 : "").append("\n");
        if (strIngredient10 != null && !strIngredient10.trim().isEmpty()) formatted.append(strIngredient10).append(": ").append(strMeasure10 != null ? strMeasure10 : "").append("\n");
        if (strIngredient11 != null && !strIngredient11.trim().isEmpty()) formatted.append(strIngredient11).append(": ").append(strMeasure11 != null ? strMeasure11 : "").append("\n");
        if (strIngredient12 != null && !strIngredient12.trim().isEmpty()) formatted.append(strIngredient12).append(": ").append(strMeasure12 != null ? strMeasure12 : "").append("\n");
        if (strIngredient13 != null && !strIngredient13.trim().isEmpty()) formatted.append(strIngredient13).append(": ").append(strMeasure13 != null ? strMeasure13 : "").append("\n");
        if (strIngredient14 != null && !strIngredient14.trim().isEmpty()) formatted.append(strIngredient14).append(": ").append(strMeasure14 != null ? strMeasure14 : "").append("\n");
        if (strIngredient15 != null && !strIngredient15.trim().isEmpty()) formatted.append(strIngredient15).append(": ").append(strMeasure15 != null ? strMeasure15 : "").append("\n");

        formatted.append("\nInstructions:\n").append(strInstructions);

        return formatted.toString().trim();
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public void setStrIngredient6(String strIngredient6) {
        this.strIngredient6 = strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }

    public void setStrIngredient7(String strIngredient7) {
        this.strIngredient7 = strIngredient7;
    }

    public String getStrIngredient8() {
        return strIngredient8;
    }

    public void setStrIngredient8(String strIngredient8) {
        this.strIngredient8 = strIngredient8;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public void setStrIngredient9(String strIngredient9) {
        this.strIngredient9 = strIngredient9;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public void setStrIngredient10(String strIngredient10) {
        this.strIngredient10 = strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public void setStrIngredient11(String strIngredient11) {
        this.strIngredient11 = strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public void setStrIngredient12(String strIngredient12) {
        this.strIngredient12 = strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public void setStrIngredient13(String strIngredient13) {
        this.strIngredient13 = strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public void setStrIngredient14(String strIngredient14) {
        this.strIngredient14 = strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public void setStrIngredient15(String strIngredient15) {
        this.strIngredient15 = strIngredient15;
    }

    public String getStrMeasure1() {
        return strMeasure1;
    }

    public void setStrMeasure1(String strMeasure1) {
        this.strMeasure1 = strMeasure1;
    }

    public String getStrMeasure2() {
        return strMeasure2;
    }

    public void setStrMeasure2(String strMeasure2) {
        this.strMeasure2 = strMeasure2;
    }

    public String getStrMeasure3() {
        return strMeasure3;
    }

    public void setStrMeasure3(String strMeasure3) {
        this.strMeasure3 = strMeasure3;
    }

    public String getStrMeasure4() {
        return strMeasure4;
    }

    public void setStrMeasure4(String strMeasure4) {
        this.strMeasure4 = strMeasure4;
    }

    public String getStrMeasure5() {
        return strMeasure5;
    }

    public void setStrMeasure5(String strMeasure5) {
        this.strMeasure5 = strMeasure5;
    }

    public String getStrMeasure6() {
        return strMeasure6;
    }

    public void setStrMeasure6(String strMeasure6) {
        this.strMeasure6 = strMeasure6;
    }

    public String getStrMeasure7() {
        return strMeasure7;
    }

    public void setStrMeasure7(String strMeasure7) {
        this.strMeasure7 = strMeasure7;
    }

    public String getStrMeasure8() {
        return strMeasure8;
    }

    public void setStrMeasure8(String strMeasure8) {
        this.strMeasure8 = strMeasure8;
    }

    public String getStrMeasure9() {
        return strMeasure9;
    }

    public void setStrMeasure9(String strMeasure9) {
        this.strMeasure9 = strMeasure9;
    }

    public String getStrMeasure10() {
        return strMeasure10;
    }

    public void setStrMeasure10(String strMeasure10) {
        this.strMeasure10 = strMeasure10;
    }

    public String getStrMeasure11() {
        return strMeasure11;
    }

    public void setStrMeasure11(String strMeasure11) {
        this.strMeasure11 = strMeasure11;
    }

    public String getStrMeasure12() {
        return strMeasure12;
    }

    public void setStrMeasure12(String strMeasure12) {
        this.strMeasure12 = strMeasure12;
    }

    public String getStrMeasure13() {
        return strMeasure13;
    }

    public void setStrMeasure13(String strMeasure13) {
        this.strMeasure13 = strMeasure13;
    }

    public String getStrMeasure14() {
        return strMeasure14;
    }

    public void setStrMeasure14(String strMeasure14) {
        this.strMeasure14 = strMeasure14;
    }

    public String getStrMeasure15() {
        return strMeasure15;
    }

    public void setStrMeasure15(String strMeasure15) {
        this.strMeasure15 = strMeasure15;
    }
}
