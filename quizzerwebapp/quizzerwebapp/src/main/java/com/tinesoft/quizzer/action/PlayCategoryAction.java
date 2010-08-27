package com.tinesoft.quizzer.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tinesoft.quizzer.model.dao.CategoryDAO;
import com.tinesoft.quizzer.model.dao.hibernate.CategoryDAOHib;
import com.tinesoft.quizzer.model.domain.Category;
import com.tinesoft.quizzer.model.domain.Question;

/**
 * Fetches the category, randomly picks an amount of questions in it,puts them
 * in the request(in session for questions), and then forward to the
 * "Play Page".
 * 
 * @author kondo tine
 * 
 */
public class PlayCategoryAction extends Action {
	private CategoryDAO categoryDAO = new CategoryDAOHib();
	private int nbQuestionsByCategory = 15;

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public int getNbQuestionsByCategory() {
		return nbQuestionsByCategory;
	}

	public void setNbQuestionsByCategory(int nbQuestionsByCategory) {
		this.nbQuestionsByCategory = nbQuestionsByCategory;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int idCategory = Integer.parseInt(request.getParameter("ID"));
		Category category = categoryDAO.get(idCategory);

		// we fetch the category's questions, then we shuffle them
		// to introduce some randomization in the game
		List<Question> allQuestions = new ArrayList<Question>(
				category.getQuestions());

		Collections.shuffle(allQuestions);

		List<Question> questions = new ArrayList<Question>();
		// we only pick a certain number of questions (15 by default)
		for (int i = 0; i < allQuestions.size() && i < nbQuestionsByCategory; i++)
			questions.add(allQuestions.get(i));

		// we put the category and the chosen questions in the request/session
		request.setAttribute("CATEGORY", category);
		request.getSession().setAttribute("QUESTIONS", questions);

		return mapping.findForward("Category Page");
	}
}
