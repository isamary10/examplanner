package br.com.fiap.examplanner.exam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

  @Autowired
  ExamRepository repository;

  public List<Exam> findAll(){
    return repository.findAll();
  }

  public boolean delete(Long id){
    var exam = repository.findById(id);

    if(exam.isEmpty()) return false;

    repository.deleteById(id);
    return true;
  }

  public void save(Exam exam){
    repository.save(exam);
  }
}
