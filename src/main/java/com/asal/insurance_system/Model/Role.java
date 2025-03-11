package com.asal.insurance_system.Model;

import com.asal.insurance_system.Enum.EnumRolePermission;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "list_of_role")
    private List<EnumRolePermission> listOfEnumRolePermission;
}
