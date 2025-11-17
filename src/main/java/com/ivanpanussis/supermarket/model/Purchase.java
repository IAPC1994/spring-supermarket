package com.ivanpanussis.supermarket.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private LocalDate date;
	private String status;
	private Double total;
	
	@ManyToOne
	private Branch branch;
	
	@OneToMany(mappedBy="purchase", cascade = CascadeType.ALL,
			orphanRemoval = true, fetch = FetchType.EAGER)
	private List<PurchaseDetail> detail = new ArrayList<>();
}
