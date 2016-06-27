package exercise.com.leo.base.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class SolrTest {

	static final String REQUEST_URL = "http://192.168.109.200:8983/solr/"; 
	
	public static void main(String[] args) {
		HttpSolrServer server = new HttpSolrServer(REQUEST_URL);
		
		query(server);
//		addIndex(server);
		
	}

	private static void update(HttpSolrServer server) {
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "17");
		document.addField("phone", "13000029999");
		document.addField("md5", "efab8a9e544c1a16d0e115bf90843fbf");
		document.addField("plusmd5", "4b6bafae6b865e731207569e4c531d2b");
		document.addField("sha", "15979ced5d4e24c895e461cddce7082b341ed5b5");
		document.addField("sha256", "04cbaf8fea4d07cc70c23e7d33d9605df4255a8d18b8afd8123f9b9227ed16a5");
		
		try {
			UpdateResponse response = server.add(document);
			server.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}

	private static void query(HttpSolrServer server) {
		server.setParser(new XMLResponseParser());
		
		SolrQuery parameters = new SolrQuery();
		
		parameters.set("q", "sha256:04cbaf8fea4d07cc70c23e7d33d9605df4255a8d18b8afd8123f9b9227ed16a5");
		parameters.set("wt", "xml");
//		parameters.set("fl", "phone");
		
		try {
			QueryResponse response = server.query(parameters);
			SolrDocumentList list = response.getResults();
			System.out.println(list.toString());
			for (SolrDocument solrDocument : list) {
				/*for (Entry<String, Object> entry : solrDocument) {
					System.out.println(entry.getKey() + " : " + entry.getValue());
				}*/
				System.out.println(solrDocument.getFieldValue("phone"));
				System.out.println(solrDocument.getFieldValue("md5"));
				System.out.println(solrDocument.getFieldValue("sha"));
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	}

}
