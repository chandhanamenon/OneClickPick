package com.example.Model;

public class Products {
    private String pname;
    private String desc;
    private String price;
    private String date;
    private String image;
    private String pid;
    private String time;
    private String category,productState;
    private Integer countcat;


    public Products(){

    }




    public Products(String pname, String desc, String price, String date, String image, String pid, String time) {
        this.pname = pname;
        this.desc = desc;
        this.price = price;
        this.date = date;
        this.image = image;
        this.pid = pid;
        this.time = time;
        this.category = category;
        this.countcat = countcat;
        this.productState=productState;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCountcat() {
        return countcat;
    }

    public void setCountcat(Integer countcat) {
        this.countcat = countcat;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }
}
