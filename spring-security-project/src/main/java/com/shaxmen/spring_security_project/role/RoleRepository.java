package com.shaxmen.spring_security_project.role;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

  Optional<RoleEntity> findByName(String roleUser);
}
