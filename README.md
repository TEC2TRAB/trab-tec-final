# trab-tec-final
Trabalho final da disciplina de técnicas de programação I. 
Tema: Sistema de vendas.

Equipe: Italo Sousa.
        Bruno Dias.
        Esdras Lemos.
        César Rodrigues.
        João Victor Barbosa.

Módulos do Sistema:
        Vendas.
                Cadastra e consulta uma venda como base no produto e no funcionário.
                Atributos:{}
        Produtos.
                Cadastra e consulta um produto.
                Atributos:{}
        Pessoas. 
                Cadastro e consulta de funcionários e Clientes
                Atributos:{}
Banco de Dados:
        
        Pessoa -> [Funcionário, cliente].
                Pessoa: CPF(PK), RG, Endereço(...)
                        Funcionario: Id(PK), CPF(FK), DataDeAdmissão, Login(Unico), Senha.
                        Cliente: Id(PK), CPF(FK). 
        
        Produto.
                Id(PK), Descricao, quantidade, preço.
        
        Vendas.
                id(PK), id_vendedor(FK), id_cliente(FK), valor_total.
                
                Historico: id_venda(FK), id_produto(FK), qtd.


Listinha bolada do que fazer:

0 - Modelo de layout.(Primeiro Checkpoint do trabalho)

1 - Definir os módulos

2 - Construir a estrutura de cada cada módulo 
(O que vai ter, ex: cadastro de produto, alteração de valor e etc)

3 - Partir para as regras de restrições de cada módulo

4 - Implementar o layout

5 - Programar o layout
