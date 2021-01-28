package com.example.challengeyourself.ChallengeModel;

public class DayTrack implements Comparable<DayTrack> {
    private Integer day;
    private Boolean isChecked;


    public DayTrack(Integer day, Boolean isChecked) {
        this.day = day;
        this.isChecked = isChecked;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Boolean getChecked() {
        return isChecked;
    }


    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "DayTrack{" +
                "day=" + day +
                ", isChecked=" + isChecked +
                '}';
    }

    @Override
    public int compareTo(DayTrack obj) {
        if (day.floatValue() > obj.day.floatValue()) {
            return 1;
        } else if (day.floatValue() < obj.day.floatValue()) {
            return -1;
        } else {
            return 0;
        }
    }
}
