package com.wyt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ${Weiyt} on 2017/7/31.
 */

@Entity
@Table(name = "user")
public class UserInfo {
    @Id
    private String id;
    private String name;
    private String password;
    @Column(name="nickname")
    private String nickName;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private String avatar;
    public String getNickname() {
        return nickName;
    }

    public void setNickname(String nickname) {
        this.nickName = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
