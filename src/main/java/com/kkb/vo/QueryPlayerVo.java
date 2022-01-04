package com.kkb.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author APPDE
 */
public class QueryPlayerVo {
    private String playerName;
    private Integer playerNum;
    private Integer location;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String nationality;
    private double salary;
    private Integer teamId;
    private Integer status;
    private Integer isDel;

    @Override
    public String toString() {
        return "QueryPlayerVo{" +
                "playerName='" + playerName + '\'' +
                ", playerNum=" + playerNum +
                ", location=" + location +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", nationality='" + nationality + '\'' +
                ", salary=" + salary +
                ", teamId=" + teamId +
                ", status=" + status +
                ", isDel=" + isDel +
                '}';
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(Integer playerNum) {
        this.playerNum = playerNum;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}

