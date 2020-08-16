package com.individual.vozenredmk;

import java.util.ArrayList;
import java.util.Comparator;

public class Relation {
    private int Id;
    private String start;
    private String end;
    private String stanica;
    private String vreme;
    private String kompanija;
    private String cena;
    private Boolean expanded;

    public Relation(int Id, String start, String end, String stanica, String vreme, String kompanija, String cena) {
        this.Id = Id;
        this.start = start;
        this.end = end;
        this.stanica = stanica;
        this.vreme = vreme;
        this.kompanija = kompanija;
        this.cena = cena;
        this.expanded = false;
    }

    public String getRelacija() {
        return start + " - " + end;
    }

    public String getVremeIKompanija() {
        return vreme + " - " + kompanija;
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

    public Relation() {}

    @Override
    public String toString() {
        return "Relacija{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", stanica='" + stanica + '\'' +
                ", vreme='" + vreme + '\'' +
                ", kompanija='" + kompanija + '\'' +
                ", cena='" + cena + '\'' +
                ", expanded=" + expanded +
                '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }



    public Relation createNewRelation(ArrayList<Relation> relacii, RelacijaViewHolder holder, int position) {
        Relation temp = new Relation();
        int Id = relacii.get(position).getId();
        temp.setId(Id);
        String relacija = (String) holder.relacija.getText();
        String[] parts = relacija.split(" - ");
        String start = parts[0];
        String end = parts[1];
        temp.setStart(start);
        temp.setEnd(end);
        String vremeikompanija = (String) holder.vremeikompanija.getText();
        parts = vremeikompanija.split(" - ");
        String vreme = parts[0];
        String kompanija = parts[1];
        temp.setVreme(vreme);
        temp.setKompanija(kompanija);
        temp.setStanica((String) holder.stanica.getText());
        temp.setCena((String) holder.cena.getText());

        return temp;
    }


    public static Comparator<Relation> sortByTime = new Comparator<Relation>() {
        @Override
        public int compare(Relation r1, Relation r2) {
            return r1.getVreme().compareTo(r2.getVreme());
        }
    };
}
