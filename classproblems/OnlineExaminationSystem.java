import java.util.*;

abstract class Question {

    protected int questionNumber;
    protected String questionText;
    protected int marks;

    public Question(
        int questionNumber,
        String questionText,
        int marks
    ) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.marks = marks;
    }

    public abstract int evaluate(String answer);

    public int getQuestionNumber() {
        return questionNumber;
    }

    public int getMarks() {
        return marks;
    }
}

class MultipleChoiceQuestion extends Question {

    private String correctAnswer;

    public MultipleChoiceQuestion(
        int questionNumber,
        String questionText,
        String correctAnswer,
        int marks
    ) {
        super(questionNumber, questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    public int evaluate(String answer) {

        if (answer.equalsIgnoreCase(correctAnswer)) {
            return marks;
        }

        return 0;
    }
}

class TrueFalseQuestion extends Question {

    private boolean correctAnswer;

    public TrueFalseQuestion(
        int questionNumber,
        String questionText,
        boolean correctAnswer,
        int marks
    ) {
        super(questionNumber, questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    public int evaluate(String answer) {

        boolean userAnswer =
            Boolean.parseBoolean(answer);

        if (userAnswer == correctAnswer) {
            return marks;
        }

        return 0;
    }
}

class ShortAnswerQuestion extends Question {

    private String correctAnswer;

    public ShortAnswerQuestion(
        int questionNumber,
        String questionText,
        String correctAnswer,
        int marks
    ) {
        super(questionNumber, questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    public int evaluate(String answer) {

        if (answer.equalsIgnoreCase(correctAnswer)) {
            return marks;
        }

        return 0;
    }
}

class Student {

    private int studentId;
    private String name;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Examination {

    private String examName;
    private ArrayList<Question> questions;

    public Examination(String examName) {
        this.examName = examName;
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public String getExamName() {
        return examName;
    }

    public int getTotalMarks() {

        int total = 0;

        for (Question q : questions) {
            total += q.getMarks();
        }

        return total;
    }
}

class Attempt {

    private Student student;
    private Examination examination;

    private HashMap<Integer, String> answers;

    private boolean submitted;

    public Attempt(
        Student student,
        Examination examination
    ) {
        this.student = student;
        this.examination = examination;
        this.answers = new HashMap<>();
        this.submitted = false;
    }

    public void recordAnswer(
        int questionNumber,
        String answer
    ) {

        if (submitted) {
            System.out.println(
                "Cannot change answers for a submitted examination."
            );
            return;
        }

        answers.put(questionNumber, answer);

        System.out.println(
            "Answer recorded for Question " +
            questionNumber + "."
        );
    }

    public void submit() {

        if (submitted) {
            System.out.println("Examination already submitted.");
            return;
        }

        submitted = true;

        System.out.println(
            examination.getExamName() +
            " submitted by " +
            student.getName() +
            "."
        );

        calculateResult();
    }

    private void calculateResult() {

        int totalScore = 0;

        for (Question question :
             examination.getQuestions()) {

            String answer =
                answers.get(question.getQuestionNumber());

            int score = 0;

            if (answer != null) {
                score = question.evaluate(answer);
            }

            totalScore += score;

            if (score > 0) {
                System.out.println(
                    "Question " +
                    question.getQuestionNumber() +
                    ": Correct (" +
                    score +
                    " points)"
                );
            } else {
                System.out.println(
                    "Question " +
                    question.getQuestionNumber() +
                    ": Incorrect (0 points)"
                );
            }
        }

        System.out.println(
            "Total score: " +
            totalScore +
            "/" +
            examination.getTotalMarks()
        );
    }
}

public class OnlineExaminationSystem {

    public static void main(String[] args) {

        Student student =
            new Student(1, "Student 1");

        Examination exam =
            new Examination("Exam A");

        Question q1 =
            new MultipleChoiceQuestion(
                1,
                "Which is a programming language?",
                "C",
                5
            );

        Question q2 =
            new TrueFalseQuestion(
                2,
                "Java is object-oriented.",
                false,
                5
            );

        exam.addQuestion(q1);
        exam.addQuestion(q2);

        Attempt attempt =
            new Attempt(student, exam);

        System.out.println(
            "Exam A started by Student 1."
        );

       
        attempt.recordAnswer(1, "C");

        attempt.recordAnswer(2, "true");

        
        attempt.submit();

        attempt.recordAnswer(1, "B");
    }
}