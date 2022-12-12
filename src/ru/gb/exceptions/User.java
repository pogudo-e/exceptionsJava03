package ru.gb.exceptions;

import java.util.Arrays;

public class User {
    private String[] fio;
    private String date;
    private int number;
    private char sex;


    public void setFio(String[] fio) {
        this.fio = fio;
    }

    public void setDate(String date) {
            this.date = date;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fio=" + Arrays.toString(fio) +
                ", date='" + date + '\'' +
                ", number=" + number +
                ", sex=" + sex +
                '}';
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String[] getFio() {
        return fio;
    }

    public String getDate() {
        return date;
    }

    public int getNumber() {
        return number;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {

            this.sex = sex;
    }


}
