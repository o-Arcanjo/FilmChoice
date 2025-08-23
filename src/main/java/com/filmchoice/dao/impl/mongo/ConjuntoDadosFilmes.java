package com.filmchoice.dao.impl.mongo;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dao.impl.jdbc.AvaliacaoJDBCDAOImpl;
import com.filmchoice.entities.Avaliacao;
import com.filmchoice.entities.Filme;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConjuntoDadosFilmes  extends AbstractDAOMONGOImpl{
    private final MongoDatabase mongoDatabase;

    public ConjuntoDadosFilmes(AvaliacaoJDBCDAOImpl avaliacao)
            throws SQLException, InvalidKeyException, IOException, ClassNotFoundException {
        super(avaliacao);
        this.mongoDatabase = mongoDatabase;
    }

     private List<Avaliacao> obterPrimeirosCincoRankingGlobal() throws PersistenciaDawException{
        return super.avaliacao.obterPrimeirosCincoRankingGlobal();
    }
    // Função pública → converte JDBC → Document (Mongo), incluindo todos os campos
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

    // Novo método → persiste os documentos Mongo na coleção "ranking_filmes"
    public void persistirMongo() throws PersistenciaDawException {
        List<Document> documentos = obterPrimeirosCincoRankingGlobalMongo();

        MongoCollection<Document> collection = mongoDatabase.getCollection("ranking_filmes");

        if (!documentos.isEmpty()) {
            collection.insertMany(documentos);
        }
    }
}
