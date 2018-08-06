package com.willi.entity;

import javax.persistence.*;

@Entity
@Table(name = "invate_code_cache", schema = "OPAS", catalog = "")
public class InvateCodeCacheEntity {
    private String createdUser;
    private String code;

    @Id
    @Column(name = "createdUser")
    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvateCodeCacheEntity that = (InvateCodeCacheEntity) o;

        if (createdUser != null ? !createdUser.equals(that.createdUser) : that.createdUser != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = createdUser != null ? createdUser.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
