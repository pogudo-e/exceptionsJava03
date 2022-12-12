package ru.gb.exceptions;

public class Main {

    public static void main(String[] args) {

        Presenter presenter = new Presenter(new MyView(), new Users());
        System.out.println(presenter);
        presenter.onClick();

    }



}
