package com.etf.unsa.ba.nwt.user.repository;

import com.etf.unsa.ba.nwt.user.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}