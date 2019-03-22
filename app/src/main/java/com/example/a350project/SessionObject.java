package com.example.a350project;

public class SessionObject {

    private String sessionID;
    private String tutor;
    private String student;
    private String subject;
    private String date;
    private String duration;    // minutes
    private String price;       // dollars
    private String status;

    public SessionObject (String sessionID, String tutor, String student,
        String subject, String date, String duration, String price, String status) {

        this.sessionID = sessionID;
        this.tutor = tutor;
        this.student = student;
        this.subject = subject;
        this.date = date;
        this.duration = duration;
        this.price = price;
        this.status = status;
    }

    public String getSessionID () { return this.sessionID; }

    public String getTutor () {
        return this.tutor;
    }

    public String getStudent () {
        return this.student;
    }

    public String getSubject () {
        return this.subject;
    }

    public String getDate () {
        return this.date;
    }

    public String getDuration () {
        return this.duration;
    }

    public String getPrice () {
        return this.price;
    }

    public String getStatus () {
        return this.status;
    }






}
