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

	
	private Condition2 c1;

	private Condition(Condition2 c1){
		this.c1=c1;
	}
	
}

