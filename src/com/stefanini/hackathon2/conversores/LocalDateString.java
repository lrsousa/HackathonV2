package com.stefanini.hackathon2.conversores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="localDateTimeConverter")
public class LocalDateString implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String dataString) {
		return LocalDate.parse(dataString);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object localDateUnformated) {
		LocalDate localDate = (LocalDate) localDateUnformated;
		return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

}
