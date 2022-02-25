package ru.selenium.course.Task12;

public class NewProduct {

    public String name;
    public String code;
    public String quantity;
    public String weight;
    public String width;
    public String height;
    public String length;
    public String picture;
    public String headTitle;
    public String price;

    public NewProduct() {
    }

    public NewProduct withName(String name) {
        this.name = name;
        return this;
    }

    public NewProduct withCode(String code) {
        this.code = code;
        return this;
    }

    public NewProduct withQuantity(int quantity) {
        this.quantity = String.valueOf(quantity);
        return this;
    }

    public NewProduct withWeight(double weight) {
        this.weight = String.valueOf(weight);
        return this;
    }

    public NewProduct withWidth(double width) {
        this.width = String.valueOf(width);
        return this;
    }

    public NewProduct withHeight(double height) {
        this.height = String.valueOf(height);
        return this;
    }

    public NewProduct withLength(double length) {
        this.length = String.valueOf(length);
        return this;
    }

    public NewProduct withPicture(String pathToPicture) {
        this.picture = pathToPicture;
        return this;
    }

    public NewProduct withHeadTitle(String headTitle) {
        this.headTitle = headTitle;
        return this;
    }

    public NewProduct withPrice(double price) {
        this.price = String.valueOf(price);
        return this;
    }


    public String shortDescription = "Товарищи! сложившаяся структура организации представляет собой интересный" +
            " эксперимент проверки направлений прогрессивного развития. Повседневная практика показывает, " +
            "что реализация намеченных плановых заданий в значительной степени обуславливает создание модели развити";

}