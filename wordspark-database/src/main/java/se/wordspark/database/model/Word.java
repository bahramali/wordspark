package se.wordspark.database.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("WORD")
public class Word {

  @Id
  @Column(name = "ID")
  @JsonIgnore
  private Long id;

  @Column(name = "TERM")
  private String term;

  @Column(name = "FREQ")
  private Integer freq;

}
