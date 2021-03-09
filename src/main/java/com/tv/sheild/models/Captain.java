package com.tv.sheild.models;

import com.tv.sheild.acl.Acl;
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
}
