package com.example.staystick;

import java.util.ArrayList;
import java.util.List;

public class ContactModal {
    String name,phone;
    String img,address;
    char st;
    private ArrayList<Person> ChildItemList;

   private ContactModal(){

    }
  public ContactModal(char st,String name,String phone,String img,String address){
        this.address=address;
        this.img=img;
        this.name=name;
        this.st=st;
        this.phone=phone;

  }
  public ContactModal(char st,ArrayList<Person>ChildItemList){
        this.ChildItemList=ChildItemList;
        this.st=st;
  }


    public char getSt() {
        return st;
    }

    public void setSt(char st) {
        this.st = st;
    }

    public List<Person> getChildItemList() {
        return ChildItemList;
    }

    public void setChildItemList(ArrayList<Person> childItemList) {
        ChildItemList = childItemList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
