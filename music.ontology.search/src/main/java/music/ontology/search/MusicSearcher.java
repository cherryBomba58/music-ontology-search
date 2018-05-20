package music.ontology.search;

import java.io.File;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLException;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;

public class MusicSearcher {
	
	public static final String INPUT_ONTOLOGY_FNAME = "D:/HÁZI/BME-MSc-3.felev/kompMI/Eclipse/music.ontology.search/musicontology.rdfs";
	public static final String INPUT_ONTOLOGY_BASE_URI = "http://purl.org/ontology/mo/";
	
	final OWLOntologyManager manager;
	final OWLOntology onto;
	final OWLReasoner reasoner;
	final OWLDataFactory fac;
	
	public void testSatisfiable(OWLReasoner reasoner) throws OWLException {
		reasoner.isConsistent();
		reasoner.getUnsatisfiableClasses();
	}
	
	public IRI getIRI(String name) {
		return IRI.create(INPUT_ONTOLOGY_BASE_URI + name);
	}

	public OWLClass cls(String name) {
		return fac.getOWLClass(getIRI(name));
	}

	public OWLNamedIndividual ind(String name) {
		return fac.getOWLNamedIndividual(getIRI(name));
	}
	
	public String searchTerm(String term) {
		String result;
		IRI termIRI = getIRI(term);
		if(!onto.containsIndividualInSignature(termIRI)) {
			result = "No such individual in the ontology!\nTry with one of these:\n\n";
			for(OWLNamedIndividual indi: onto.getIndividualsInSignature()) {
				if(reasoner.isEntailed(fac.getOWLClassAssertionAxiom(cls("MusicArtist"), indi))) {
					result = result + indi.getIRI().getFragment() + "\n";
				}
			}
			return result;
		}
		
		OWLNamedIndividual indiv = ind(term);
		result = indiv.getIRI().getFragment() + "\n\n";
		for(OWLObjectProperty op : onto.getObjectPropertiesInSignature()) {
			NodeSet<OWLNamedIndividual> ops = reasoner.getObjectPropertyValues(indiv, op);
            for (OWLNamedIndividual indi : ops.getFlattened()) {
            	result = result + op.getIRI().getFragment() + ": " + indi.getIRI().getFragment() + "\n";
            }
		}
		result = result + "\n";
		
		for(OWLDataProperty dp : onto.getDataPropertiesInSignature()) {
			Set<OWLLiteral> dps = reasoner.getDataPropertyValues(indiv, dp);
			for (OWLLiteral lit : dps) {
				result = result + dp.getIRI().getFragment() + ": " + lit.getLiteral() + "\n";
			}
		}
		result = result + "\n";
		
		return result;
	}
	
	public MusicSearcher() throws Exception {
		manager = OWLManager.createOWLOntologyManager();
		fac = manager.getOWLDataFactory();
		onto = manager.loadOntologyFromOntologyDocument(new File(INPUT_ONTOLOGY_FNAME));
		System.out.println("Ontológia betöltve: " 
				+ manager.getOntologyDocumentIRI(onto));

		OWLReasonerFactory reasonerFactory = new org.semanticweb.HermiT.Reasoner.ReasonerFactory();
		reasoner = reasonerFactory.createReasoner(onto);
		testSatisfiable(reasoner);
	}
	
}
