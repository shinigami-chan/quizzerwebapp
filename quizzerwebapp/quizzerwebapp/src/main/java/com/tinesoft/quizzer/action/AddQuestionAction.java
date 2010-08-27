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

import com.tinesoft.quizzer.model.dao.CategoryDAO;
import com.tinesoft.quizzer.model.dao.QuestionDAO;
import com.tinesoft.quizzer.model.dao.QuestionUpdateDAO;
import com.tinesoft.quizzer.model.dao.hibernate.CategoryDAOHib;
import com.tinesoft.quizzer.model.dao.hibernate.QuestionDAOHib;
import com.tinesoft.quizzer.model.dao.hibernate.QuestionUpdateDAOHib;
import com.tinesoft.quizzer.model.domain.Category;
import com.tinesoft.quizzer.model.domain.Question;
import com.tinesoft.quizzer.model.domain.QuestionUpdate;
import com.tinesoft.quizzer.model.domain.Player;

/**
 * Adds a question in a category.
 * 
 * @author kondo tine
 * 
 */
public class AddQuestionAction extends Action {
	private QuestionDAO questionDAO = new QuestionDAOHib();
	private QuestionUpdateDAO questionUpdateDAO = new QuestionUpdateDAOHib();
	private CategoryDAO categoryDAO = new CategoryDAOHib();

	public QuestionDAO getQuestionDAO() {
		return questionDAO;
	}

	public void setQuestionDAO(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}

	public QuestionUpdateDAO getQuestionUpdateDAO() {
		return questionUpdateDAO;
	}

	public void setQuestionUpdateDAO(QuestionUpdateDAO questionUpdateDAO) {
		this.questionUpdateDAO = questionUpdateDAO;
	}

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm questionForm = (DynaValidatorForm) form;
		int idCategory = (Integer) questionForm.get("idCategory");
		String text = questionForm.getString("question");
		String answer = questionForm.getString("answer");

		Player player = (Player) request.getSession().getAttribute("PLAYER");

		// if no player is logged in, we stop and forward to home page
		if (player == null)
			return mapping.findForward("Home Page");

		try {
			Category category = categoryDAO.get(idCategory);

			if (player.isAdmin()) { // if the player is an admin, the question
									// is saved in validated ones

				Question question = new Question(text, answer, category);
				questionDAO.save(question);
				categoryDAO.saveOrUpdate(category);
				return mapping.findForward("Success Page");
			} else { // otherwise, it's saved in the non validated questions(
						// updates)
				QuestionUpdate update = new QuestionUpdate(text, answer,
						category, null);
				questionUpdateDAO.save(update);
				return mapping.findForward("Success Page");

			}

		} catch (Exception e) {
			// if an error occurred, we redirect to the form
			ActionErrors errors = new ActionErrors();
			errors.add("addQuestionFailed", new ActionMessage(
					"errors.exception.occurred"));
			saveErrors(request, errors);

			return mapping.getInputForward();
		}

	}
}
