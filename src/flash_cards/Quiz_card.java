package flash_cards;

public class Quiz_card {

	// fields/attributes
	private String question;
	private String answer;

	// constructor method
	public Quiz_card(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	// question getter
	public String getQuestion() {
		return question;
	}

	// question setter
	public void setQuestion(String question) {
		this.question = question;
	}

	// asnwer getter
	public String getAnswer() {
		return answer;
	}

	// answer setter
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	
}
