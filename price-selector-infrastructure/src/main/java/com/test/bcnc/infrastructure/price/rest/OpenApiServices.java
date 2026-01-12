package com.test.bcnc.infrastructure.price.rest;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Class for redirecting to the swagger default page.
 */
@RestController
@RequestMapping("/")
public class OpenApiServices {

    @Hidden
    @Operation(summary = "Redirects root address to API web site.")
    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public void root(HttpServletResponse response, HttpServletRequest httpRequest) throws IOException {
        response.sendRedirect("./swagger-ui/index.html");
    }
}
