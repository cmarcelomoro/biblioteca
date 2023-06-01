<?php
header("Content-Type: application/json; charset=UTF-8");
function criaJSON($sql){
    include("conexao.php");
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
$sql = "SELECT * from livro";
criaJSON($sql);
/*$resposta = ['livros' => array()];
$jsonResultado = json_encode($resposta);
print($jsonResultado);*/
?>