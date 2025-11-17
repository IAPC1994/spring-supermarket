package com.ivanpanussis.supermarket.dto.branch;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchCreateDTO {
	@NotBlank(message = "Branch name is required")
	private String name;
	
	@NotBlank(message = "Branch address is required")
	private String address;
}
