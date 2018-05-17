package music.ontology.search;

public class MusicService implements IMusicService {
	
	@Override
	public String search(String term) {
		try {
			MusicSearcher ms = new MusicSearcher();
			return ms.searchTerm(term);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
