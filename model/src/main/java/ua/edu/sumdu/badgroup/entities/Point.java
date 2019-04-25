package ua.edu.sumdu.badgroup.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Point {
    private double arg;
    private double res;
    private String tag;

    public Point(double arg, double res) {
        this.arg = arg;
        this.res = res;
    }

    public Point() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.arg, arg) == 0 &&
                Double.compare(point.res, res) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(arg, res);
    }

    public double getArg() {
        return arg;
    }

    public void setArg(double arg) {
        this.arg = arg;
    }

    public double getValue() {
        return res;
    }

    public double getRes() {
        return res;
    }

    public void setRes(double res) {
        this.res = res;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}