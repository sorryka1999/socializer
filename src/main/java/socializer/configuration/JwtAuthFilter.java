package socializer.configuration;

import socializer.entity.PortalUserEntity;
import socializer.repository.PortalUserRepository;
import socializer.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static socializer.constant.Endpoint.AUTHENTICATE;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String AUTH_HEADER_VALUE_STARTS_WITH = "Bearer ";
    private static final int AUTH_HEADER_SUBSTRING_BEGIN_INDEX = 7;

    private final SecretKeyProvider secretKeyProvider;
    private final PortalUserRepository portalUserRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        if (request.getServletPath().contains(AUTHENTICATE)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader(AUTH_HEADER_NAME);
        if (authHeader == null || !authHeader.startsWith(AUTH_HEADER_VALUE_STARTS_WITH)) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = authHeader.substring(AUTH_HEADER_SUBSTRING_BEGIN_INDEX);
        Long portalUserId;
        try {
            portalUserId = JwtUtil.validateTokenAndGetPortalUserId(secretKeyProvider.getSecretKey(), jwt);

            PortalUserEntity portalUserEntity = portalUserRepository
                    .findById(portalUserId)
                    .orElseThrow();

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    portalUserEntity,
                    null,
                    portalUserEntity.getAuthorities()
            );

            authenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
