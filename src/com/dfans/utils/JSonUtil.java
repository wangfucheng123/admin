package com.dfans.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSonUtil {
	private static Gson gson = new GsonBuilder().create();
	public static void retJosn(HttpServletResponse response, Object obj) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(gson.toJson(obj));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}