package com.tinesoft.quizzer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action that logs the player out (removes it from session).
 * Then, we forward to the home page .
 * 
 * @author kondo tine
 */
public class LogoutAction extends Action {
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


		HttpSession session = request.getSession(true);
		//we remove the player from the session
		session.removeAttribute("PLAYER");
		
        return mapping.findForward("Home Page");
    }
}
