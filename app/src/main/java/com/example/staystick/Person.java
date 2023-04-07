package com.example.staystick;

public class Person {
    String name,imgphoto;
    Person(){

    }
    Person(String name,String imgphoto){
        this.imgphoto=imgphoto;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgphoto() {
        return imgphoto;
    }

    public void setImgphoto(String imgphoto) {
        this.imgphoto = imgphoto;
    }
}
