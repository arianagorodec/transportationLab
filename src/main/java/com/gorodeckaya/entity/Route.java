package com.gorodeckaya.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="route")

public class Route {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_route", length = 11, nullable = false)
    private long id;

    @Column(name = "from_c")
    private String from;
    @Column(name = "to_c")
    private String to;

    @ManyToOne(fetch = FetchType.LAZY) //orphanRemoval=true
    @JoinColumn(name = "id_partner")
    private Partner partner;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "route", cascade = CascadeType.ALL)
    private Set<DistRoutes> distRoutes;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public Set<DistRoutes> getDistRoutes() {
        return distRoutes;
    }

    public void setDistRoutes(Set<DistRoutes> distRoutes) {
        this.distRoutes = distRoutes;
    }
}