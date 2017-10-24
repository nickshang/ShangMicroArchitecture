package com.shang.springboot.readinglist.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Think on 2017/6/9.
 */
@ApiModel(value = "Reader", description = "用户对象")
public class Reader implements UserDetails {
    private static final long serialVersionUID = 1L;

    public Reader(){

    }

    @ApiModelProperty(value = "id")
    @Setter
    @Getter
    private long id;

    @Setter
    @Getter
    private String username;

    @Setter
    @Getter
    private String fullname;

    @Setter
    @Getter
    private String password;

    // UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_READER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
