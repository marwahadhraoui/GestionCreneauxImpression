package com.plateformeDev.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationWithExamenDTO { 
	
	 private Long id;
	    private String typeImpression;
	    private int nbrPages;
	    
	    private Long examenId;

}
