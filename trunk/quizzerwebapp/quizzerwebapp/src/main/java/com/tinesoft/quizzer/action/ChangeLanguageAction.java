package com.tinesoft.quizzer.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.RedirectingActionForward;


/**
 * Action that changes the application locale .
 * 
 * @author kondo tine
 * 
 */

public class ChangeLanguageAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{

		HttpSession session = request.getSession();

		// fetching request's parameters and the referer URL 
		String country = request.getParameter("cty");
		String language = request.getParameter("lang");
		String referer = request.getHeader("referer");

		ActionForward forward = null;

		//current locale
		Locale locale = getLocale(request);

		boolean isLanguage = ((language != null) && (language.length() > 0));
		boolean isCountry = ((country != null) && (country.length() > 0));

		if ((isLanguage) && (isCountry))
		{
			locale = new java.util.Locale(language, country);
		}
		else if (isLanguage)
		{
			locale = new java.util.Locale(language, "");
		}

		session.setAttribute(Globals.LOCALE_KEY, locale);

		// forwarding to Home Page, if no referer
		if (referer == null)
		{
			forward = (mapping.findForward("Home Page"));
		}
		// forwarding to referrer URL.
		else
		{
			forward = new RedirectingActionForward();
			forward.setPath(referer);
		}

		return forward;

	}
}
