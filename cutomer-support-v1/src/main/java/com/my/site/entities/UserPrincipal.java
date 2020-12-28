package com.my.site.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UserPrincipal_Username", columnNames = "Username")
})
@XmlAccessorType(XmlAccessType.NONE)
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE,
          fieldVisibility = JsonAutoDetect.Visibility.NONE,
          getterVisibility = JsonAutoDetect.Visibility.NONE,
          isGetterVisibility = JsonAutoDetect.Visibility.NONE,
          setterVisibility = JsonAutoDetect.Visibility.NONE)
public class UserPrincipal implements UserDetails, CredentialsContainer, Cloneable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String username;
    private byte[] hashedPassword;
    private Set<UserAuthority> authorities = new HashSet<>();
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @Id
    @Column(name = "UserId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "HashedPassword")
    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(byte[] hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setAuthorities(Set<UserAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void eraseCredentials() {
        this.hashedPassword = null;
    }

    @Override
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "UserPrincipal_Authority", joinColumns = {
            @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    })
    public Set<UserAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    @Transient
    public String getPassword() {
        return this.getHashedPassword() == null ? null :
                new String(this.getHashedPassword(), StandardCharsets.UTF_8);
    }

    @Override
    @XmlElement
    @JsonProperty
    public String getUsername() {
        return this.username;
    }

    @Override
    @XmlElement
    @JsonProperty
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    @XmlElement
    @JsonProperty
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    @XmlElement
    @JsonProperty
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof UserPrincipal &&
                ((UserPrincipal)o).id == this.id;
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

    @Override
    protected UserPrincipal clone() {
        try {
            return (UserPrincipal)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return this.username;
    }
}
