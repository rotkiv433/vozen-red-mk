package com.individual.vozenredmk;

public class Relacija {

    private String start;
    private String end;
    private String stanica;
    private String vreme;
    private String kompanija;
    private String cena;
    private Boolean expanded;

    public Relacija(String start, String end, String stanica, String vreme, String kompanija, String cena) {
        this.start = start;
        this.end = end;
        this.stanica = stanica;
        this.vreme = vreme;
        this.kompanija = kompanija;
        this.cena = cena;
       this.expanded = false;
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

    public String getKompanija() {
        return kompanija;
    }

    public void setKompanija(String kompanija) {
        this.kompanija = kompanija;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

   public Boolean isExpanded() {
       return expanded;
    }

    public void setExpanded(Boolean expanded) {
       this.expanded = expanded;
   }

    public Relacija() {}


}
