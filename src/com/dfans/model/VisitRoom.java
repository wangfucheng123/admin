package com.dfans.model;

import java.util.Date;

public class VisitRoom {
    private Integer id;

    private String visitTitle;

    private Date visitDate;

    private String visitIntrod;

    private String name;

    private String title;

    private String introduction;

    private String photo;

    private String img;

    private String video;

    private String audio;

    private String textRecord;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVisitTitle() {
        return visitTitle;
    }

    public void setVisitTitle(String visitTitle) {
        this.visitTitle = visitTitle == null ? null : visitTitle.trim();
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitIntrod() {
        return visitIntrod;
    }

    public void setVisitIntrod(String visitIntrod) {
        this.visitIntrod = visitIntrod == null ? null : visitIntrod.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video == null ? null : video.trim();
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio == null ? null : audio.trim();
    }

    public String getTextRecord() {
        return textRecord;
    }

    public void setTextRecord(String textRecord) {
        this.textRecord = textRecord == null ? null : textRecord.trim();
    }
}