package Model;

public enum Condition {

	PRESENCE(Presence),
	ET(Et),
	//OU,
	CASEALLIEE(CaseAlliee),
	CASEENNEMIE(CaseEnnemie),
	CASENEUTRE(CaseNeutre),

	SCANLOIN(ScanLoin),
	SCANPROCHE(ScanProche);	

	
	Condition c1;
	Condition c2;
	
	private Condition(){
		
	}
	private Condition(Condition2 c1){
		this.c1=c1;
	}
	
}

