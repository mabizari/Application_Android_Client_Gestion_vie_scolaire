package com.zerophi.gestionvie.models;

public class absencemodel {

    String absence_date;
    String status;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    String module;

    public String getAbsence_date() {
        return absence_date;
    }

    public void setAbsence_date(String absence_date) {
        this.absence_date = absence_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHeur() {
        return heur;
    }

    public void setHeur(int heur) {
        this.heur = heur;
    }

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    int heur,module_id;
}
