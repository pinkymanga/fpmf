package mx.finerio.pfm.api.controllers

import mx.finerio.pfm.api.dtos.resource.ClientDto
import mx.finerio.pfm.api.logging.Log
import mx.finerio.pfm.api.services.SignupService
import mx.finerio.pfm.api.validation.SignupCommand

import javax.annotation.security.PermitAll
import javax.inject.Inject
import javax.validation.Valid

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller
class SignupController {

    @Inject
    SignupService signupService

    @Log
    @Post('/signup')
    @PermitAll
    HttpResponse<ClientDto> signup(@Valid @Body SignupCommand dto ) {
        HttpResponse.ok( signupService.create( dto ) )
    }

}
