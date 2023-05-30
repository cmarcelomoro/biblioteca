<?php

include("conexao.php");


function criaJSON($sql){
    $resultado = $conexao->query($sql);
    if($resultado->num_rows > 0){
        $rows = array();
   
       // Itera sobre os resultados e adiciona cada linha ao array
       while ($row = $resultado->fetch_assoc()) {
           $rows[] = $row;
       }
   
       // Converte o array em JSON
       $json = json_encode($rows);
   
       // Exibe o JSON  
       $json_com_quebra_de_linha = nl2br($json);
   
       echo $json_com_quebra_de_linha;

    }
}

$sql = "SELECT * from livros";



criaJSON(sql);



?>