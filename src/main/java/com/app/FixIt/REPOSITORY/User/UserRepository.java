package com.app.FixIt.REPOSITORY.User;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.FixIt.ENTITIES.Maintenance.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
