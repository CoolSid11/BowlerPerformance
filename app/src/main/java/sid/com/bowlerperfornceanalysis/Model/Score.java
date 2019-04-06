package sid.com.bowlerperfornceanalysis.Model;

public class Score {
    private String coordinates;
    private String runs;

    public Score(String coordinates, String runs) {
        this.coordinates = coordinates;
        this.runs = runs;
    }

    public Score() {
    }

    public String getCoordinates() {

        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getRuns() {
        return runs;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }
}
