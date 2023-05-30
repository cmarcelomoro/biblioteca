<?php

    	include("conexao.php");
       

        if(isset($_POST['titulo']) && isset($_POST['autor'])
        && isset($_POST['ano']) && isset($_POST['numPaginas']) && isset($_POST['genero']) 
        && isset($_POST['classificacao'])  && isset($_POST['meuId'])){

            $titulo = $_POST['titulo'];
            $autor = $_POST['autor'];
            $ano = $_POST['ano'];
            $numPaginas = $_POST['numPaginas'];
            $classificacao = $_POST['classificacao'];
            $genero = $_POST['genero'];
            $idPessoa = $_POST['meuId'];

            print("dados_recebidos");
           

            

   
            $sqlInserirLivro = "INSERT INTO livro (titulo,autor,
            ano, numPaginas, classificacao, genero) VALUES ('$titulo','$autor','$ano','$numPaginas','$classificacao','$genero');";


         	if ($conexao->query($sqlInserirLivro)  === TRUE) {
                $idLivro = $conexao->insert_id;
                print("livro_inserido");
                $sqlInserirDoacao = "INSERT INTO doacao (fk_pessoa, fk_livro) VALUES ('$idPessoa','$idLivro');";
                 if($conexao->query($sqlInserirDoacao) === TRUE){
                    print("doacao_inserida");
                 }else{
                    print("erro_doacao");
                 }
                
              }
              else{
                 print("erro_ao_inserir");
                 }
                 
        }else{
            print("erro_post");
   
       }




?>