package com.ivanpanussis.supermarket.dto.branch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchUpdateDTO {
	private String name;
	private String address;
}
