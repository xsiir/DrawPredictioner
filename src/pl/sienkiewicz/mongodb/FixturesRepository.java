package pl.sienkiewicz.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import pl.sienkiewicz.APIModels.Matches;

@Repository
public class FixturesRepository {

	private MongoOperations mongoOperations;

	@Autowired
	private StandingsRepository repo;

	@Autowired
	public FixturesRepository(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	public void saveFixture(Matches match) {
		mongoOperations.save(match);
	}

	public List<Matches> getAllMatches() {
		return mongoOperations.findAll(Matches.class);
	}

	public void dropFixturesCollections() {
		mongoOperations.dropCollection(Matches.class);
	}

}
