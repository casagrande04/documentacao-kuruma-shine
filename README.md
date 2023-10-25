# ☕ Java MySQL - Projeto Kuruma Shine - Ordem de Serviço

# Instalação e Demonstração do Projeto
A seguir estão descritas as instruções para uma cópia do projeto desenvolvido em Java no SENAC Tatuapé para utilização do programa de Ordem de Serviço.

# Pré requisitos:
É necessário ter instalado no computador:

• Java JDK 8;
• XAMPP.
# Instalação:
• Inicie o XAMPP;
• Dê Start no "Apache" e no "MySQL";

![xampp](https://github.com/casagrande04/CarSaoOS/assets/104094435/189f624f-00c2-48da-b06f-1722b0d20f18)

• Clique na opção de Admin do MySQL no XAMPP;
![xampp2](https://github.com/casagrande04/CarSaoOS/assets/104094435/c080237c-63c1-48cc-acc4-b0f1a2d2770c)

• Após clicar na opção de Admin, você será redirecionado para a página do phpmyadmin para fazer a criação do Banco de Dados;
• Dentro do site clicar na opção sinalizada na imagem em "SQL";

![xampp](https://github.com/casagrande04/CarSaoOS/assets/104094435/6249160a-6d2c-4ad4-a258-0340adf42773)

• Depois de clicar na opção "SQL", cole seu script do banco de dados e execute com CTRL + ENTER para a criação do mesmo;

• Copie os códigos abaixo:
create database dbsistema;
use dbsistema;
show tables;
create table usuarios (
id int primary key auto_increment,
	nome varchar(50) not null,
	login varchar(15) not null unique,
    senha varchar (250) not null,
    perfil varchar (10)
);

insert into usuarios (nome,login,senha,perfil) values ('administrador','admin', md5('admin'), 'admin');


create table clientes (
	idcli int primary key auto_increment,
	nome varchar (50) not null,
    cpf varchar (11) not null unique ,
    contato varchar (18) not null,
    cep varchar (10),
	endereco varchar (100) not null,
    numero varchar (10) not null,
    complemento varchar (20),
    bairro varchar (40) not null,
    cidade varchar (30) not null,
    uf char (2) not null,
    referencia varchar (40)
    );
    

create table servicos (
os int primary key auto_increment,
dataOS timestamp default current_timestamp,
veiculo varchar(200) not null,
servico varchar(200) not null,
valor decimal(10,2),
idcli int not null,
foreign key (idcli) references clientes (idcli)
);

create table fornecedores (
codefornecedor int primary key auto_increment,
razaosocial varchar (50) not null,
fantasia varchar (50) not null,
fone varchar(18) not null ,
vendedor varchar (20),
cnpj varchar (20) not null unique,
site varchar(50),
ie varchar (50),     
email varchar (50) not null,
cep varchar(10),
endereco varchar(50) not null,
numero varchar (20) not null,
comp varchar (20),
bairro varchar (30) not null,
cidade varchar(30) not null,
uf char(2) not null,
ref varchar (50)
);


create table produtos (
codigo int primary key auto_increment,
barcode varchar(250) unique,
descricao varchar(250) unique not null,
produto varchar (50) not null,
lote varchar (20) not null,
fabricante varchar (50),
dataent timestamp default current_timestamp,
dataval date not null,
custo decimal (10,2) not null,
lucro decimal (10,2),
foto longblob,
estoque int not null,
estoquemin int not null,
unidademedida char(2) not null,
localarmazenagem varchar (50),
idfornecedor int not null, 
foreign key (idfornecedor) references fornecedores(codefornecedor)
);

![xampp4](https://github.com/casagrande04/CarSaoOS/assets/104094435/69de1393-ec69-4b3b-a9ec-539fb637f7a2)

