package com.asal.insurance_system.Model;
import com.asal.insurance_system.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "users")
public class User extends BasePerson implements UserDetails {

    @Column(name = "department_id")
    private Integer departmentId;
    @NotNull(message = "Hiring date is mandatory")
    @PastOrPresent(message = "Hiring date cannot be in the future")
    @Column(name = "hiring_date")
    private Date hiringDate;
    @Positive(message = "Salary must be a positive number")
    @Column(name = "salary")
    private Float salary;

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        User user = (User) obj;
        return Objects.equals(id, user.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                email,
                idNumber,
                firstName,
                lastName,
                role
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }
}
