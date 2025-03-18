package com.nerchuko.quizapp.Service;

import com.nerchuko.quizapp.Model.Question;
import com.nerchuko.quizapp.Model.QuestionWrapper;
import com.nerchuko.quizapp.Model.Quiz;
import com.nerchuko.quizapp.Model.Response;
import com.nerchuko.quizapp.Persistance.QuestionDao;
import com.nerchuko.quizapp.Persistance.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<String> createQuiz(String category, int noOfQuestions, String title) {
        List<Question> questionsFromDb= questionDao.findRandomQuestionsByCategory(category,noOfQuestions);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionsFromDb);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id)
    {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser=new ArrayList<>();

        for(Question q: questionsFromDb)
        {
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getCategory(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4(),q.getQuestionTitle());
            questionsForUser.add(qw);

        }

        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);



    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        Quiz quiz = quizDao.findById(id).get();
        List<Question> question = quiz.getQuestions();
        int result=0;
        int i=-1;
        for(Response r:responses)
        {
            i++;
            if(r.getResponse().equals(question.get(i).getRightAnswer()))
            {
                result++;
            }
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
