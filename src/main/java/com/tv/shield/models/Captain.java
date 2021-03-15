package com.tv.shield.models;

import com.tv.shield.acl.Acl;
import java.util.Objects;
import java.util.Set;

public class Captain {

    private String captainName;

    private Set<Acl> acl;

    public Captain(String captainName, Set<Acl> acl) {
        this.captainName = captainName;
        this.acl = acl;
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public Set<Acl> getAcl() {
        return acl;
    }

    public void setAcl(Set<Acl> acl) {
        this.acl = acl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Captain captain = (Captain) o;
        return getCaptainName().equals(captain.getCaptainName()) &&
                getAcl().equals(captain.getAcl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCaptainName(), getAcl());
    }
}
