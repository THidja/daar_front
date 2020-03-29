package daar.front.forms;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import daar.front.Engine;


@ManagedBean(name="ssForm")
public class SimpleSearchForm {
	
	private String word;
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public String submit() throws IOException {
		if(Engine.simpleSearch(word)) {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.redirect(context.getRequestContextPath() + "/result.xhtml");
		}
		return "";
	}
}