package util;

import dao.Dao;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import modelo.Animal;

@FacesConverter(value = "animalConverter")
public class AnimalConverter implements Converter {
    
@Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String nome) {
        Animal temp = null;
        Dao<Animal> dao = new Dao(Animal.class);
        try {
            temp = dao.buscarPorNome(nome);
	} catch (Exception e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro","Selecione um Animal"));
	}
 	return temp;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object obj) {
        if (obj instanceof Animal){
            Animal c = (Animal)obj;
            return c.getNome();
        }
        return "";
    }
}