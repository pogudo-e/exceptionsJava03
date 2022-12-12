package ru.gb.exceptions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Users implements Model {

    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private User user;
    private String[] data;
    private String inputData;
    private SaveFile saveFile;


    public Users() {
        this.user = new User();
        this.saveFile = new SaveFile();
    }

    public static boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void getSaveFile() {
        String[] fioParse = user.getFio();
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(fioParse[0] + " " + fioParse[1] + " " + fioParse[2] + " ");
        strBuffer.append(user.getDate() + " ");
        strBuffer.append(user.getNumber() + " ");
        strBuffer.append(user.getSex());
        if (!saveFile.fileExist()) {
            saveFile.saveNewFile(strBuffer.toString());
            System.out.println("Сохранено в новый файл!");
        } else {
            saveFile.saveOldFile(strBuffer.toString());
            System.out.println("Добовлено к существующему файлу!");

        }
    }

    public void setDate(String date) {
        this.user.setDate(date);
    }

    public void setSex(char sex) {
        if (sex == 'f' || sex == 'm') {
            this.user.setSex(sex);
        }
    }

    public Boolean setNumber(int number) {
        if (number < 999 || number > 999999999) {
            return false;
        }
        this.user.setNumber(number);
        return true;
    }

    public void setFio(String family, String name, String otch) {
        String[] fio = new String[]{family, name, otch};
        this.user.setFio(fio);
    }

    private String[] getDataFromText() throws RuntimeException{
        data = inputData.split("\\s+");
        if(data.length > 6){

            throw new RuntimeException("Слишком много всего, не могу разобраться :(");
        }
        return data;
    }

    private void validateData(String[] d) {
        int fioIterator = 0;
        String[] fioAdd = new String[3];
        for (String r : d) {
            if (r.length() == 1) {
                char c = r.charAt(0);
                setSex(c);
            }
            if (isDigit(r)) {
                setNumber(Integer.parseInt(r));
            }
            if (isDateValid(r)) {
                setDate(r);
            }

            if (r.matches("^[a-zA-Z]*$") && r.length() > 1) {
                try {
                    fioAdd[fioIterator] = r;
                    fioIterator++;
                } catch (ArrayIndexOutOfBoundsException e){
                    fioAdd = null;
                }

            }
        }
        try {
            if (fioAdd[0] != null && fioAdd[1] != null && fioAdd[2] != null) {
                setFio(fioAdd[0], fioAdd[1], fioAdd[2]);
                saveFile.setFileName(fioAdd[0]);
            }
        } catch (NullPointerException e){

        }

    }

    @Override
    public void setText(String s) {
        inputData = s;
    }

    @Override
    public String toString() {
        return user.toString();
    }

    private Boolean validatePerSave() throws RuntimeException {
        if (user.getNumber() == 0) {
            throw new RuntimeException("Номер указан неверно");
        } else if (user.getFio() == null) {
            throw new RuntimeException("Неверно заполнено ФИО");
        } else if (Character.isWhitespace(user.getSex()) || user.getSex() == '\0') {
            throw new RuntimeException("Пол указан неверно");
        } else if (user.getDate() == null) {
            throw new RuntimeException("Дата указано неверно");
        } else {
            System.out.println("validatePerSave true");
            return true;
        }

    }

    @Override
    public void execute() {

        try {
            validateData(getDataFromText());
            if (validatePerSave()) {
                getSaveFile();
            }
        } catch (RuntimeException e) {
            System.out.println(e);
        }


    }
}
