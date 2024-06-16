use superKinalDB;

select * from NivelesAcceso;

INSERT INTO Cargos (nombreCargo, descripcionCargo) VALUES
('Gerente', 'Encargado de la gestión general de la empresa'),
('Asistente', 'Asistente administrativo y apoyo general'),
('Desarrollador', 'Desarrollo de software y mantenimiento de sistemas'),
('Contador', 'Manejo de cuentas y finanzas'),
('Recursos Humanos', 'Gestión de personal y reclutamiento');

INSERT INTO Empleados (nombreEmpleado, apellidoEmpleado, sueldo, horaEntrada, horaSalida, cargoId, encargadoId) VALUES
('Carlos', 'Martínez', 5000.00, '08:00:00', '17:00:00', 1, NULL),
('Ana', 'Gómez', 3500.00, '09:00:00', '18:00:00', 2, 1),
('Luis', 'Fernández', 4500.00, '08:30:00', '17:30:00', 3, 1),
('María', 'López', 4000.00, '08:00:00', '17:00:00', 4, 1),
('Laura', 'Rodríguez', 3000.00, '09:00:00', '18:00:00', 5, 1);

INSERT INTO Distribuidores (nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefonoDistribuidor, web) VALUES
('Distribuidora ABC', 'Calle 123, Ciudad X', '1234567890123', '123456789', 'www.abc.com'),
('Proveedores XYZ', 'Avenida 456, Ciudad Y', '2345678901234', '234567890', 'www.xyz.com'),
('Comercial LMN', 'Boulevard 789, Ciudad Z', '3456789012345', '345678901', 'www.lmn.com'),
('Suministros OPQ', 'Calle 101, Ciudad W', '4567890123456', '456789012', 'www.opq.com'),
('Importadora RST', 'Avenida 202, Ciudad V', '5678901234567', '567890123', 'www.rst.com');

INSERT INTO CategoriaProductos (nombreCategoria, descripcionCategoria) VALUES
('Electrónica', 'Productos relacionados con dispositivos electrónicos y gadgets'),
('Alimentos', 'Productos comestibles y bebidas'),
('Ropa', 'Prendas de vestir y accesorios'),
('Hogar', 'Artículos para el hogar y decoración'),
('Deportes', 'Equipamiento y ropa deportiva');

INSERT INTO Clientes (nombre, apellido, telefono, direccion, nit) VALUES
('Juan', 'Pérez', '123456789', 'Calle Falsa 123, Ciudad X', '1234567890123'),
('María', 'García', '234567890', 'Avenida Siempre Viva 456, Ciudad Y', '2345678901234'),
('Luis', 'Martínez', '345678901', 'Boulevard de los Sueños Rotos 789, Ciudad Z', '3456789012345'),
('Ana', 'López', '456789012', 'Callejón Sin Salida 101, Ciudad W', '4567890123456'),
('Carlos', 'Rodríguez', '567890123', 'Avenida del Sol 202, Ciudad V', '5678901234567');

INSERT INTO Compras (fechaCompra, totalCompra) VALUES
('2024-01-15', 1500.50),
('2024-02-20', 2300.00),
('2024-03-10', 750.75),
('2024-04-05', 1250.00),
('2024-05-18', 3000.25);

INSERT INTO DetalleCompra (cantidadCompra, productoId, compraId) VALUES
(2, 1, 1),
(1, 2, 2),
(5, 3, 3),
(3, 4, 4),
(1, 5, 5);

INSERT INTO Promociones (precioPromocion, descripcionPromocion, fechaInicio, fechaFinalizacion, productoId) VALUES
(650.00, 'Descuento en Televisor 50"', '2024-06-01', '2024-06-30', 1),
(1100.00, 'Oferta especial en Laptop Gamer', '2024-06-10', '2024-07-10', 2),
(15.00, 'Promoción en Camiseta Deportiva', '2024-06-05', '2024-06-20', 3),
(40.00, 'Descuento en Juego de Sábanas', '2024-06-15', '2024-07-15', 4),
(400.00, 'Oferta en Bicicleta de Montaña', '2024-06-20', '2024-07-20', 5);

-- INSERT INTO Productos (nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, imagenProducto, distribuidorId, categoriaProductosId) VALUES
-- ('Televisor 50"', 'Televisor de 50 pulgadas, 4K UHD', 50, 700.00, 650.00, 500.00, NULL, 1, 1),
-- ('Laptop Gamer', 'Laptop para videojuegos con alta performance', 30, 1200.00, 1150.00, 900.00, NULL, 2, 1),
-- ('Camiseta Deportiva', 'Camiseta transpirable para deportes', 200, 20.00, 18.00, 10.00, NULL, 3, 3),
-- ('Juego de Sábanas', 'Juego de sábanas de algodón para cama queen size', 100, 50.00, 45.00, 30.00, NULL, 4, 4),
-- ('Bicicleta de Montaña', 'Bicicleta todo terreno con suspensión completa', 25, 500.00, 450.00, 350.00, NULL, 5, 5);

-- INSERT INTO Facturas (fecha, hora, clienteId, empleadoId, total) VALUES
-- ('2024-06-01', '10:30:00', 1, 1, 1500.50),
-- ('2024-06-02', '11:45:00', 2, 2, 2300.00),
-- ('2024-06-03', '12:00:00', 3, 3, 750.75),
-- ('2024-06-04', '13:30:00', 4, 4, 1250.00),
-- ('2024-06-05', '14:45:00', 5, 5, 3000.25);

-- INSERT INTO DetalleFactura (facturaId, productoId) VALUES
-- (1, 1),
-- (1, 3),
-- (2, 2),
-- (3, 3),
-- (4, 4),
-- (5, 5),
-- (5, 1),
-- (2, 4),
-- (3, 5),
-- (4, 2);
