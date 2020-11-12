package com.gorodeckaya.entity;

import com.gorodeckaya.entity.enums.TransportEnum;
import com.gorodeckaya.entity.enums.TypeGoodsEnum;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="deal")
public class Deal {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_deal", length = 11, nullable = false)
    private long id;

    @Column(name = "code")
    private int code;
    @Column(name = "type_goods")
    private TypeGoodsEnum type_goods;
    @Column(name = "price")
    private Double price;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "size")
    private Double size;
    @Column(name = "time")
    private Double time;
    @Column(name = "address_from")
    private String address_from;
    @Column(name = "address_to")
    private String address_to;
    @Column(name = "city_from")
    private String city_from;
    @Column(name = "city_to")
    private String city_to;

    @ManyToOne(fetch = FetchType.LAZY) //orphanRemoval=true
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY) //orphanRemoval=true
    @JoinColumn(name = "id_route")
    private Route route;

//    @OneToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name="id_distroute", unique = false, nullable = false, updatable = false)
//    private DistRoutes distRoutes;

    @Transient
    List<TransportEnum> typeTransportationList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public TypeGoodsEnum getType_goods() {
        return type_goods;
    }

    public void setType_goods(TypeGoodsEnum type_goods) {
        this.type_goods = type_goods;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getAddress_from() {
        return address_from;
    }

    public void setAddress_from(String address_from) {
        this.address_from = address_from;
    }

    public String getAddress_to() {
        return address_to;
    }

    public void setAddress_to(String address_to) {
        this.address_to = address_to;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getCity_from() {
        return city_from;
    }

    public void setCity_from(String city_from) {
        this.city_from = city_from;
    }

    public String getCity_to() {
        return city_to;
    }

    public void setCity_to(String city_to) {
        this.city_to = city_to;
    }

    public List<TransportEnum> getTypeTransportationList() {
        return typeTransportationList;
    }

    public void setTypeTransportationList(List<TransportEnum> typeTransportationList) {
        this.typeTransportationList = typeTransportationList;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", code=" + code +
                ", type_goods=" + type_goods +
                ", price=" + price +
                ", weight=" + weight +
                ", size=" + size +
                ", time=" + time +
                ", address_from='" + address_from + '\'' +
                ", address_to='" + address_to + '\'' +
                ", city_from='" + city_from + '\'' +
                ", city_to='" + city_to + '\'' +
                ", client=" + client +
                ", route=" + route +
                ", typeTransportationList=" + typeTransportationList +
                '}';
    }

}
