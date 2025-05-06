# 🎥 **FilmChoice**

## ❗️ **Problema**

Atualmente, há uma ausência de mecanismos que apresentem aos usuários categorias de filmes bem avaliados com base em critérios personalizados, os quais despertam seu interesse.

---

## 🎬 **Introdução**

Observa-se que os usuários de plataformas de streaming tendem a assistir a filmes, séries ou documentários que contenham ao menos um dos elementos que mais apreciam, como: atores, diretores, temas abordados, cenas marcantes, comentários em destaque e, com frequência, o gênero da produção.  
Esse comportamento é amplamente influenciado por aplicativos como **IMDb**, **Letterboxd**, **Rotten Tomatoes** e similares, os quais moldam a escolha de filmes com base em um conjunto de fatores:

- A opinião individual de cada usuário;  
- A média da comunidade;  
- O pensamento coletivo social;  
- E as críticas especializadas.

No entanto, nota-se a **ausência de mecanismos personalizados mais sofisticados**, que cruzem avaliações com métricas de qualidade como pontuação, frequência de visualização e comentários.  

Para solucionar esse problema, surgiu a iniciativa deste projeto, idealizado por **Renato Medeiros**, **Thiago Bezerra** e **Miguel Arcanjo**, estudantes do **IFPB Campus Esperança**.

A aplicação **FilmChoice**, como o nome sugere, visa **indicar filmes adequados ao perfil de cada usuário**, com base em critérios relevantes. Após discussões entre os membros da equipe, decidiu-se iniciar o projeto com o critério **gênero cinematográfico**, a fim de detalhar os atributos e relacionamentos dos dados em um tempo viável.

---

## 🎯 **Objetivo**

Elencar filmes de determinadas categorias com características semelhantes àquelas que o usuário já assistiu, considerando principalmente:

- Nível de pontuação positiva ⭐️;
- Quantidade de comentários 💬;
- Número de visualizações 👁️;

Inicialmente, o foco estará na categoria **gênero** de filme.

---

## ⚙️ **Metodologia**

> *(Em construção – esta seção descreverá como os dados serão obtidos, tratados e como será implementada a lógica de recomendação personalizada.)*

---

## 🌐 **Minimundo**

A aplicação **FilmChoice** tem como objetivo centralizar informações sobre filmes classificados por **gênero**, mapeando também os **atores**, **diretores** e os **idiomas principais ou disponíveis** de cada obra.  
Com base nesses dados, o sistema busca identificar e recomendar aos usuários os títulos mais agradáveis ao seu perfil. Para isso, são considerados critérios principais:

- 🗣️ **Idiomas de Filmes Disponíveis na Linguagem do Usuário**
- 👁️ **Filmes mais assistidos por gênero**;
- ⭐️ **Filmes mais bem-avaliados por gênero**.

Além disso, o sistema analisa a **quantidade de comentários** 💬 por filme, a fim de compreender os motivos das avaliações, o que permite:

- 👍 **Avaliações positivas**: apontam que o estilo do filme é relevante para o usuário ou algum critério novo considerado nos comentários do usuário moldou a opinião classificatória;
- 👎 **Avaliações negativas**: indicam que fatores externos ao gênero (como expectativa, contexto, etc.) podem ter influenciado negativamente a pontuação.

Isso faz com que:

- Filmes de categorias diferentes tenham avaliações positivas com algum padrão em uma dessas categorias que possivelmente seja gênero cinematográfico.

- Comentários de avaliações positivas indicam quais principais critérios os usuários aplicaram em considerar uma categoria que possivelmente seja a de gênero cinematográfico.

- Comentários de avaliações negativas em filmes com gêneros cinematográficos consideráveis apresentam os motivos de qual categoria se baseou a pontuação negativa.

- A maioria dos filmes de mesmo gênero cinematográfico que apresentem avaliações positivas ou de alguma outra categoria atraia usuários para assistir a filmes.



A proposta inclui futuras integrações com outros fatores que influenciam a avaliação, utilizando os próprios **comentários dos usuários** 🧠 como fonte para descobrir novas variáveis relevantes, bem como a adaptação para incluir:

- 🎭 **Filmes mais assistidos por algum ator ou diretor**
- 🏆 **Filmes mais bem avaliados por algum ator ou diretor**

---

## 🏗 Modelo Conceitual


Segue abaixo o modelo conceitual do projeto, representado por um diagrama de entidade relacionamento

![](https://github.com/o-Arcanjo/FilmChoice/blob/main/imagens/modelo_conceitual.PNG)



## 🏗 Modelo Lógico


Segue abaixo o modelo lógico do projeto, representado por um diagrama de entidade relacionamento

![](https://github.com/o-Arcanjo/FilmChoice/blob/main/imagens/modelo_logico.PNG)
---
