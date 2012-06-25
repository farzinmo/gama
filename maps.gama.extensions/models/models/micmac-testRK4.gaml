/**
 *  micmactestRK4
 *  Author: bgaudou
 *  Description: 
 */

model micmactestRK4

global {

	float alpha <- 0.2 min: 0.0 max: 1.0;
	float beta <- 0.8 min: 0.0 max: 1.0;
	float gamma <- 0.0 min: 0.0 max: 1.0;
	float b <- 0.0 min: 0.0 max: 1.0;
	float d1 <- 0.0 min: 0.0 max: 1.0;
	float d2 <- 0.0 min: 0.0 max: 1.0;
	int N <- 1500 min: 1 max: 3000;
	float hKR4 <- 0.01;
	int iInit <- 1;

	init {
		create node number: 1;
	}

}

entities {
	species node skills: [SIR]{
		float I <- float(iInit); 
		float S <- N - I; 
		float R <- 0.0; 
	
		reflex go {
			let temp type: list of: float <- list(self RK4SIRS [S::S, I::I, R::R,alpha::alpha, beta::beta, gamma::gamma,b::b, d1::d1, d2::d2, n::N, h::hKR4]); 
		  set S value: (temp at 0);
		  set I value: (temp at 1);
		  set R value: (temp at 2);
		}		
	}
}

experiment testRK4 type: gui {
	parameter 'Alpha:' var: alpha category: 'SIR' ;
	parameter 'Beta:' var: beta category: 'SIR' ;	
	parameter 'Gamma:' var: gamma category: 'SIR' ;	
	
	parameter 'Population par noeud:' var: N category: 'Population'; 
	
	output {
		display SIR refresh_every: 1 {
			chart "SIR" type: series background: rgb('white') {
				data S value: first(list(node)).S color: rgb('green') ;				
				data I value: first(list(node)).I color: rgb('red') ;
				data R value: first(list(node)).R color: rgb('blue') ;
			}
		}
	}
}