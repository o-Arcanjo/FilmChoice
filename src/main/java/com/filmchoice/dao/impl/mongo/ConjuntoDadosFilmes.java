package com.filmchoice.dao.impl.mongo;
import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dao.impl.jdbc.AvaliacaoJDBCDAOImpl;
import com.filmchoice.entities.Avaliacao;
import com.filmchoice.entities.Filme;
import org.bson.Document;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.reactivestreams.client.MongoCollection;

public class ConjuntoDadosFilmes  extends AbstractDAOMONGOImpl{
    public ConjuntoDadosFilmes(AvaliacaoJDBCDAOImpl avaliacao)
            throws SQLException, InvalidKeyException, IOException, ClassNotFoundException {
        super(avaliacao);
    }
     private List<Avaliacao> obterPrimeirosCincoRankingGlobal() throws PersistenciaDawException{
        return super.avaliacao.obterPrimeirosCincoRankingGlobal();
    }
   
    public List<Document> obterPrimeirosCincoRankingGlobalMongo() throws PersistenciaDawException {
        List<Avaliacao> avaliacoes = obterPrimeirosCincoRankingGlobal();
        List<Document> documentos = new ArrayList<>();

        for (Avaliacao a : avaliacoes) {
            Filme f = a.getFilme();

            Document docFilme = new Document()
                    .append("id", f.getId())
                    .append("titulo", f.getTitulo())
                    .append("atores", f.getAtores())
                    .append("diretores", f.getDiretores())
                    .append("generos", f.getGeneros())
                    .append("idiomas", f.getIdiomas());

            Document docAvaliacao = new Document()
                    .append("filme", docFilme)
                    .append("mediaConfiavel", a.getMediaConfiavel());

            documentos.add(docAvaliacao);
        }

        return documentos;
    }

    public void persistirMongo() throws PersistenciaDawException {
        List<Document> documentos = obterPrimeirosCincoRankingGlobalMongo();
        MongoCollection<Document> collection = super.mongoDatabase.getCollection("ranking_filmes");

        if (!documentos.isEmpty()) {
            collection.insertMany(documentos);
        }
    }
}
