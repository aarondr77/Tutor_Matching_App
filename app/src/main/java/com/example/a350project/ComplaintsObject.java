package com.example.a350project;

public class ComplaintsObject {
    private String content;
    private String submitter;
    private String status;
    private String target;

    public ComplaintsObject(String content, String submitter, String status, String target) {
        this.content = content;
        this.submitter = submitter;
        this.status = status;
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public String getSubmitter() {return submitter;}
}
