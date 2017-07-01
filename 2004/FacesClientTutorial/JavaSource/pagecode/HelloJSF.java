/*
 * Created on Apr 21, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pagecode;

import com.ibm.odcb.simple.businessobjects.Hello;
import com.ibm.faces.bf.component.html.HtmlClientData;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
/**
 * @author fenil
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class HelloJSF extends PageCodeBase {

	protected Hello hello;
	protected HtmlClientData dm1;
	protected HtmlForm myForm;
	protected HtmlInputText input1;
	protected HtmlInputText input2;
	/** 
	* @author WebSphere Studio
	* @beanName hello
	* @managed-bean true
	* @beanClass com.ibm.odcb.simple.businessobjects.Hello
	*/
	public Hello getHello() {
		if (hello == null) {
			hello = new Hello();
			hello =
				(Hello) getFacesContext()
					.getApplication()
					.createValueBinding("#{hello}")
					.getValue(getFacesContext());
		}
		return hello;
	}
	public void setHello(Hello hello) {
		this.hello = hello;
	}
	protected HtmlClientData getDm1() {
		if (dm1 == null) {
			dm1 = (HtmlClientData) findComponentInRoot("dm1");
		}
		return dm1;
	}
	protected HtmlForm getMyForm() {
		if (myForm == null) {
			myForm = (HtmlForm) findComponentInRoot("myForm");
		}
		return myForm;
	}
	protected HtmlInputText getInput1() {
		if (input1 == null) {
			input1 = (HtmlInputText) findComponentInRoot("input1");
		}
		return input1;
	}
	protected HtmlInputText getInput2() {
		if (input2 == null) {
			input2 = (HtmlInputText) findComponentInRoot("input2");
		}
		return input2;
	}
}