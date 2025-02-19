package com.example.mesadmin.config;

import com.nvc.core.dto.response.UserResponseDto;
import com.nvc.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService, InitializingBean {

    private final UserService userService;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponseDto userDto = userService.getByUsername(username);
        if (Objects.isNull(userDto)){
            throw new UsernameNotFoundException(USER_NOT_FOUND);
        }

        Set<String> cols = userDto.getAuthorities();
        Iterator iAuthority = cols.iterator();

        while (iAuthority.hasNext()){
            GrantedAuthority grantedAuthor = (GrantedAuthority) iAuthority.next();
            userDto.getAuthorities().add(String.valueOf(grantedAuthor));
        }
        return userDto;
    }
}
