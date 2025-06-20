package upeu.edu.pe.lp.infrastructure.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class loginHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        AtomicReference<String> redirectURL = new AtomicReference<>(request.getContextPath());
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userDetails.getAuthorities().forEach(
                grantedAuthority -> {
                    if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                        redirectURL.set("/admin");
                    } else {
                        redirectURL.set("/home");
                    }

                }
        );
        response.sendRedirect(String.valueOf(redirectURL));
    }

}
