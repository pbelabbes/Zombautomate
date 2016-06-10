

package Model;

public enum Condition2 {

	PRESENCE(Presence),
	ET(Et),
	//OU,
	CASEALLIEE(CaseAlliee),
	CASEENNEMIE(CaseEnnemie),
	CASENEUTRE(CaseNeutre),

	SCANLOIN(ScanLoin),
	SCANPROCHE(ScanProche);	


	
	private Condition c1;

	private Condition2(Condition c1){
		this.c1=c1;

	}
	
}

