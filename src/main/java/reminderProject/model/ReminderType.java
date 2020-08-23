package reminderProject.model;

import java.util.InputMismatchException;

public enum ReminderType {
    LEASING("leasing"),
    INSURANCE("insurance"),
    REVIEW("review"),
    OIL_CHANGE("oil"),
    FIRE_EXTINGUISHER_VALIDITY("fire"),
    TACHO_LEGALIZATION("tacho"),
    CAR_WASH("wash"),
    THERMOMETER_CALIBRATION("calibration");

    private String shortName;

    ReminderType(String shortName) {
        this.shortName = shortName;
    }

    public static ReminderType valueOfShortReminder(String shortName) {

        if (shortName.equalsIgnoreCase(LEASING.shortName)) {
            return LEASING;
        } else if (shortName.equalsIgnoreCase(INSURANCE.shortName)) {
            return INSURANCE;
        } else if (shortName.equalsIgnoreCase(REVIEW.shortName)) {
            return REVIEW;
        } else if (shortName.equalsIgnoreCase(OIL_CHANGE.shortName)) {
            return OIL_CHANGE;
        } else if (shortName.equalsIgnoreCase(FIRE_EXTINGUISHER_VALIDITY.shortName)) {
            return FIRE_EXTINGUISHER_VALIDITY;
        } else if (shortName.equalsIgnoreCase(TACHO_LEGALIZATION.shortName)) {
            return TACHO_LEGALIZATION;
        } else if (shortName.equalsIgnoreCase(CAR_WASH.shortName)) {
            return CAR_WASH;
        } else if (shortName.equalsIgnoreCase(THERMOMETER_CALIBRATION.shortName)) {
            return THERMOMETER_CALIBRATION;
        }
        throw new InputMismatchException();
    }
}