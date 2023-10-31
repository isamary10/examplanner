package br.com.fiap.examplanner.exam;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Exam {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotBlank
  String title;

  @NotBlank
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  LocalDate examDate;

  @Size(min = 10, message = "{exam.description.size}")
  String description;

  @Min(1) @Max(5)
  Integer level;

  @Min(1) @Max(10)
  Integer score;

}
