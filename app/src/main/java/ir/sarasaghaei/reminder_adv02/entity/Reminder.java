package ir.sarasaghaei.reminder_adv02.entity;

public class Reminder {
    int id;
    String category,detail;
    String date;
    String time;


    public Reminder(int id, String category, String detail, String date, String time) {
        this.id = id;
        this.category = category;
        this.detail = detail;
        this.date = date;
        this.time = time;
    }

    public Reminder(String category, String detail, String date, String time) {
        this.category = category;
        this.detail = detail;
        this.date = date;
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getDetail() {
        return detail;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
