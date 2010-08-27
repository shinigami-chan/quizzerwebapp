package com.tinesoft.quizzer.action;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action that checks whether the player is logged in. If so, we forward to the
 * page it was attempting to access( Add Category Page, Add Question Page, ...)
 * Otherwise, we redirect to the Login Page.
 * 
 * @author kondo tine
 * 
 */
public class LoginRequiredAction extends Action
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		// we fetch the page to forward to after verification
		// we fetch the authorized forwards from the action mapping
		// if the next page is not among them, we set it to "Home Page"

		String nextPage = request.getParameter("page");

		ArrayList<String> authorizedForwards = new ArrayList<String>(
				Arrays.asList(mapping.findForwards()));

		if (!authorizedForwards.contains(nextPage))
			nextPage = "Home Page";

		//if the user is not logged in, we forward to the login page
		//after saving the page he was trying to access ( NEXT_PAGE)
		if (request.getSession().getAttribute("PLAYER") == null)
		{
			request.getSession().setAttribute("NEXT_PAGE", nextPage);
			return mapping.findForward("Login Page");
		}
		else
		{
			return mapping.findForward(nextPage);

		}

	}

}
