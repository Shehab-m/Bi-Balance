package com.biBalance.myapplication.util

//region User
open class UserException : Exception()
class InvalidInputException : UserException()
class UsernameAlreadyExistException : UserException()
class InvalidUserIdException : UserException()
class InvalidUserNameOrPasswordException : UserException()
class InvalidEmailException : UserException()
class InvalidNameException : UserException()
class EmailAlreadyExistException : UserException()
class InvalidUserNameInputException : UserException()
class InvalidPasswordInputException : UserException()
class UnKnownUserException : UserException()
//endregion



//region GeneralException
open class GeneralException : Exception()
//endregion

//region Token
open class TokenException : Exception()
//endregion

//region Network
open class NetworkException : Exception()
class NoConnectionException : NetworkException()
class InvalidDataException : NetworkException()
class NotFoundException : NetworkException()
class InternalServerException : NetworkException()
class ForbiddenException : NetworkException()
class UnAuthorizedException : NetworkException()
//endregion


sealed interface ErrorHandler {

    //region User
    data class GeneralErrorException(val errorMessage: String) : ErrorHandler
}

fun handelUserException(
    exception: Exception,
    onError: (t: ErrorHandler) -> Unit,
) {
    onError(ErrorHandler.GeneralErrorException(exception.message ?: "Unknown error"))
}