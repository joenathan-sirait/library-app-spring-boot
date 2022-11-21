package net.springBootApp.apiLibrary.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {
  private String title;
  private String author;
  private String categoryName;
}