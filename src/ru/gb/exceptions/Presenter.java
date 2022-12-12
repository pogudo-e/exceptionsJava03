package ru.gb.exceptions;
public class Presenter {
    private View view;
    private Model model;


    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
    }


    @Override
    public String toString() {
        return "model=" + model;
    }

    public void onClick(){
            model.setText(view.getText());
            model.execute();
    }
}
