SELECT * 
FROM Veiculo
INNER JOIN Cliente
ON Veiculo.IdCliente = Cliente.IdCliente
WHERE Cliente.CPF = ?