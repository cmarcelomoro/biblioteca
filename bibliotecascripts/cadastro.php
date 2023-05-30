<?php
	include("conexao.php");
	 if(isset($_POST['nome']) && isset($_POST['senha'])){
	 	$nome = $_POST['nome'];
		$senha = $_POST['senha'];
	 	$senhaHash = password_hash($senha, PASSWORD_DEFAULT);

	 	$sql = "INSERT INTO Pessoa (Nome, Senha) VALUES ('$nome','$senhaHash')";
		if ($conexao->query($sql) === TRUE) {
	 		print("dados_inseridos");
	 	}
	 	else{
			print("erro_ao_inserir");
	 	}
		
	 }else{
	 	print("erro_post");

	}


?>