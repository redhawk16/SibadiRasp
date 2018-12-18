package com.example.pochekuev.myapplication.items;

public class Lessons {
    private int id;
    private String time_start; // Время начала занятий
    private String time_end; // Время конца занятий
    private String disciplines; // Название дисциплины
    private String typelessons; // Тип занятия
    private String teachers; // ФИО преподавателя
    private String auditories; // Номер аудитории
    private String subgroups; // подгруппа
    private String groups; // группа
    private String faculty; // факультет

    public Lessons(int id, String time_start, String time_end, String disciplines, String typelessons, String teachers, String auditories) {
        this.id = id;
        this.time_start = time_start;
        this.time_end = time_end;
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

    public String getTimeStart() {
        return time_start;
    }

    public void setTimeStart(String time_start) {
        this.time_start = time_start;
    }

    public String getTimeEnd() {
        return time_end;
    }

    public void setTimeEnd(String time_end) {
        this.time_end = time_end;
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