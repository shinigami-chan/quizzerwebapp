package com.tinesoft.quizzer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.DynaValidatorForm;

import com.tinesoft.quizzer.model.dao.PlayerDAO;
import com.tinesoft.quizzer.model.dao.hibernate.PlayerDAOHib;
import com.tinesoft.quizzer.model.domain.Player;
import com.tinesoft.quizzer.util.Utils;

public class RegistrationAction extends Action {
	private PlayerDAO playerDAO = new PlayerDAOHib();

	public PlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public void setPlayerDAO(PlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm registrationForm = (DynaValidatorForm) form;
		Player player = (Player) registrationForm.get("player");
		String birthDateString = registrationForm.getString("birthDateString");
		String datePattern = registrationForm.getString("datePattern");

		player.setLastConnectionDate(Utils.todayTimestamp());
		player.setRegistrationDate(Utils.todayDate());
		
		// set the player's birth date from the birthDateString
		player.setBirthDate(Utils.dateFromString(birthDateString, datePattern));

		try {
			Player otherPlayer = playerDAO.get(player.getLogin());

			// we check whether the login already exists or not
			if (otherPlayer != null) {
				ActionErrors errors = new ActionErrors();
				errors.add("player.login", new ActionMessage(
						"errors.login.already.exists"));
				saveErrors(request, errors);

				return mapping.getInputForward();
			}
			// we save the player in both db and session,
			// and we forward to the home page
			playerDAO.save(player);
			request.getSession().setAttribute("PLAYER", player);
			return mapping.findForward("Home Page");

		} catch (Exception e) {
			// if an error occurred, we redirect to the form
			ActionErrors errors = new ActionErrors();
			errors.add("registrationFailed", new ActionMessage(
					"errors.exception.occurred"));
			saveErrors(request, errors);

			return mapping.getInputForward();
		}

	}

}
