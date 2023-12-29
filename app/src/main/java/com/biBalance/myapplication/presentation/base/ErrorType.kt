package com.biBalance.myapplication.presentation.base

open class BpException(message: String) : Exception(message)

//region InternetException
class NoInternetException : BpException("No internet connection")

//endregion
open class AuthorizationException(message: String) : BpException(message)
class PermissionDenied(message: String) : AuthorizationException(message)


open class RequestException(message: String) : BpException(message)
class ServerSideException : RequestException("")
class UnknownErrorException(val errorMessage: String) : RequestException(errorMessage)
class UserNotFoundException(val errorMessage: String) : RequestException(errorMessage)
class WrongPasswordException(val errorMessage: String) : RequestException(errorMessage)


open class InvalidCredentialsException() : BpException("")
class InvalidUserNameException : InvalidCredentialsException()
class InvalidPasswordException : InvalidCredentialsException()
class NotFoundedException : BpException("Not founded")

