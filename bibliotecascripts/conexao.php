<?php

	//conexão com o mysql
	$ip = "192.168.0.107";
	$usuario = "root";
	$senha = "Hellfire777@";
	
	$conexao = new mysqli($ip, $usuario, $senha);
	
	//conexão com o banco
	$conexao->select_db("biblioteca");
	
	if ($conexao->connect_error) {
		die("Conexão falhou: " . $conexao->connect_error); // Termina se houver algum problema
	}
?>