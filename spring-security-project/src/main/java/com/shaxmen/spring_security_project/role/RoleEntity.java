package com.shaxmen.spring_security_project.role;

import com.shaxmen.spring_security_project.common.BaseEntity;
import com.shaxmen.spring_security_project.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

  private String id;

  @Column(name = "name")
  private String name;

  @ManyToMany(mappedBy = "roles")
  private List<UserEntity> users;
}