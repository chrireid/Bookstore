/**
 * 
 */
package g4w14.BookStore.actionbeans;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import g4w14.BookStore.beans.GenreBean;
import g4w14.BookStore.beans.SurveyAnswerBean;
import g4w14.BookStore.beans.SurveyBean;
import g4w14.BookStore.util.LoadProperties;

/**
 * Class responsible for managing Surveys DAO functions
 * @author Chris
 */

@Named
@SessionScoped
public class SurveyActionBean implements Serializable {

	private static final long serialVersionUID = -7470223548556650314L;
	private final Logger log = LoggerFactory.getLogger(this.getClass()
			.getName());

	private long selected;
	
	@Inject
	SessionTrackingBean stb;
	
	@Inject
	NavigationBean navBean;

	// See the context.xml for the datasource
	@Resource(name = "jdbc/genres")
	private DataSource ds;

	/**
	 * Constructor
	 * 
	 * @throws IOException
	 * @throws NullPointerException
	 * 
	 * @author Chris
	 * 
	 */
	public SurveyActionBean() throws NullPointerException, IOException {
		super();
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/genres");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		log.debug("SurveyActionBean created");
	}

	/**
	 * Inserts a survey bean into the db
	 * @author Chris
	 */
	public int insert(SurveyBean sb) throws SQLException {
		int records = 0;
		long surveyId = -1;

		if (sb == null) {
			return 0;
		}

		String sql = "INSERT INTO survey_questions (question) VALUES (?)";

		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = ds.getConnection();
				PreparedStatement insertPS = connection.prepareStatement(sql)) {

			// Set the values of PreparedStatement from the SurveyBean object
			insertPS.setString(1, sb.getQuestion());

			records = insertPS.executeUpdate();

			if (records != 1) {
				surveyId = -1;
			}
			
			// Nitty gritty ghetto way of getting the actual question_id
			ResultSet rs = null;
			// returns id of last book inserted
			rs = insertPS.executeQuery("SELECT last_insert_id()");
			rs.next();
			surveyId = rs.getLong(1);

			// Assign correct ID to survey bean
			sb.setId(surveyId);

			/*
			 * Now that the survey question is inserted into SQL, the answers
			 * must also be inserted with the corresponding question_id.
			 */

			sql = "INSERT INTO survey_answers (question_id, answer, votes) VALUES (?,?,?)";
			try (PreparedStatement insertAnswersPS = connection.prepareStatement(sql)) {
				
				for (SurveyAnswerBean sab : sb.getAnswers()) {
					insertAnswersPS.setLong(1, sb.getId());
					insertAnswersPS.setString(2, sab.getAnswer());
					insertAnswersPS.setInt(3, sab.getVotes());
					
					records = insertAnswersPS.executeUpdate();
				}
				
			} catch (SQLException sqlex) {

				// Log the exception
				log.error("insert answer failed", sqlex);

				// Re-throw the exception
				throw sqlex;
			}

		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		}
		
