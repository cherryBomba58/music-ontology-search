# Music Ontology Search
Music Ontology Search is a RESTful Java EE web application, which provides browsing the individuals in an ontology called [Music Ontology](http://musicontology.com/), with some little modifications and examples of bands, albums, tracks etc.

## The Repository
The music.ontology.search is a Maven project, which was created with Eclipse Java EE Oxygen IDE.

Under the documentation directory, you can find the Hungarian documentation of the project.

## The Ontology
I used [Music Ontology](http://musicontology.com/), its original RDFS source can be found at the GitHub repository of it by motools, [here](https://github.com/motools/musicontology/blob/master/rdf/musicontology.rdfs).

I downloaded it, and modified a bit to this project: I removed some of the dependencies, which were problematic by the loading of the ontology in Java, because one of the dependencies was unreachable, and two more of the dependencies couldn't be parsed (more details in the documentation). Additionally, I added one more object property named track_of to the ontology, which is the inverse property of track=has_track. Then, I added more individuals to the ontology, which are concrete instances of music artist, record, track, Instrument and Genre classes, and I added object and data property values to them. This modified music ontology can be found [here](https://github.com/cherryBomba58/music-ontology-search/blob/master/music.ontology.search/musicontology.rdfs) in my repository.

## Requires
- Java EE 8
- Maven
- Wildfly 12.0.0.Final
- JBoss Tools (from Eclipse Marketplace, to make Wildfly work in Eclipse)
- HermiT (it is already in the project)

## To make it work
In MusicSearcher.java, change the INPUT_ONTOLOGY_FNAME to the absolute path of the musicontology.rdfs. If you want to add a relative path, then you should put the ontology file to the bin directory of Wildfly.
