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
import com.tinesoft.quizzer.model.dao.CategoryUpdateDAO;
import com.tinesoft.quizzer.model.dao.hibernate.CategoryDAOHib;
import com.tinesoft.quizzer.model.dao.hibernate.CategoryUpdateDAOHib;
import com.tinesoft.quizzer.model.domain.Category;
import com.tinesoft.quizzer.model.domain.CategoryUpdate;
import com.tinesoft.quizzer.model.domain.Player;

/**
 * Proposes a new category.
 * 
 * @author kondo tine
 * 
 */
public class AddCategoryAction extends Action {
	private CategoryDAO categoryDAO = new CategoryDAOHib();
	private CategoryUpdateDAO categoryUpdateDAO = new CategoryUpdateDAOHib();

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public CategoryUpdateDAO getCategoryUpdateDAO() {
		return categoryUpdateDAO;
	}

	public void setCategoryUpdateDAO(CategoryUpdateDAO categoryUpdateDAO) {
		this.categoryUpdateDAO = categoryUpdateDAO;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm categoryForm = (DynaValidatorForm) form;
		String title = categoryForm.getString("title");

		Player player = (Player) request.getSession().getAttribute("PLAYER");

		if (player == null)
			return mapping.findForward("Home Page");

		try {
			if (player.isAdmin()) { // if the player is an admin, the category
									// is saved in validated categories
				Category category = new Category(title);
				categoryDAO.save(category);
				return mapping.findForward("Success Page");
			} else { // otherwise, it's saved in the non validated categories(
						// updates)
				CategoryUpdate update = new CategoryUpdate(title, null);
				categoryUpdateDAO.save(update);
				return mapping.findForward("Success Page");

			}

		} catch (Exception e) {
			// if an error occurred, we redirect to the form
			ActionErrors errors = new ActionErrors();
			errors.add("addCategoryFailed", new ActionMessage(
					"errors.exception.occurred"));
			saveErrors(request, errors);

			return mapping.getInputForward();
		}

	}

}
