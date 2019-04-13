package spring.tasks;

public class SparqlQuery {

    private final String sparql;
    private final String endpoint;

    public SparqlQuery(String sparql, String endpoint) {
        this.sparql = sparql;
        this.endpoint = endpoint;
    }

    public String getSparql() {
        return sparql;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
