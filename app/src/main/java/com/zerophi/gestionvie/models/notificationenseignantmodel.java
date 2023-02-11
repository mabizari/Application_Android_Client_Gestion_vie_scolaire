package com.zerophi.gestionvie.models;

public class notificationenseignantmodel {

    int notic_id , departement_id , module_id , enseignantuser_id;
    String notic_titre, notic_description , publish_date, enseignant_name;

    public int getNotic_id() {
        return this.notic_id;
    }

    public void setNotic_id(int notic_id) {
        this.notic_id = notic_id;
    }

    public int getDepartement_id() {
        return departement_id;
    }

    public void setDepartement_id(int departement_id) {
        this.departement_id = departement_id;
    }

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public int getEnseignantuser_id() {
        return enseignantuser_id;
    }

    public void setEnseignantuser_id(int enseignantuser_id) {
        this.enseignantuser_id = enseignantuser_id;
    }

    public String getNotic_titre() {
        return notic_titre;
    }

    public void setNotic_titre(String notic_titre) {
        this.notic_titre = notic_titre;
    }

    public String getNotic_description() {
        return notic_description;
    }

    public void setNotic_description(String notic_description) {
        this.notic_description = notic_description;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getEnseignant_name() {
        return enseignant_name;
    }

    public void setEnseignant_name(String enseignant_name) {
        this.enseignant_name = enseignant_name;
    }
}

