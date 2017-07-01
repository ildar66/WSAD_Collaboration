package pagecode;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 * @generated
 *
 * Provides a common base class for all generated code behind files.
 */
public abstract class PageCodeBase {
	protected FacesContext facesContext;
	protected Map requestScope;
	protected Map sessionScope;
	protected Map applicationScope;
	protected Map requestParam;

	/**
	 * @generated
	 */
	public PageCodeBase() {
		facesContext = FacesContext.getCurrentInstance();

		requestScope =
			(Map) facesContext
				.getApplication()
				.createValueBinding("#{requestScope}")
				.getValue(facesContext);
		sessionScope =
			(Map) facesContext
				.getApplication()
				.createValueBinding("#{sessionScope}")
				.getValue(facesContext);
		applicationScope =
			(Map) facesContext
				.getApplication()
				.createValueBinding("#{applicationScope}")
				.getValue(facesContext);
		requestParam =
			(Map) facesContext
				.getApplication()
				.createValueBinding("#{param}")
				.getValue(facesContext);

	}

	/** 
	 * @generated
	 */
	protected void gotoPage(String pageName) {
		if (pageName != null) {
			UIViewRoot newView =
				facesContext.getApplication().getViewHandler().createView(
					facesContext,
					pageName);
			facesContext.setViewRoot(newView);
		}
	}

	/**
	 * <p>Return the {@link UIComponent} (if any) with the specified
	 * <code>id</code>, searching recursively starting at the specified
	 * <code>base</code>, and examining the base component itself, followed
	 * by examining all the base component's facets and children.
	 * Unlike findComponent method of {@link UIComponentBase}, which
	 * skips recursive scan each time it finds a {@link NamingContainer},
	 * this method examines all components, regardless of their namespace
	 * (assuming IDs are unique).
	 *
	 * @param base Base {@link UIComponent} from which to search
	 * @param id Component identifier to be matched
	 */
	public static UIComponent findComponent(UIComponent base, String id) {

		// Is the "base" component itself the match we are looking for?
		if (id.equals(base.getId())) {
			return base;
		}

		// Search through our facets and children
		UIComponent kid = null;
		UIComponent result = null;
		Iterator kids = base.getFacetsAndChildren();
		while (kids.hasNext() && (result == null)) {
			kid = (UIComponent) kids.next();
			if (id.equals(kid.getId())) {
				result = kid;
				break;
			}
			result = findComponent(kid, id);
			if (result != null) {
				break;
			}
		}
		return result;
	}

	public static UIComponent findComponentInRoot(String id) {
		UIComponent ret = null;

		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null) {
			UIComponent root = context.getViewRoot();
			ret = findComponent(root, id);
		}

		return ret;
	}

	/**
	 * @generated
	 * Place an Object on the tree's attribute map
	 * 
	 * @param key
	 * @param value
	 */
	protected void putTreeAttribute(String key, Object value) {
		getFacesContext().getViewRoot().getAttributes().put(key, value);
	}

	/**
	 * @generated
	 * Retrieve an Object from the tree's attribute map
	 * @param key
	 * @return
	 */
	protected Object getTreeAttribute(String key) {
		return getFacesContext().getViewRoot().getAttributes().get(key);
	}

	/**
	 * @generated
	 * Return the result of the resolved expression
	 * 
	 * @param expression
	 * @return
	 */
	protected Object resolveExpression(String expression) {
		Object value = null;
		if ((expression.indexOf("#{") != -1)
			&& (expression.indexOf("#{") < expression.indexOf('}'))) {
			value =
				getFacesContext().getApplication().createValueBinding(
					expression).getValue(
					getFacesContext());
		} else {
			value = expression;
		}
		return value;
	}

	/**
	 * @generated
	 * Resolve all parameters passed in via the argNames/argValues array pair, and 
	 * add them to the provided paramMap. If a parameter can not be resolved, then it
	 * will attempt to be retrieved from a cachemap stored using the cacheMapKey
	 * 
	 * @param paramMap
	 * @param argNames
	 * @param argValues
	 * @param cacheMapKey
	 */
	protected void resolveParams(
		Map paramMap,
		String[] argNames,
		String[] argValues,
		String cacheMapKey) {

		Object rawCache = getTreeAttribute(cacheMapKey);
		Map cache = Collections.EMPTY_MAP;
		if (rawCache instanceof Map) {
			cache = (Map) rawCache;
		}
		for (int i = 0; i < argNames.length; i++) {
			Object result = resolveExpression(argValues[i]);
			if (result == null) {
				result = cache.get(argNames[i]);
			}
			paramMap.put(argNames[i], result);
		}
		putTreeAttribute(cacheMapKey, paramMap);
	}

	/** 
	 * @generated
	 * Returns a full system path for a file path given relative to the web project
	 */
	protected static String getRealPath(String relPath) {
		String path = relPath;
		try {
			URL url =
				FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.getResource(
					relPath);
			if (url != null) {
				path = url.getPath();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return path;
	}
	/**
	 *
	 */
	protected void logException(Throwable throwable) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		throwable.printStackTrace(printWriter);
		log(stringWriter.toString());
	}
	/**
	 * @generated
	 */
	protected void log(String message) {
		System.out.println(message);
	}
	/**
	 * @return
	 */
	public Map getApplicationScope() {
		return applicationScope;
	}

	/**
	 * @return
	 */
	public FacesContext getFacesContext() {
		return facesContext;
	}

	/**
	 * @return
	 */
	public Map getRequestParam() {
		return requestParam;
	}

	/**
	 * @return
	 */
	public Map getRequestScope() {
		return requestScope;
	}

	/**
	 * @return
	 */
	public Map getSessionScope() {
		return sessionScope;
	}

}