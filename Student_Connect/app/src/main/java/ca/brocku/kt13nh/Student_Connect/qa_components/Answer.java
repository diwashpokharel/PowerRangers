package ca.brocku.kt13nh.Student_Connect.qa_components;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pc-user on 3/12/2018.
 */

public class Answer {
    private String explanation;
    private String user;
    private String photoUrl;
    private String fileName;
    private String fileUrl;
    private String time;

    public Answer(){
    }

    public Answer(String explanation, String user, String photoUrl, String fileName, String fileUrl ) {
        this.explanation = explanation;
        this.user = user;
        this.photoUrl = photoUrl;
        this.fileName = fileName;
        this.fileUrl = fileUrl;

        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        this.time = dateFormat.format(new Date());
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String text) {
        this.explanation = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime(){ return time; }

    public void setTime(String time) { this.time = time; }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }
}