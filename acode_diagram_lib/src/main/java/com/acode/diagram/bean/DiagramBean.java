package com.acode.diagram.bean;

/**
 * user:yangtao
 * date:2018/8/271050
 * email:yangtao@bjxmail.com
 * introduce:折线实体
 */
public class DiagramBean {
    private float dx;
    private float dy;
    private String strdx;
    private String strdy;

    public DiagramBean(float dx, float dy, String strdx, String strdy) {
        this.dx = dx;
        this.dy = dy;
        this.strdx = strdx;
        this.strdy = strdy;
    }

    public DiagramBean(String strdx, float dy) {
        this.dy = dy;
        this.strdx = strdx;
    }

    public DiagramBean(String strdx, String strdy) {
        this.strdx = strdx;
        this.strdy = strdy;
    }

    public float getDx() {
        return dx;
    }

    public DiagramBean setDx(float dx) {
        this.dx = dx;
        return this;
    }

    public float getDy() {
        return dy;
    }

    public DiagramBean setDy(float dy) {
        this.dy = dy;
        return this;
    }

    public String getStrdx() {
        return strdx;
    }

    public DiagramBean setStrdx(String strdx) {
        this.strdx = strdx;
        return this;
    }

    public String getStrdy() {
        return strdy;
    }

    public DiagramBean setStrdy(String strdy) {
        this.strdy = strdy;
        return this;
    }

    @Override
    public String toString() {
        return "DiagramBean{" +
                "dx=" + dx +
                ", dy=" + dy +
                ", strdx='" + strdx + '\'' +
                ", strdy='" + strdy + '\'' +
                '}';
    }
}
