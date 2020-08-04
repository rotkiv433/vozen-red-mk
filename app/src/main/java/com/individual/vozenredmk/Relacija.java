package com.individual.vozenredmk;

public class Relacija {

    private String start;
    private String end;
    private String stanica;
    private String vreme;

    public Relacija(String start, String end, String stanica, String vreme) {
        this.start = start;
        this.end = end;
        this.stanica = stanica;
        this.vreme = vreme;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStanica() {
        return stanica;
    }

    public void setStanica(String stanica) {
        this.stanica = stanica;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public Relacija() {}

}
