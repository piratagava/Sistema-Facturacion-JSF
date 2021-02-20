drop database facturacion;
create database facturacion;
	use facturacion;

	create table vendedor(
		codVendedor int auto_increment,
		nombres varchar (40) not null,
		apellidos varchar (40) not null,
		dui varchar(10) not null,
		celular varchar(10) not null,
		direccion varchar (150) not null,
		primary key(codVendedor));

	create table cliente(
		codCliente int auto_increment,
		nombres varchar(40) not null,
		apellidos varchar(40) not null,
		direccion varchar(40) not null,
		primary key(codCliente));

	create table producto(
		codProducto int auto_increment,
		nombreProducto varchar(40) not null,
		precioVenta decimal(10,2) not null,
		stockMinimo int not null,
		stockActual int not null,
		codBarra varchar (40)not null,
		primary key (codProducto));

	create table factura(
		codFactura int auto_increment,
		numeroFactura int not null,
		codVendedor int not null,
		codCliente int not null,
		totalVenta decimal(10,2) not null,
		fechaRegistro timestamp not null,
		primary key(codFactura),
		foreign key(codVendedor) references vendedor(codVendedor),
		foreign key(codCliente) references cliente(codCliente));

	create table detallefactura(
		codDetalle int auto_increment,
		codFactura int not null,
		codProducto int not null,
		codBarra varchar(40) not null,
		nombreProducto varchar(40) not null,
		cantidad int not null,
		precioVenta decimal (10,2) not null,
		total decimal(10,2) not null,
		primary key(codDetalle),
		foreign key(codFactura) references factura(codFactura),
		foreign key(codProducto)references producto(codProducto));

	create table usuario(
		codUsuario int auto_increment,
		nombreUsuario varchar(40) not null,
		password varchar(40) not null,
		codVendedor int not null,
		primary key(codUsuario),
		foreign key(codVendedor) references vendedor(codVendedor));
