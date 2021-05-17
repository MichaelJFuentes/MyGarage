package Models.Cars;

import Models.Garage.Garage;
import Models.Users.Mechanic;

public class Note {
    private String note;
    private Garage garage;
    private Mechanic mechanic;


    public Note(String note, Garage garage, Mechanic mechanic) {
        this.note = note;
        this.garage = garage;
        this.mechanic = mechanic;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }
}
