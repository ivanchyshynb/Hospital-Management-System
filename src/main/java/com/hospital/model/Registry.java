package com.hospital.model;

import java.util.Date;

public class Registry {
    private int number;
    private Patient patient;
    private Doctor doctor;
    private String disease;
    private Date admission;
    private Date dismission;
    private int roomNumber;

    public Registry() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Date getAdmission() {
        return admission;
    }

    public void setAdmission(Date admission) {
        this.admission = admission;
    }

    public Date getDismission() {
        return dismission;
    }

    public void setDismission(Date dismission) {
        this.dismission = dismission;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Registry{" +
                "number=" + number +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", disease='" + disease + '\'' +
                ", admission=" + admission +
                ", dismission=" + dismission +
                ", roomNumber=" + roomNumber +
                '}';
    }
}
