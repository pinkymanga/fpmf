package mx.finerio.pfm.api.controllers


import io.micronaut.context.MessageSource
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Error

import io.micronaut.http.hateoas.JsonError
import io.micronaut.validation.Validated
import io.reactivex.Single
import mx.finerio.pfm.api.domain.User
import mx.finerio.pfm.api.dtos.UserDto
import mx.finerio.pfm.api.dtos.UserErrorDto
import mx.finerio.pfm.api.exeptions.UserNotFoundException
import mx.finerio.pfm.api.validation.UserCreateCommand
import mx.finerio.pfm.api.services.UserService

import javax.inject.Inject
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import io.micronaut.http.*

@Validated
@Controller("/users")
class UserController {

    @Inject
    UserService userService

    @Inject
    MessageSource messageSource

    @Post("/")
    Single<UserDto> save(@Body @Valid UserCreateCommand cmd){
        Single.just(new UserDto(userService.save(new User(cmd.name))))
    }

    @Get("/{id}")
    Single<UserDto> show(Long id) {
        Single.just(new UserDto(Optional.ofNullable(userService.getById(id))
                .orElseThrow({ -> new UserNotFoundException('The user ID you requested was not found.') })))
    }

    @Error
    HttpResponse<List<UserErrorDto>> jsonError(ConstraintViolationException constraintViolationException) {
        HttpResponse.<List<UserErrorDto>> status(HttpStatus.BAD_REQUEST,
                messageBuilder("system.json.invalid").get()).body(
                constraintViolationException.constraintViolations.collect {
                    new UserErrorDto(it.message, messageSource)
                })
    }

    @Error(status = HttpStatus.BAD_REQUEST)
    HttpResponse<JsonError> badRequest(HttpRequest request) {
        HttpResponse.<JsonError>badRequest().body(
                new JsonError(messageBuilder("user.malformed.message").orElse('Bad Request'))
        )
    }

    private Optional<String> messageBuilder(String code) {
        messageSource.getMessage(code, MessageSource.MessageContext.DEFAULT)
    }

    @Error(exception = UserNotFoundException)
    HttpResponse<JsonError> notFound(UserNotFoundException ex) {
        HttpResponse.<JsonError>notFound().body(new JsonError(ex.message))
    }
}
