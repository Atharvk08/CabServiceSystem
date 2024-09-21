package com.cabservice.profile_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cabservice.profile_service.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
