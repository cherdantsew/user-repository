package com.deliverengine.user.service;

import com.deliverengine.core.enumeration.RolesConstants;
import com.deliverengine.core.security.jwt.JWTConfigurer;
import com.deliverengine.core.security.jwt.TokenProvider;
import com.deliverengine.user.model.dto.JWTToken;
import com.deliverengine.user.model.dto.UserAuthDataDto;
import com.deliverengine.user.model.entity.User;
import com.deliverengine.user.service.abstraction.IAuthenticationService;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@RequiredArgsConstructor
@Slf4j
@Service
public abstract class AbstractCommonAuthenticationService implements IAuthenticationService {

    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity authenticate(UserAuthDataDto userAuthDataDto) {
        User user = userService.findUserByLogin(userAuthDataDto.getLogin()).orElseThrow(NoSuchElementException::new);
        if (getRoleByLoginUrl() != user.getRoles().stream().findFirst().orElseThrow().getRoleName()) {
            return buildUnauthorizedAuthenticationUrlResponse();
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getLogin(), userAuthDataDto.getPassword());
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(token);
        } catch (Exception e) {
            log.error("Authentication exception", e);
            return buildAuthError(e.getMessage());
        }
        return buildSuccessAuthResponse(token, authentication, user);
    }

    private ResponseEntity<JWTToken> buildSuccessAuthResponse(UsernamePasswordAuthenticationToken token, Authentication authentication, User user) {
        Map<String, Object> tokenDetails = Map.of(TokenProvider.CURRENT_ROLE_KEY, user.getRoles().stream().findFirst().orElseThrow().getRoleName().name(), TokenProvider.LOGIN_KEY, user.getLogin());
        token.setDetails(tokenDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String authorities = authentication.getAuthorities().stream().map(obj -> obj.getAuthority()).collect(Collectors.joining());
        String jwtTokenString = tokenProvider.createToken(TokenProvider.JwtTokenConfiguration.fromParams(authentication, authorities, tokenDetails));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwtTokenString);
        httpHeaders.add("Content-type", MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<>(new JWTToken(jwtTokenString), httpHeaders, HttpStatus.OK);
    }

    ResponseEntity<Object> buildAuthError(String message) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Problem.builder().withStatus(Status.UNAUTHORIZED).withDetail(message).build());
    }

    ResponseEntity<Object> buildUnauthorizedAuthenticationUrlResponse() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Problem.builder().withStatus(Status.BAD_REQUEST).withDetail("Authorization path doesn't correspond to user role. User another authorization url.").build());
    }

    protected abstract RolesConstants getRoleByLoginUrl();

}
