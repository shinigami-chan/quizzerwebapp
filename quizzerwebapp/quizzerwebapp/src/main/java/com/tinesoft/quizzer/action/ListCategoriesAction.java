package com.tinesoft.quizzer.action;

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

/**
 * Lists all categories, then forward to a "Categories Page".
 * 
 * @author kondo tine
 * 
 */
public class ListCategoriesAction extends Action {
	private CategoryDAO categoryDAO = new CategoryDAOHib();

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<Category> categories;

		categories = categoryDAO.loadAll();
		request.setAttribute("CATEGORIES", categories);
		return mapping.findForward("Categories Page");

	}

}
