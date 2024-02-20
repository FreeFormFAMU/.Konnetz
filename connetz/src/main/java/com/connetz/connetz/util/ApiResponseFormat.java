package com.connetz.connetz.util;

public record ApiResponseFormat(boolean success, String message, Object data, Object error) {}
