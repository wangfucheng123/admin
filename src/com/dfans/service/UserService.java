package com.dfans.service;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

	Map save(HttpServletRequest request) throws ParseException;

	Map getLogin(HttpServletRequest request) throws Exception;

}
