package com.yucong.web;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.yucong.domain.User;
import com.yucong.domain.UserEditor;

public class MyBindingInitializer implements WebBindingInitializer{
  public void initBinder(WebDataBinder binder, WebRequest request) {
	  binder.registerCustomEditor(User.class, new UserEditor());
   }
}
