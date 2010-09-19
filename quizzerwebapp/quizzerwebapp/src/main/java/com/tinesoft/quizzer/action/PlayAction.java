package com.tinesoft.quizzer.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.tinesoft.quizzer.model.dao.CategoryDAO;
import com.tinesoft.quizzer.model.dao.GameResultDAO;
import com.tinesoft.quizzer.model.dao.PlayerDAO;
import com.tinesoft.quizzer.model.domain.Category;
import com.tinesoft.quizzer.model.domain.GameResult;
import com.tinesoft.quizzer.model.domain.Player;
import com.tinesoft.quizzer.model.domain.Question;
import com.tinesoft.quizzer.util.Utils;

/**
 * Checks that the player's answers are correct and saves his result( for
 * registered players only).
 * 
 * @author kondo tine
 * 
 */
public class PlayAction extends Action {
	private CategoryDAO categoryDAO = null;
	private GameResultDAO gameResultDAO = null;
	private PlayerDAO playerDAO = null;

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public GameResultDAO getGameResultDAO() {
		return gameResultDAO;
	}

	public void setGameResultDAO(GameResultDAO gameResultDAO) {
		this.gameResultDAO = gameResultDAO;
	}

	public PlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public void setPlayerDAO(PlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm playForm = (DynaActionForm) form;
		Integer idCategory = (Integer) playForm.get("idCategory");
		String answers[] = (String[]) playForm.get("answers");

		HttpSession session = request.getSession();

		Player player = (Player) session.getAttribute("PLAYER");
		Category category = categoryDAO.get(idCategory);
		List<Question> questions = (ArrayList<Question>) session
				.getAttribute("QUESTIONS");

		ArrayList<String> corrections = new ArrayList<String>();

		int score = 0;

		for (int i = 0; i < questions.size(); i++) {
			if (!answers[i].trim().equals("")
					&& questions.get(i).getAnswer()
							.equalsIgnoreCase(answers[i].toLowerCase())) {
				score++;
				corrections.add("question.answer.good");
			} else
				corrections.add("question.answer.wrong");
		}

		if (player != null) {
			GameResult result = new GameResult(score, category, player);
			result.setPlayedDate(Utils.todayTimestamp());
			gameResultDAO.save(result);
			playerDAO.saveOrUpdate(player);
			// we update the player in the session
			session.setAttribute("PLAYER", player);
		}

		request.setAttribute("SCORE", score);
		request.setAttribute("CORRECTIONS", corrections);
		request.setAttribute("QUESTIONS", questions);
		request.setAttribute("ANSWERS", answers);
		request.setAttribute("ID_CATEGORY", idCategory);

		return mapping.findForward("GameOver Page");

	}
}
