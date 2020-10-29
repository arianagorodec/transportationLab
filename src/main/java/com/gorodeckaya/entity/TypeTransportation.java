package com.gorodeckaya.entity;

import com.gorodeckaya.entity.enums.TransportEnum;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="type_transportation")

public class TypeTransportation {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_type_transportation", length = 11, nullable = false)
    private long id;

    @Column(name = "type")
    private TransportEnum type;
    @Column(name = "time")
    private Double time;
    @Column(name = "distance")
    private Double distance;
    @Column(name = "price")
    private Double price; // for hour
    @Column(name = "percent_for_added")
    private Double percent;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "description")
    private String description;

    @OneToOne(mappedBy="typeTransportation", cascade = CascadeType.ALL)
    public DistRoutes distRoutes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TransportEnum getType() {
        return type;
    }

    public void setType(TransportEnum type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DistRoutes getDistRoutes() {
        return distRoutes;
    }

    public void setDistRoutes(DistRoutes distRoutes) {
        this.distRoutes = distRoutes;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
