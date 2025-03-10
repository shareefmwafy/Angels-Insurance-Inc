package com.asal.insurance_system.Model;

import com.asal.insurance_system.Enum.Permission;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "list_of_role")
    private List<Permission> listOfPermission;
}
