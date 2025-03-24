//package com.asal.insurance_system.Model;
//
//import com.asal.insurance_system.Enum.EnumRolePermission;
//import jakarta.persistence.*;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Table(name = "role")
//public class Role implements GrantedAuthority {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "role_id")
//    private Integer roleId;
//
//
//
//    @Column(name = "name", length= 20)
//    private String name;
//
////    @ManyToMany(mappedBy = "roles")
////    private Set<Employee> employees;
//
//    @Override
//    public String getAuthority() {
//        return  name;
//    }
//}
