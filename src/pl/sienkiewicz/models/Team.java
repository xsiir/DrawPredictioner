package pl.sienkiewicz.models;

public class Team {

	private String name;
	private Fixtures fixtures;
	
	
	public Team(String name) {
		this.name = name;
		this.fixtures = new Fixtures();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Fixtures getFixtures() {
		return fixtures;
	}
	public void setFixtures(Fixtures fixtures) {
		this.fixtures = fixtures;
	}
	
	public void addNewFixture(String type, GameStats stats) {
		this.fixtures.putStats(type, stats);
	}
	
	

}