		// Return result
		return records;
	}
	
	/**
	 * Updates a survey bean in db
	 * 
	 * @author Chris
	 * @throws SQLException 
	 */
	public int update(SurveyBean sb) throws SQLException {
		
		int records = 0;

		if (sb == null) {
			return 0;
		}
		
		String sql = "UPDATE survey_questions SET question = ? WHERE _id = ?";

		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = ds.getConnection();
				PreparedStatement questionPS = connection.prepareStatement(sql)) {

			// Set the values of PreparedStatement from the SurveyBean object
			questionPS.setString(1, sb.getQuestion());
			questionPS.setLong(2, sb.getId());

			records = questionPS.executeUpdate();

			sql = "DELETE FROM survey_answers where question_id = ?";
			try (PreparedStatement deleteAnswersPS = connection.prepareStatement(sql)) {
				
				deleteAnswersPS.setLong(1, sb.getId());
				records = deleteAnswersPS.executeUpdate();
				
				
			} catch (SQLException sqlex) {

				// Log the exception
				log.error("delete failed", sqlex);

				// Re-throw the exception
				throw sqlex;
			}
			
			sql = "INSERT INTO survey_answers (question_id, answer, votes) VALUES (?,?,?)";
			try (PreparedStatement insertAnswersPS = connection.prepareStatement(sql)) {
				
				for (SurveyAnswerBean sab : sb.getAnswers()) {
					insertAnswersPS.setLong(1, sb.getId());
					insertAnswersPS.setString(2, sab.getAnswer());
					insertAnswersPS.setInt(3, sab.getVotes());
					
					records = insertAnswersPS.executeUpdate();
				}
				
			} catch (SQLException sqlex) {

				// Log the exception
				log.error("insert answer failed", sqlex);

				// Re-throw the exception
				throw sqlex;
			}

		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		} 

		// Return result
		return records;
		
	}

	/**
	 * Deletes a survey from db
	 * 
	 * @throws SQLException
	 * @author Chris
	 */
	public int remove(SurveyBean sb) throws SQLException {

		int records = 0;

		if (sb == null) {
			return 0;
		}
		
		String sql = "DELETE FROM survey_answers WHERE question_id = ?";

		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = ds.getConnection();
				PreparedStatement deleteAnswersPS = connection.prepareStatement(sql)) {

			// Set the values of PreparedStatement from the SurveyBean object
			deleteAnswersPS.setLong(1, sb.getId());

			records = deleteAnswersPS.executeUpdate();

			sql = "DELETE FROM survey_questions where _id = ?";
			try (PreparedStatement deleteQuestionPS = connection.prepareStatement(sql)) {
				
				deleteQuestionPS.setLong(1, sb.getId());
				records = deleteQuestionPS.executeUpdate();
				
				
			} catch (SQLException sqlex) {

				// Log the exception
				log.error("delete failed", sqlex);

				// Re-throw the exception
				throw sqlex;
			}

		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		} 

		// Return result
		return records;
		
	}

	/**
	 * Checks if there is an active survey, returns result.
	 * 
	 * @return
	 * @throws SQLException
	 * @author Chris
	 */
	public boolean checkActiveSurvey() throws SQLException {
		
		String preparedSQL = "SELECT * FROM survey_questions WHERE active = true ORDER BY _id DESC LIMIT 1 ";
		
		// Using Java 1.7 try with resources
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(preparedSQL);
				ResultSet resultSet = ps.executeQuery()) {

			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		}
	}
	
	/**
	 * Retrieves the most recently added survey that is marked as active in the
	 * database
	 * 
	 * @return
	 * @throws SQLException
	 * @author Chris
	 */
	public SurveyBean getActiveSurvey() throws SQLException {
		SurveyBean sb = new SurveyBean();
		String preparedSQL = "SELECT * FROM survey_questions WHERE active = true ORDER BY _id DESC LIMIT 1 ";

		// Using Java 1.7 try with resources
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(preparedSQL);
				ResultSet resultSet = ps.executeQuery()) {

			while (resultSet.next()) {

				sb.setId(resultSet.getLong("_id"));
				sb.setQuestion(resultSet.getString("question"));
				sb.setActive(resultSet.getBoolean("active"));

				ArrayList<SurveyAnswerBean> answers = new ArrayList<SurveyAnswerBean>();

				// Get all the answers for a given survey
				try (PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM survey_answers WHERE question_id = ?");) {

					statement.setLong(1, sb.getId());

					try (ResultSet answersRS = statement.executeQuery()) {
						while (answersRS.next()) {
							SurveyAnswerBean sab = new SurveyAnswerBean();
							sab.setId(answersRS.getLong("_id"));
							sab.setQuestionId(answersRS.getLong("question_id"));
							sab.setAnswer(answersRS.getString("answer"));
							sab.setVotes(answersRS.getInt("votes"));

							answers.add(sab);
						}

					}

					sb.setAnswers(answers);
				}

			}
		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		}

		return sb;

	}

	public long getSelected() {
		return selected;
	}

	public void setSelected(long selected) {
		this.selected = selected;
	}

	/**
	 * Returns all surveys from db
	 * 
	 * @throws SQLException
	 * @author Chris
	 */
	public ArrayList<SurveyBean> getAllSurveys() throws SQLException {
		ArrayList<SurveyBean> surveys = new ArrayList<>();
		String preparedSQL = "SELECT * FROM survey_questions ORDER BY _id DESC";

		// Using Java 1.7 try with resources
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(preparedSQL);
				ResultSet resultSet = ps.executeQuery()) {

			while (resultSet.next()) {

				SurveyBean sb = new SurveyBean();
				sb.setId(resultSet.getLong("_id"));
				sb.setQuestion(resultSet.getString("question"));
				sb.setActive(resultSet.getBoolean("active"));

				ArrayList<SurveyAnswerBean> answers = new ArrayList<SurveyAnswerBean>();
				preparedSQL = "SELECT * FROM survey_answers WHERE question_id = ?";
				try (Connection connectionQuestions = ds.getConnection();
						PreparedStatement statement = connection
								.prepareStatement(preparedSQL);) {
					// the normal id for the question is the question id in the
					// answer
					statement.setLong(1, sb.getId());

					try (ResultSet answersRS = statement.executeQuery()) {
						while (answersRS.next()) {
							SurveyAnswerBean sab = new SurveyAnswerBean();
							sab.setId(answersRS.getLong("_id"));
							sab.setQuestionId(answersRS.getLong("question_id"));
							sab.setAnswer(answersRS.getString("answer"));
							sab.setVotes(answersRS.getInt("votes"));

							answers.add(sab);
						}
					}

				}

				sb.setAnswers(answers);
				surveys.add(sb);
			}

		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		}

		log.debug("Total number of genres [getAllSurveys]=" + surveys.size());
		return surveys;
	}
	
	/**
	 * Makes the selected survey bean the only active survey bean in the db
	 * 
	 * @author Chris
	 * @throws SQLException 
	 */
	public void makeActive(SurveyBean sb) throws SQLException {
		String sql = "UPDATE survey_questions SET active = false";

		// Using Java 1.7 try with resources
		try (Connection connection = ds.getConnection();
				PreparedStatement inactiveSB = connection.prepareStatement(sql)) {

			inactiveSB.executeUpdate();
			
			sql = "UPDATE survey_questions SET active = true WHERE _id = ?";
			
			// Using Java 1.7 try with resources
			try (PreparedStatement activeSB = connection.prepareStatement(sql)) {
				activeSB.setLong(1, sb.getId());

				activeSB.executeUpdate();
				
			} catch (SQLException sqlex) {

				// Log the exception
				log.error("JDBC Connection failed", sqlex);

				// Re-throw the exception
				throw sqlex;
			}

		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		}
	}
	
	/**
	 * Makes the selected survey bean inactive in the db
	 * 
	 * @author Chrhis
	 * @throws SQLException 
	 */
	public void makeInactive(SurveyBean sb) throws SQLException {
		String sql = "UPDATE survey_questions SET active = false WHERE _id = ?";

		// Using Java 1.7 try with resources
		try (Connection connection = ds.getConnection();
				PreparedStatement surveySB = connection.prepareStatement(sql)) {
			
			surveySB.setLong(1, sb.getId());

			surveySB.executeUpdate();

		} catch (SQLException sqlex) {

			// Log the exception
			log.error("JDBC Connection failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		}
	}
	
	public void vote() throws SQLException {
		
		System.out.println("getting to vote()");
		
		updateResults();
		stb.setVoted(true);
		
		//refresh page
//		return navBean.toHome();
	}
	
	/*
	 * Increments vote for selected answer
	 * 
	 * @author Chris
	 */
	private void updateResults() throws SQLException {
		
		int votes = 0;
		
		String sql = "SELECT * FROM survey_answers WHERE _id = ?";
		// Using Java 1.7 try with resources to create JDBC connection
		try (Connection connection = ds.getConnection();
				PreparedStatement selectPS = connection.prepareStatement(sql)) {
			
			selectPS.setLong(1, selected);
			ResultSet resultSet = selectPS.executeQuery();
			
			if (resultSet.next()) {
				votes = resultSet.getInt("votes");
				
				sql = "UPDATE survey_answers SET votes = ? WHERE _id = ?";
				try (PreparedStatement updatePS = connection.prepareStatement(sql)) {
					
					updatePS.setInt(1, votes + 1);
					updatePS.setLong(2, selected);
					updatePS.executeUpdate();
					
				} catch (SQLException sqlex) {

					// Log the exception
					log.error("update failed", sqlex);

					// Re-throw the exception
					throw sqlex;
				}
			}
			
		}  catch (SQLException sqlex) {

			// Log the exception
			log.error("select failed", sqlex);

			// Re-throw the exception
			throw sqlex;
		}
	}

}
