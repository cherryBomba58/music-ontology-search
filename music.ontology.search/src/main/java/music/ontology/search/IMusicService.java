package music.ontology.search;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("music")
public interface IMusicService {
	@GET
	@Path("search")
	public String search(@QueryParam("term") String term);
}
