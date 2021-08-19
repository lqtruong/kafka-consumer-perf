package com.turong.training.rest.controller;

public interface WebValidator<WebRequest> {

    boolean validate(final WebRequest request);

}
