package net.springBootApp.apiLibrary.model.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {
  private Integer status;
  private String message;
  private T data;
}
