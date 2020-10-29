package com.gorodeckaya.service.impl;

import com.gorodeckaya.entity.Client;
import com.gorodeckaya.entity.Partner;
import com.gorodeckaya.entity.User;
import com.gorodeckaya.entity.enums.RoleEnum;
import com.gorodeckaya.repository.ClientRepository;
import com.gorodeckaya.repository.PartnerRepository;
import com.gorodeckaya.repository.UserRepository;
import com.gorodeckaya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {


    @PersistenceContext
    private EntityManager em;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveClient(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setRole(RoleEnum.ROLE_USER);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //userRepository.save(user);

        Client client = new Client();
        client.setName(user.getName());
        client.setSurname(user.getSurname());
        client.setMobphone(user.getMobphone());
        client.setGender(user.getGender());
        client.setEmail(user.getUsername());
        client.setUser(user);
        clientRepository.save(client);
        userRepository.save(user);
        return true;
    }
    public boolean savePartner(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        Partner partner = new Partner();
        user.setRole(RoleEnum.ROLE_PARTNER);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        partner.setName(user.getName());
        partner.setSurname(user.getSurname());
        partner.setMobphone(user.getMobphone());
        partner.setGender(user.getGender());
        partner.setEmail(user.getUsername());
        partner.setCompany(user.getCompany());
        partner.setAddress(user.getAddress());
        partner.setUser(user);
        partnerRepository.save(partner);
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }


    @Override
    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User editUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getById_user(long id) {
        return userRepository.findById(id);
    }


    @Override
    public List<User> getAdmin() {
        return userRepository.findAdmin();
    }

}
