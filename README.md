# Music Ontology Search
Music Ontology Search is a RESTful Java EE web application, which provides browsing the individuals in an ontology called [Music Ontology](http://musicontology.com/), with some little modifications and examples of bands, albums, tracks etc.

## The Repository
The music.ontology.search is a Maven project, which was created with Eclipse Java EE Oxygen IDE.

Under the documentation directory, you can find the Hungarian documentation of the project (soon!).

## Requires
- Java EE 8
- Maven
- Wildfly 12.0.0.Final
- JBoss Tools (from Eclipse Marketplace, to make Wildfly work in Eclipse)
- HermiT (it is already in the project)

## To make it work
In MusicSearcher.java, change the INPUT_ONTOLOGY_FNAME to the absolute path of the musicontology.rdfs. If you want to add a relative path, then you should put the ontology file to the bin directory of Wildfly.
