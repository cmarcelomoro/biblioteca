<?php

include("conexao.php");


$sql = "SELECT * from livros";
$resultado = $conexao->query($sql);
print("pegou");

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





?>