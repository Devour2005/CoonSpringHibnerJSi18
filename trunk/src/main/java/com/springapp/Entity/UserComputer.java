package com.springapp.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
//@Table(name = "usercomp2")
@Table(name = "usercomp")
public class UserComputer implements Serializable {
    private static final long serialVersionUID = 4L;

    @Column(name = "user_id", unique = false, nullable = false)
    private Integer userId;
    @Column(name = "comp_id", unique = false, nullable = false)
    private Integer compId;


    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true, nullable = false)
    private Integer Id;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public UserComputer() {
    }

    public UserComputer(Integer userId, Integer compId) {
//        this.id = id;
        this.userId = userId;
        this.compId = compId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCompId() {
        return compId;
    }

    public void setCompId(Integer compId) {
        this.compId = compId;
    }
}



