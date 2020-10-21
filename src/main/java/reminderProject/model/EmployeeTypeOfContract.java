package reminderProject.model;

import java.util.InputMismatchException;

public enum EmployeeTypeOfContract {
    UMOWA_O_PRACE_OKRES_PROBNY("proba"),
    UMOWA_O_PRACE_NA_CZAS_OKRESLONY("okreslony"),
    UMOWA_O_PRACE_NA_CZAS_NIEOKRESLONY("nieokreslony");

    private String skroconaNazwa;

    EmployeeTypeOfContract(String skroconaNazwa) {

        this.skroconaNazwa = skroconaNazwa;
    }
    public static EmployeeTypeOfContract valueOfShort(String skroconaNazwa){
        if(skroconaNazwa.equalsIgnoreCase(UMOWA_O_PRACE_NA_CZAS_NIEOKRESLONY.skroconaNazwa)){
            return UMOWA_O_PRACE_NA_CZAS_NIEOKRESLONY;
        }else if(skroconaNazwa.equalsIgnoreCase(UMOWA_O_PRACE_NA_CZAS_OKRESLONY.skroconaNazwa)){
            return UMOWA_O_PRACE_NA_CZAS_OKRESLONY;
        } else if(skroconaNazwa.equalsIgnoreCase(UMOWA_O_PRACE_OKRES_PROBNY.skroconaNazwa)){
            return UMOWA_O_PRACE_OKRES_PROBNY;
        }
        throw new InputMismatchException();
    }
}
