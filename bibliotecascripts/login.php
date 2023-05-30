
<?php
	include("conexao.php");
	
	if(isset($_POST['nome']) && isset($_POST['senha'])){
		$nome = $_POST['nome'];
		$senha = $_POST['senha'];
		
		
		
	 	$sql = "SELECT * FROM pessoa WHERE nome = '$nome';";
		$resultado = $conexao->query($sql);
		
	 	if ($resultado->num_rows > 0) {
	 		$linha = $resultado->fetch_assoc();
	 		if(password_verify($senha, $linha['senha'])){

				print("logado_".$linha['id']);
	 		}
	 		else{
	 			print("senha_incorreta");
	 		}
	 	}
	 	else{
	 		print("conta_n_encontrada");
	 	}
	 }
	 else{
	 	print("erro_post");
	}

?>