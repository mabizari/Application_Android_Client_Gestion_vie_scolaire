package com.zerophi.gestionvie.adminespace.gestion_utilisateur;

public class module_model {


    String module_name ;
    int module_id,departement_id,semestre_id ;

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public int getDepartement_id() {
        return departement_id;
    }

    public void setDepartement_id(int departement_id) {
        this.departement_id = departement_id;
    }

    public int getSemestre_id() {
        return semestre_id;
    }

    public void setSemestre_id(int semestre_id) {
        this.semestre_id = semestre_id;
    }
}
