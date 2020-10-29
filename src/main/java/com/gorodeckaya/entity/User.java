package com.gorodeckaya.entity;

import com.gorodeckaya.entity.enums.RoleEnum;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name="user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name= "native", strategy= "native")
    @Column(name = "id_user", length = 11, nullable = false)
    private long id_user;

    @Transient
    private String passwordConfirm;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    @Column(name = "role")
    private RoleEnum role;

    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
//    @Column(name="activation_code")
//    private String activationCode;


    @OneToOne(mappedBy="user", cascade = CascadeType.ALL)
    public Client client;

    @OneToOne(mappedBy="user", cascade = CascadeType.ALL)
    private Partner partner;

    //for registration
    @Transient
    private Person person=new Person();



    public User() {
    }

    public User(String passwordConfirm, RoleEnum role, String username, String password) {
        this.passwordConfirm = passwordConfirm;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public User(String passwordConfirm, RoleEnum role, String username, String password, Client client, Partner partner) {
        this.passwordConfirm = passwordConfirm;
        this.role = role;
        this.username = username;
        this.password = password;
        this.client = client;
        this.partner = partner;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority(role.getAuthority()));

        return list;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return person.getSurname();
    }

    public void setSurname(String surname) {
        this.person.setSurname(surname);
    }

    public String getName() {
        return person.getName();
    }

    public void setName(String name) {
        this.person.setName(name);
    }

    public String getMobphone() {
        return person.getMobphone();
    }

    public void setMobphone(String mobphone) {
        this.person.setMobphone(mobphone);
    }

    public String getGender() {
        return person.getGender();
    }

    public void setGender(String gender) {
        this.person.setGender(gender);
    }

    public String getAddress() {
        return person.getAddress();
    }

    public void setAddress(String address) {
        this.person.setAddress(address);
    }

    public String getCompany() {
        return person.getCompany();
    }

    public void setCompany(String company) {
        this.person.setCompany(company);
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
