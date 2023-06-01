<?php
	//conexão com o mysql
	$ip = "localhost";
	$usuario = "root";
	$senha = "laboratorio";
	
	$conexao = new mysqli($ip, $usuario, $senha);
	
	//conexão com o banco
	$conexao->select_db("biblioteca");
	
	if ($conexao->connect_error) {
		die("Conexão falhou: " . $conexao->connect_error); // Termina se houver algum problema
	}
?>