package com.tinesoft.quizzer.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.DynaValidatorForm;

import com.tinesoft.quizzer.model.dao.PlayerDAO;
import com.tinesoft.quizzer.model.domain.Player;
import com.tinesoft.quizzer.util.Utils;

/**
 * Action that logs the player in ( save him in the session).
 * 
 * @author kondo tine
 * 
 */
public class LoginAction extends Action {
	private PlayerDAO playerDAO = null;

	public PlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public void setPlayerDAO(PlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaValidatorForm loginForm = (DynaValidatorForm) form;
		String login = loginForm.getString("login");
		String password = loginForm.getString("password");

		// we fetch the page to forward to after, if the login succeeded
		HttpSession session = request.getSession();
		String nextPage = (String) session.getAttribute("NEXT_PAGE");

		if (nextPage == null)
			nextPage = "Home Page";

		session.removeAttribute("NEXT_PAGE");

		try {
			// we try to get the player matching the login and password
			Player player = playerDAO.get(login, password);

			if (player != null) {
				// we update the last connection date, and we save the player in
				// the session
				
				player.setLastConnectionDate(Utils.todayTimestamp());
				playerDAO.saveOrUpdate(player);
				request.getSession().setAttribute("PLAYER", player);
				return mapping.findForward(nextPage);
			} else {
				ActionErrors errors = new ActionErrors();
				errors.add("loginFailed", new ActionMessage(
						"errors.login.failed"));
				saveErrors(request, errors);
				return mapping.getInputForward();
			}

		} catch (Exception e) {
			// if an error occurred, we redirect to the form
			ActionErrors errors = new ActionErrors();
			errors.add("loginFailed", new ActionMessage(
					"errors.exception.occurred"));
			saveErrors(request, errors);
			return mapping.getInputForward();
		}

	}

}
