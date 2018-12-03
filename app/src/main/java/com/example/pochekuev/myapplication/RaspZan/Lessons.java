package com.example.pochekuev.myapplication.RaspZan;

public class Lessons {
    private int id;
    private String typeweeks; // Тип недели
    private String dayweeks; // День недели
    private String time; // Время занятий
    private String disciplines; // Название дисциплины
    private String typelessons; // Тип занятия
    private String teachers; // ФИО преподавателя
    private String auditories; // Номер аудитории

    public Lessons(int id, String typeweeks, String dayweeks, String time, String disciplines, String typelessons, String teachers, String auditories) {
        this.id = id;
        this.typeweeks = typeweeks;
        this.dayweeks = dayweeks;
        this.time = time;
        this.disciplines = disciplines;
        this.typelessons = typelessons;
        this.teachers = teachers;
        this.auditories = auditories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeweeks() {
        return typeweeks;
    }

    public void setTypeweeks(String typeweeks) {
        this.typeweeks = typeweeks;
    }

    public String getDayweeks() {
        return dayweeks;
    }

    public void setDayweeks(String dayweeks) {
        this.dayweeks = dayweeks;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(String disciplines) {
        this.disciplines = disciplines;
    }

    public String getTypelessons() {
        return typelessons;
    }

    public void setTypelessons(String typelessons) {
        this.typelessons = typelessons;
    }

    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    public String getAuditories() {
        return auditories;
    }

    public void setAuditories(String auditories) {
        this.auditories = auditories;
    }
}
