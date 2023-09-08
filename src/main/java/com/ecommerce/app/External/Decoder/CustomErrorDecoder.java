package com.ecommerce.app.External.Decoder;

import java.io.IOException;

import com.ecommerce.app.Exception.CustomException;
import com.ecommerce.app.External.Response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
	// ErrorDecoder is a part of feign to handle errors
	// When we call the services using feignclient, the exceptions throws by other
	// services are catched by this error decoder

	@Override
	public Exception decode(String methodKey, Response response) {
		ObjectMapper objectMapper = new ObjectMapper();

		log.info("::{}", response.request().url());
		log.info("::{}", response.request().headers());

		try {
			ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
			
			return new CustomException(errorResponse.getErrorMessage(), errorResponse.getErrorCode(), response.status());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new CustomException("Internal Server Error", "INTERNAL_SERVER_ERROR", 500);
		}

	}

}
