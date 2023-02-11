package com.zerophi.gestionvie.models;

public class etudiantsmodel {

     int etudiantuser_id;
    int departement_id;
    int role_id;
    int annee_id;

    public int getSemestre_id() {
        return semestre_id;
    }

    public void setSemestre_id(int semestre_id) {
        this.semestre_id = semestre_id;
    }

    int semestre_id;
    String id_unique  ,gender, name, email,password,first_name,last_name,telephone ,address;



    public int getEtudiantuser_id() {
        return etudiantuser_id;
    }

    public void setEtudiantuser_id(int etudiantuser_id) {
        this.etudiantuser_id = etudiantuser_id;
    }

    public int getDepartement_id() {
        return departement_id;
    }

    public void setDepartement_id(int departement_id) {
        this.departement_id = departement_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getId_unique() {
        return id_unique;
    }

    public void setId_unique(String id_unique) {
        this.id_unique = id_unique;
    }

    public int getAnnee_id() {
        return annee_id;
    }

    public void setAnnee_id(int annee_id) {
        this.annee_id = annee_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
