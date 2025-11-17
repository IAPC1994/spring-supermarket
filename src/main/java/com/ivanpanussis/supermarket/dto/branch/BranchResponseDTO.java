package com.ivanpanussis.supermarket.dto.branch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchResponseDTO {
	private Long id;
	private String name;
	private String address;
}
