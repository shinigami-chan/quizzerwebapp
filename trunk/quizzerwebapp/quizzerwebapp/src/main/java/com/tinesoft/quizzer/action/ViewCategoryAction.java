package com.tinesoft.quizzer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tinesoft.quizzer.model.dao.CategoryDAO;
import com.tinesoft.quizzer.model.dao.hibernate.CategoryDAOHib;
import com.tinesoft.quizzer.model.domain.Category;
import com.tinesoft.quizzer.model.domain.CategoryVote;
import com.tinesoft.quizzer.model.domain.Player;

/**
 * Fetch the category, then forward to a "Category Page".
 * 
 * @author kondo tine
 * 
 */
public class ViewCategoryAction extends Action {
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
		int idCategory = Integer.parseInt(request.getParameter("ID"));
		Category category = categoryDAO.get(idCategory);

		Player player = (Player) request.getSession().getAttribute("PLAYER");

		if (player != null) {
			// we fetch the previous vote (if any), and save it in the request
			CategoryVote vote = player.getCategoryVote(category);

			if (vote != null)
				request.setAttribute("MY_VOTE_IS_CON", vote.isCon());
		}

		request.setAttribute("CATEGORY", category);
		return mapping.findForward("Category Page");

	}

}
