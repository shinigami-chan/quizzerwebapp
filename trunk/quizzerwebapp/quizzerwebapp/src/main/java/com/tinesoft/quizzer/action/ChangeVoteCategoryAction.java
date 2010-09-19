package com.tinesoft.quizzer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.tinesoft.quizzer.model.dao.CategoryDAO;
import com.tinesoft.quizzer.model.dao.CategoryVoteDAO;
import com.tinesoft.quizzer.model.dao.PlayerDAO;
import com.tinesoft.quizzer.model.domain.Category;
import com.tinesoft.quizzer.model.domain.CategoryVote;
import com.tinesoft.quizzer.model.domain.Player;

/**
 * Action that performs the player'svote for a category.
 * 
 * @author kondo tine
 * 
 */
public class ChangeVoteCategoryAction extends Action {
	private PlayerDAO playerDAO = null;
	private CategoryDAO categoryDAO = null;
	private CategoryVoteDAO categoryVoteDAO = null;

	public PlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public void setPlayerDAO(PlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public CategoryVoteDAO getCategoryVoteDAO() {
		return categoryVoteDAO;
	}

	public void setCategoryVoteDAO(CategoryVoteDAO categoryVoteDAO) {
		this.categoryVoteDAO = categoryVoteDAO;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm voteForm = (DynaActionForm) form;
		Boolean isCon = (Boolean) voteForm.get("isCon");
		Integer idCategory = (Integer) voteForm.get("idCategory");
		HttpSession session = request.getSession();

		Player player = (Player) session.getAttribute("PLAYER");
		Category category = categoryDAO.get(idCategory);
		CategoryVote vote = player.getCategoryVote(category);

		if (vote == null) { // first vote of the player for this category
			vote = new CategoryVote(isCon, category);
			player.addCategoryVote(vote);
		} else {
			// we update the previous vote
			vote.setCon(isCon);
			category = vote.getCategory();
		}

		// we save the player, the category and the vote
		categoryVoteDAO.saveOrUpdate(vote);
		categoryDAO.saveOrUpdate(category);
		playerDAO.saveOrUpdate(player);

		// we update the category and the player in the session/request
		session.setAttribute("PLAYER", player);
		request.setAttribute("CATEGORY", category);
		request.setAttribute("MY_VOTE_IS_CON", vote.isCon());

		return mapping.findForward("Category Page");
	}

}
