package Car;

import java.sql.Timestamp;

public class Note {
    private int id;
    private String note;
    private String garageName;
    private String email;
    private Timestamp timestamp;

    public Note() {
    }

    public Note(int id, String note, String garageName, String email, Timestamp timestamp) {
        this.note = note;
        this.garageName = garageName;
        this.email = email;
        this.timestamp = timestamp;
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", garageName='" + garageName + '\'' +
                ", email='" + email + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
