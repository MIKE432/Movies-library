package com.apusart.moviesliblary.tools

class BadRequestException(msg: String, val code: Int = 400): Exception(msg)

class UnauthorizedException(msg: String, val code: Int = 401): Exception(msg)

class ForbiddenException(msg: String, val code: Int = 403): Exception(msg)

class NotFoundException(msg: String, val code: Int = 404): Exception(msg)

class InternalServerException(msg: String, val code: Int = 500): Exception(msg)

class TimeoutException(msg: String, val code: Int = 504): Exception(msg)

class CustomException(msg: String, code: Int): Exception(msg)
