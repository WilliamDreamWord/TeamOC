package com.willi.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_invate_code", schema = "OPAS", catalog = "")
public class UserInvateCodeEntity {
    private String code;
    private String createdUser;
    private String createdDate;
    private Integer numVersion;
    private String acceptedUser1;
    private String acceptedDate1;
    private String acceptedUser2;
    private String acceptedDate2;
    private String acceptedUser3;
    private String acceptedDate3;
    private String acceptedUser4;
    private String acceptedDate4;

    @Id
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "createdUser")
    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    @Basic
    @Column(name = "createdDate")
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "numVersion")
    public Integer getNumVersion() {
        return numVersion;
    }

    public void setNumVersion(Integer numVersion) {
        this.numVersion = numVersion;
    }

    @Basic
    @Column(name = "acceptedUser1")
    public String getAcceptedUser1() {
        return acceptedUser1;
    }

    public void setAcceptedUser1(String acceptedUser1) {
        this.acceptedUser1 = acceptedUser1;
    }

    @Basic
    @Column(name = "acceptedDate1")
    public String getAcceptedDate1() {
        return acceptedDate1;
    }

    public void setAcceptedDate1(String acceptedDate1) {
        this.acceptedDate1 = acceptedDate1;
    }

    @Basic
    @Column(name = "acceptedUser2")
    public String getAcceptedUser2() {
        return acceptedUser2;
    }

    public void setAcceptedUser2(String acceptedUser2) {
        this.acceptedUser2 = acceptedUser2;
    }

    @Basic
    @Column(name = "acceptedDate2")
    public String getAcceptedDate2() {
        return acceptedDate2;
    }

    public void setAcceptedDate2(String acceptedDate2) {
        this.acceptedDate2 = acceptedDate2;
    }

    @Basic
    @Column(name = "acceptedUser3")
    public String getAcceptedUser3() {
        return acceptedUser3;
    }

    public void setAcceptedUser3(String acceptedUser3) {
        this.acceptedUser3 = acceptedUser3;
    }

    @Basic
    @Column(name = "acceptedDate3")
    public String getAcceptedDate3() {
        return acceptedDate3;
    }

    public void setAcceptedDate3(String acceptedDate3) {
        this.acceptedDate3 = acceptedDate3;
    }

    @Basic
    @Column(name = "acceptedUser4")
    public String getAcceptedUser4() {
        return acceptedUser4;
    }

    public void setAcceptedUser4(String acceptedUser4) {
        this.acceptedUser4 = acceptedUser4;
    }

    @Basic
    @Column(name = "acceptedDate4")
    public String getAcceptedDate4() {
        return acceptedDate4;
    }

    public void setAcceptedDate4(String acceptedDate4) {
        this.acceptedDate4 = acceptedDate4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInvateCodeEntity that = (UserInvateCodeEntity) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (createdUser != null ? !createdUser.equals(that.createdUser) : that.createdUser != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (numVersion != null ? !numVersion.equals(that.numVersion) : that.numVersion != null) return false;
        if (acceptedUser1 != null ? !acceptedUser1.equals(that.acceptedUser1) : that.acceptedUser1 != null)
            return false;
        if (acceptedDate1 != null ? !acceptedDate1.equals(that.acceptedDate1) : that.acceptedDate1 != null)
            return false;
        if (acceptedUser2 != null ? !acceptedUser2.equals(that.acceptedUser2) : that.acceptedUser2 != null)
            return false;
        if (acceptedDate2 != null ? !acceptedDate2.equals(that.acceptedDate2) : that.acceptedDate2 != null)
            return false;
        if (acceptedUser3 != null ? !acceptedUser3.equals(that.acceptedUser3) : that.acceptedUser3 != null)
            return false;
        if (acceptedDate3 != null ? !acceptedDate3.equals(that.acceptedDate3) : that.acceptedDate3 != null)
            return false;
        if (acceptedUser4 != null ? !acceptedUser4.equals(that.acceptedUser4) : that.acceptedUser4 != null)
            return false;
        if (acceptedDate4 != null ? !acceptedDate4.equals(that.acceptedDate4) : that.acceptedDate4 != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (createdUser != null ? createdUser.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (numVersion != null ? numVersion.hashCode() : 0);
        result = 31 * result + (acceptedUser1 != null ? acceptedUser1.hashCode() : 0);
        result = 31 * result + (acceptedDate1 != null ? acceptedDate1.hashCode() : 0);
        result = 31 * result + (acceptedUser2 != null ? acceptedUser2.hashCode() : 0);
        result = 31 * result + (acceptedDate2 != null ? acceptedDate2.hashCode() : 0);
        result = 31 * result + (acceptedUser3 != null ? acceptedUser3.hashCode() : 0);
        result = 31 * result + (acceptedDate3 != null ? acceptedDate3.hashCode() : 0);
        result = 31 * result + (acceptedUser4 != null ? acceptedUser4.hashCode() : 0);
        result = 31 * result + (acceptedDate4 != null ? acceptedDate4.hashCode() : 0);
        return result;
    }
}
