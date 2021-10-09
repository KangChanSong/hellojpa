package hellojpa.domain;

import hellojpa.RoleType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static javax.persistence.EnumType.ORDINAL;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
public class Member {

    @Id
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(ORDINAL)
    private RoleType roleType;

    @Temporal(TIMESTAMP)
    private Date createDate;

    @Temporal(TIMESTAMP)
    private Date updateDate;

    @Lob
    private String description;

    @Transient
    private int iDontWantToBeInDB;

    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
