package lee.joohan.dto;

import lombok.Getter;

/**
 * Created by Joohan Lee on 2019/11/13
 */

public class CreateCustomerRequest {
  public CreateCustomerRequest(String firstName, String lastName, Integer age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  private String firstName;
  private String lastName;
  private Integer age;

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Integer getAge() {
    return age;
  }
}
