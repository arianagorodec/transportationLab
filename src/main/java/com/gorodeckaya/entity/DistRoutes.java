package com.gorodeckaya.entity;

import com.gorodeckaya.entity.enums.TransportEnum;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="distroute")

public class DistRoutes {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_distroute", length = 11, nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_route")
    private Route route;

    @Column(name = "dist_cities")
    private String cities;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="id_typetranspotation", unique = true, nullable = false, updatable = false)
    private TypeTransportation typeTransportation;

    public DistRoutes() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getCities() {
        return cities;
    }


    public void setCities(String cities) {
        this.cities = cities;
    }

    public TypeTransportation getTypeTransportation() {
        return typeTransportation;
    }

    public void setTypeTransportation(TypeTransportation typeTransportation) {
        this.typeTransportation = typeTransportation;
    }

    @Override
    public String toString() {
        return "DistRoutes{" +
                "id=" + id +
                ", route=" + route +
                ", cities='" + cities + '\'' +
                ", typeTransportation=" + typeTransportation +
                '}';
    }
}
