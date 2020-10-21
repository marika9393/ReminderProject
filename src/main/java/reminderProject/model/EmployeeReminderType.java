package reminderProject.model;

import java.util.InputMismatchException;

public enum EmployeeReminderType {

    UBEZPIECZENIE_ZUS("ZUS"),
    WYPLATA("wyplata"),
    PODATEK_OD_WYNAGRODZENIA("podatek"),
    DELEGACJA("delegacja"),
    BADANIA_OKRESOWE("badania"),
    LISTA_OBECNOSCI("lista");

    private String skroconaNazwa;

    EmployeeReminderType(String skroconaNazwa) {

        this.skroconaNazwa = skroconaNazwa;
    }

    public static EmployeeReminderType valueOfShortReminder(String skroconaNazwa) {
        if (skroconaNazwa.equalsIgnoreCase(UBEZPIECZENIE_ZUS.skroconaNazwa)){
            return UBEZPIECZENIE_ZUS;
        } else if(skroconaNazwa.equalsIgnoreCase(WYPLATA.skroconaNazwa)) {
            return WYPLATA;
        } else if(skroconaNazwa.equalsIgnoreCase(PODATEK_OD_WYNAGRODZENIA.skroconaNazwa)) {
            return PODATEK_OD_WYNAGRODZENIA;
        } else if(skroconaNazwa.equalsIgnoreCase(DELEGACJA.skroconaNazwa)){
            return DELEGACJA;
        } else if(skroconaNazwa.equalsIgnoreCase(BADANIA_OKRESOWE.skroconaNazwa)) {
            return BADANIA_OKRESOWE;
        } else if (skroconaNazwa.equalsIgnoreCase(LISTA_OBECNOSCI.skroconaNazwa)) {
            return LISTA_OBECNOSCI;
        }
        throw new InputMismatchException();
    }
}
