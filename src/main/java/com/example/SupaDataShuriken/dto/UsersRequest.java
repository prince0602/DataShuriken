package com.example.SupaDataShuriken.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
public class UsersRequest {
	
	@NotBlank(message = "Email is required")
	private String fullName;
	@NotBlank(message = "Email is required")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE, message = "Invalid email format")
    private String email;
	@NotBlank(message = "Contact Number is required")
    private String contactNumber;
	@NotBlank(message = "Company Id is required")
    private String companyId;

}
