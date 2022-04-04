/*  alter session set “_ORACLE_SCRIPT”=true;
	CREATE USER productos_limpieza IDENTIFIED BY 1234;
	GRANT ALL PRIVILEGES TO productos_limpieza;
	DISCONNECT productos_limpieza;
	CONNECT productos_limpieza;
 */
--TABLA CATEGORIA 
CREATE TABLE categoria(
id_categoria int primary key,
nombre_categoria varchar(50)
);

-- TABLA PRODUCTO
CREATE TABLE producto(
id_producto int primary key, 
nombre_producto varchar(75),
precio_producto int,
descripcion_producto varchar(200),
id_categoria int,
foreign key (id_categoria) references categoria(id_categoria)
);

CREATE TABLE admin (
	id_admin NUMBER,
	nombre VARCHAR2(25),
	password VARCHAR2(25),
	PRIMARY KEY (id_admin)
);

INSERT INTO admin (id_admin, nombre, password) VALUES (1,'admin', '1234');

CREATE SEQUENCE producto_seq MINVALUE 1 START WITH 1 INCREMENT BY 1
NOCACHE;
CREATE SEQUENCE categoria_seq MINVALUE 1 START WITH 1 INCREMENT BY 1
NOCACHE;

INSERT INTO categoria(id_categoria, nombre_categoria)
VALUES (categoria_seq.nextval, 'Detergente líquido');

INSERT INTO categoria(id_categoria, nombre_categoria)
VALUES (categoria_seq.nextval, 'Detergente en polvo');

INSERT INTO producto(id_producto, nombre_producto, precio_producto, descripcion_producto, id_categoria)
VALUES (producto_seq.nextval , 'Omo', 3500, 'Quita manchas', 1);

INSERT INTO producto(id_producto, nombre_producto, precio_producto, descripcion_producto, id_categoria)
VALUES (producto_seq.nextval , 'Ariel', 4400, 'Quita manchas', 2);

				
