package com.example.h8.bean.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class KieliValidator implements 
		ConstraintValidator<Kieli, String> {
	
	public void initialize(Kieli kieli) {
	
	}
	
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (!(value == null || value.length() == 0)) {
			return value.equals("suomi")
					|| value.equals("ruotsi")
					|| value.equals("englanti")
					|| value.equals("saksa")
					|| value.equals("viro")
					|| value.equals("japani")
					|| value.equals("kiina");
		} else {
			return false;
		}
	}
}