package org.raul.receipesweb.repository;

import org.raul.receipesweb.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByUsername(String username);
}
