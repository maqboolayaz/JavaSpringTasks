package spring.tasks;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.Model;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
public class ResponseController {

    ByteArrayOutputStream ntriplesStream;
    StringBuffer tripleStore;

    @RequestMapping("/test")
    public String Test(@RequestParam(value="str") String str) {
        return "This is the response";
    }

    @RequestMapping(value = "/fetch-all")
    public String fetchAll() {
        return tripleStore.toString();
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String execConstructSparql(SparqlQuery inputQuery){

        Query query = QueryFactory.create(inputQuery.getSparql());
        QueryExecution qexec = QueryExecutionFactory.sparqlService(inputQuery.getEndpoint(), query);
        Model resultModel = qexec.execConstruct();
        qexec.close();

        ntriplesStream = new ByteArrayOutputStream();
        resultModel.write(ntriplesStream, "NTRIPLES");
        tripleStore = new StringBuffer();
        tripleStore.append(ntriplesStream.toString());
        return ntriplesStream.toString();
    }
}